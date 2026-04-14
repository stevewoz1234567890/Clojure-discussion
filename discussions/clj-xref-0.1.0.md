# clj-xref 0.1.0 — LLM-friendly cross-reference database for Clojure

**[clj-xref](https://github.com/danlentz/clj-xref)** ([`com.github.danlentz/clj-xref`](https://clojars.org/com.github.danlentz/clj-xref) **0.1.0**) is a **queryable cross-reference database** for Clojure projects, built on **[clj-kondo](https://github.com/clj-kondo/clj-kondo)** static analysis. **Generate once** (`lein xref` or `clj -T:xref generate`), then **query from the REPL** with plain **maps** and **vectors** you can compose with `filter`, `map`, `group-by`, and the rest of Clojure.

## Why it exists (including LLM context)

Instead of pointing an AI assistant at an entire `src/` tree, you can load an xref DB and pull only the **dependency neighborhood** of the var you are editing—**fewer files and fewer tokens** for the same semantic slice. The author also positions it for **dead code** detection, **Graphviz** / DOT graphs, **CI** impact analysis, and **REPL-driven** exploration of unfamiliar code.

## Quick API (from the announcement)

```clojure
(require '[clj-xref.core :as xref])

(def db (xref/load-db))  ;; after generate — default `.clj-xref/xref.edn`

(xref/who-calls db 'myapp.orders/process-payment)
(xref/calls-who db 'myapp.web/handler)
(xref/who-implements db 'myapp.protocols/Billable)
(xref/unused-vars db)
(xref/call-graph db 'myapp.core/main {:depth 3})
```

See the upstream README for options such as **`:direction`** on `call-graph`, **in-memory** `(xref/analyze ["src" "test"])`, and incremental **`:only`** generation.

## Query surface

**who-calls**, **calls-who**, **who-references**, **who-macroexpands**, **who-implements**, **who-dispatches**, **unused-vars**, **call-graph**, **apropos**, **ns-deps**, **ns-dependents** (plus **ns-vars** in the official table). Design inspiration is cited from **[SBCL `sb-introspect`](http://www.sbcl.org/manual/#sb_002dintrospect)** and **Smalltalk senders/implementors**.

## Token / context reduction (clj-xref’s own source)

Upstream describes this as an **experiment**; on **clj-xref** itself, xref-guided selection reportedly lands in **1–3 files** vs **8** for the whole tree (**~66–80%** fewer characters in the sample below).

| Target | Whole-tree | Xref-guided | Reduction |
|--------|------------|-------------|-----------|
| `core/index` | 8 files, 32K chars | 1 file, 6K chars | **80%** |
| `analyze/transform-analysis` | 8 files, 32K chars | 1 file, 7K chars | **78%** |
| `leiningen.xref/xref` | 8 files, 32K chars | 3 files, 11K chars | **66%** |
| `analyze/classify-kind` | 8 files, 32K chars | 1 file, 7K chars | **78%** |

For automated measurement with Claude (tokens + quality), upstream documents **`lein measure-improvement`** (requires API key and dev profile).

## Generate

```bash
lein xref
clj -T:xref generate
```

**deps.edn** tool alias (from upstream):

```clojure
{:aliases
 {:xref {:extra-deps {com.github.danlentz/clj-xref {:mvn/version "0.1.0"}}
         :ns-default clj-xref.tool}}}
```

## Links

- [GitHub — danlentz/clj-xref](https://github.com/danlentz/clj-xref)  
- [Clojars — `com.github.danlentz/clj-xref`](https://clojars.org/com.github.danlentz/clj-xref)  
- [Sponsor clj-kondo / @borkdude](https://github.com/sponsors/borkdude)

## Credits

**[danlentz](https://github.com/danlentz)** and **[clj-xref](https://github.com/danlentz/clj-xref)** contributors. Analysis engine: **[clj-kondo](https://github.com/clj-kondo/clj-kondo)** — **[Michiel Borkent](https://github.com/borkdude)** ([sponsor](https://github.com/sponsors/borkdude)).
