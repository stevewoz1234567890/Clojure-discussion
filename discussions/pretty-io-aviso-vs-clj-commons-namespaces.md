# `io.aviso/pretty` vs `org.clj-commons/pretty` — duplicate namespaces & classpath pain

This note summarizes a **Clojurians Slack** thread about migrating from **`io.aviso/pretty`** to **`org.clj-commons/pretty`** while **keeping the same `io.aviso.*` namespaces**. That migration strategy collides at load time when **both JARs** appear on the classpath: Clojure sees **two different `io/aviso/repl.clj`** resources (and related **`io.aviso`** namespaces). What follows is informal chat, not vendor guidance; validate against current artifact contents and your dependency tree.

---

## What went wrong

Someone bumped **`io.aviso/pretty`** to **1.4.4** (still publishing **`io.aviso`** namespaces) but had **`org.clj-commons/pretty`** on the classpath as well. Loading **`io/aviso/repl.clj`** from the older-coords JAR failed with:

```text
IllegalStateException: Alias repl already exists in namespace io.aviso.repl, aliasing clj-commons.pretty.repl
```

So **`io.aviso.repl`** had already been partially established from **one** implementation when **another** implementation’s file was loaded—classic **duplicate namespace / wrong JAR wins** failure. The reporter noted it took a while to notice a second **`io.aviso.repl`** living under **different Maven coordinates**.

**`org.clj-commons/pretty`** intentionally keeps **`src/io/aviso`** paths (compatibility bridge) so **`io.aviso.*`** namespaces exist there too—see **[clj-commons/pretty](https://github.com/clj-commons/pretty/tree/main/src/io/aviso)**.

---

## Thread themes

### “Coexist” vs “eliminate”

The **clj-commons** line was meant to ease **replacing** **`io.aviso/pretty`** on a bump: drop one coordinate, add the other, optionally use **bridge** namespaces. In practice, having **both** artifacts can mean **classpath order** decides which **`io.aviso.repl`** you get—unless you use **`:exclusions`** and ensure a **single** implementation. That is not peaceful **side-by-side coexistence** for the same **`io.aviso`** tree; it is **pick one**.

### Why bridge / overlap exists

**Howard Lewis Ship** ([@hlship](https://github.com/hlship)) noted the **bridge** idea: make migration easier. At one point **REPL bridge** code lived in a **secondary** library; that caused **operational pain** (e.g. many **Nubank** repos), so bridge code moved **back** into the main library. He allowed he would **revisit** stripping the bridge again and fixing the Nubank side after this thread.

### Transitive pins

**eftest** was called out as still depending on **`io.aviso/pretty`** (e.g. **1.3.x**); bumping to **1.4.4** worked in that scenario, but **overlapping** coords remain a footgun.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|-------------------------|
| **p-himik** | Hit **`Alias repl already exists`** when both **`io.aviso/pretty`** **1.4.4** and **`org.clj-commons/pretty`** supplied **`io.aviso.repl`**; argues true coexistence needs **distinct** namespaces, not two **`io/aviso/*.clj`** on the classpath. |
| **seancorfield** | Points to **`org.clj-commons/pretty`** **`io/aviso`** overlap with the legacy artifact; notes **1.4.4** is older but not prehistoric (**June 2023** vintage came up jokingly). |
| **hlship** | Explains **bridge** rationale and past **secondary-library** extraction that hurt large org adoption; open to **removing bridge** again and aligning **Nubank** once feasible. |

---

## Update — `org.clj-commons/pretty` **3.8.0**

As of **[3.8.0](https://github.com/clj-commons/pretty/releases/tag/3.8.0)**, **`io.aviso.exception`** and **`io.aviso.repl`** are **no longer** shipped inside **`org.clj-commons/pretty`**; use **[pretty-aviso-bridge](https://github.com/clj-commons/pretty-aviso-bridge)** instead. Community release summary: **[`clj-commons/pretty` 3.8.0 — bridge namespaces split out](clj-commons-pretty-3.8.0.md)**.

---

## See also

- [GitHub — clj-commons/pretty](https://github.com/clj-commons/pretty) ([releases](https://github.com/clj-commons/pretty/releases); **`io.aviso.*`** compatibility → **[pretty-aviso-bridge](https://github.com/clj-commons/pretty-aviso-bridge)**)
- Clojure classpath semantics — **first resource wins** per **`io/aviso/repl.clj`** path; **`tools.deps`** / Leiningen **`:exclusions`** when you must force a single provider

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
