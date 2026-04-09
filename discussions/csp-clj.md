# `csp-clj` — CSP on Clojure with JDK virtual threads (PoC)

**[csp-clj](https://github.com/fgasperino/csp-clj)** is a **proof-of-concept** **[Communicating Sequential Processes](https://en.wikipedia.org/wiki/Communicating_sequential_processes) (CSP)** library for **Clojure**, aimed at developers who already know **core.async**–style channel programming but want an implementation built on **modern JDK** concurrency primitives.

## Idea

- **JDK 24+** **[virtual threads](https://openjdk.org/jeps/444)** — channel operations **block** the current **virtual** thread; the author argues that makes the model **explicit** and cheap at scale (no thread-pool exhaustion for typical “lots of blocked tasks” workloads).
- **Versus `core.async`** — **`core.async`** uses **state machines** and **parking**; **`csp-clj`** uses **real blocking** on virtual threads and **plain function calls** (no macro-driven transform).
- **Naming** — e.g. **`multiplex`** / **`select`** where behavior differs from **`core.async`**’s **`mult`** / **`alts!`**, so call sites reflect semantics.

Status: **experimental**; treat as a **lab** for API and performance exploration, not a drop-in replacement for production **`core.async`** without your own review.

## Coordinates (from upstream)

```clojure
;; deps.edn
{:deps {org.clojars.fgasperino/csp-clj {:mvn/version "0.0.0"}}}
```

**Requirement:** **JDK 24** or later. Build notes: **[BUILD.md](https://github.com/fgasperino/csp-clj/blob/master/BUILD.md)**.

## Links

- [GitHub — fgasperino/csp-clj](https://github.com/fgasperino/csp-clj) (long **README** with tutorials and examples)  
- [Clojars — `org.clojars.fgasperino/csp-clj`](https://clojars.org/org.clojars.fgasperino/csp-clj)  
- Related ecosystem: **[core.async](https://github.com/clojure/core.async)**, **[missionary](https://github.com/leonoel/missionary)** (different model), classic JVM CSP threads in the **JCSP** / **C++CSP** lineage.

## Credits

Library by **[Franco Gasperino](https://github.com/fgasperino)** ([@fgasperino](https://github.com/fgasperino)).
