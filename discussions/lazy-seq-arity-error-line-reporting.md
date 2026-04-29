# Lazy sequences & arity errors — where the exception points — community thread

This note summarizes a short exchange about **misleading line numbers** when a bug lives in a **lazy sequence** (e.g. **`filter`** with a predicate of the **wrong arity**) but **`clojure.lang.ArityException`** is raised only when the sequence is **forced**—often on a **later** form such as **`empty?`**, **`first`**, or **`doall`**. The same behavior shows up on **Clojure** and **[Babashka](https://babashka.org/)** here. Informal synthesis, not JVM-debugging gospel.

---

## The confusing shape

A predicate passed to **`filter`** must accept **one** element per step. Binding two parameters in the **`fn`** by mistake—

```clojure
(filter (fn [error-file error-files]
          ;; …)
        error-files)
```

—produces a **`fn`** whose **fixed arity is two**; **`filter`** invokes it with **one** argument **per element**. While the **`filter`** **`seq`** stays **lazy**, that predicate simply **never runs**, so arity problems stay hidden.

When **`empty?`** (or **`first`**, **`rest`**, **`doseq`**, …) walks the **`seq`**, **`filter`** finally calls the predicate → **`ArityException`**. Stack traces and REPL highlights therefore lean on the **consumer**, not the **`filter`** expression, even though the authoring bug is the predicate’s **arity**.

---

## Minimal reproduction (Jan Šuráň)

Shared on **both** Clojure REPL **and** Babashka:

```clojure
(let [res (filter (fn [a b]
                   (even? a))
                  (range 10))]
  (empty? res))
;; clojure.lang.ArityException:
;; Wrong number of args (1) passed to: function of arity 2 [at <repl>:4:10]
```

The reported **location tracks realization** (**`empty?`** realizes the **`seq`**), **not** the **`filter`** **`fn`**. **[@borkdude](https://github.com/borkdude)** confirmed Clojure reports similarly—“the error happens there because **that’s where the lazy seq is realized**”—and asked for shrunk, dependency-free repros when escalating.

---

## Anonymous vs named call sites

**Jan Šuráň** contrasted arity mistakes directly on **`empty?`**:

```clojure
user=> (empty? [] [])
;; ArityException: Wrong number of args (2) passed to: clojure.core/empty? [at <repl>:5:1]
```

Calling **`empty?`** with wrong **arity**, the **`ArityException`** names **`clojure.core/empty?`** plainly. Anonymous predicates inside **`filter`** surface instead as **function of arity …**—no obvious **`var`** name alongside.

---

## Takeaway

- **Realization site ≠ authoring site.** The stack highlights where **`seq`** is **forced** (`empty?`, **`first`**, **`rest`**, **`doseq`**, …), not always where **`filter`** / **`map`** / **`for`** closes around the erroneous **`fn`**.
- Narrow things down with **`take`**, **`*print-length*`**, or a **minimal** **`let`/three-line** repro.
- **Naming predicates** (**`defn`** or **`let`** bind) sometimes clarifies **`ArityException`** wording compared to deeply nested **`fn`** forms.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|---------------------------|
| **Thread OP** | Babashka test harness: wrong-arity **`filter`** predicate; exception surfaced at **`empty?`** (misleading line). |
| **Jan Šuráň** | Minimal **`let`/`filter`/`empty?`** repro; Clojure matches Babashka; contrasts messages for **`anonymous` predicate** vs **`clojure.core/empty?`**. |
| **[@borkdude](https://github.com/borkdude)** | Smaller reproductions help upstream; emphasizes **lazy realization** as line anchor (same behavior on Clojure). |

---

## See also

- [Babashka book](https://book.babashka.org/)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
