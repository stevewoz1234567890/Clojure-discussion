# Babashka — catching JSON parse errors when `import` of `JsonParseException` fails — community thread

This note summarizes a short exchange about **handling invalid JSON** in **[Babashka](https://babashka.org/)** when code that works on the **JVM** uses **`(catch JsonParseException …)`**, but **`(import '[com.fasterxml.jackson.core JsonParseException])`** raises **`Unable to resolve classname`**. Informal recap, not upstream babashka policy.

---

## The symptom

On the JVM, idiomatic code often looks like:

```clojure
(try
  (cheshire.core/parse-string s)
  (catch com.fasterxml.jackson.core.JsonParseException _ nil))
```

In Babashka, **`import`** (or **`(:import …)`**) for **`com.fasterxml.jackson.core.JsonParseException`** can fail with something like:

```text
java.lang.Exception: Unable to resolve classname: com.fasterxml.jackson.core.JsonParseException
```

That message reflects **the class not being registered for direct resolution** in the **native** image—**not** “JSON did not parse.”

---

## Why it happens

**Babashka** is built with **GraalVM native-image**. Only **certain Java classes** are **compiled in** and exposed the way **`import`** expects; you **cannot** load arbitrary new Java classes at runtime the way a normal JVM process can.

**[Cheshire](https://github.com/dakrone/cheshire)** is bundled and used for JSON, and **`cheshire.core/parse-string`** can still throw Jackson’s **`JsonParseException`** under the hood—but **`JsonParseException`** may **not** appear in the same **`import`** surface as on Clojure-on-JVM. **[`babashka.impl.classes`](https://github.com/babashka/babashka/blob/master/src/babashka/impl/classes.clj)** (internal list of reflected classes) is the place maintainers extend that surface; it is useful when debugging “why can’t I **`import`** this type?”.

---

## Workaround: `Class/forName` + `instance?`

**exitsandman** suggested resolving the class **by name** and branching with **`instance?`** inside a broad **`catch`**:

```clojure
(def ^Class json-parse-exception-class
  (Class/forName "com.fasterxml.jackson.core.JsonParseException"))

(try
  (cheshire.core/parse-string s)
  (catch Exception e
    (if (instance? json-parse-exception-class e)
      nil ;; or your parse-error value
      (throw e))))
```

A minimal REPL check (from the thread) is simply: parse invalid input, **`catch Exception`**, and **`instance?`** against that **`Class`** to confirm the concrete type.

**ray1729** reported this pattern as a good-enough substitute for **`(catch JsonParseException _ …)`** until **`import`** behaves like on the JVM.

---

## Alternatives called out in chat

- Catching plain **`Exception`** without **`instance?`** is always possible but **less specific**—the thread preferred narrowing when feasible.
- **`teodorlu`** clarified that the **`import`** error is about **classpath / native registration**, not the JSON error itself, and verified **`JsonParseException`** in the **ancestor chain** of the thrown value when **`cheshire.core/parse-string`** gets bad input.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|------------------------|
| **ray1729** | Asked for **`JsonParseException`** in **`try`/`catch`** like JVM Clojure; **`import`** fails in Babashka; confirmed **`Class/forName`** + **`instance?`** works. |
| **exitsandman** | **`Class/forName`** + **`instance?`** workaround sketch. |
| **teodorlu** | Babashka’s limited Java surface; Cheshire vs Jackson **`import`**; pointer to **`impl/classes.clj`**; **`JsonParseException`** as ancestor of thrown parse errors. |

---

## See also

- [Cheshire](https://github.com/dakrone/cheshire) — **`cheshire.core/parse-string`**
- [Babashka book](https://book.babashka.org/) — scripting and native image constraints
- [Jackson `JsonParseException` (Javadoc)](https://fasterxml.github.io/jackson-core/javadoc/2.14/com/fasterxml/jackson/core/JsonParseException.html)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
