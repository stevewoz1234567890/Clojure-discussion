# clj-kondo v2026.04.15 — six years, new linters, potemkin, performance

**[clj-kondo](https://github.com/clj-kondo/clj-kondo)** ([Michiel Borkent / @borkdude](https://github.com/borkdude)) is a **static analyzer and linter** for **Clojure**, **ClojureScript**, and related dialects. **[v2026.04.15](https://github.com/clj-kondo/clj-kondo/releases/tag/v2026.04.15)** marks **six years** of the project. This release adds **new linters**, better **potemkin** support, and many **performance** improvements. Thanks to [@jramosg](https://github.com/jramosg), [@alexander-yakushev](https://github.com/alexander-yakushev), [@harryzcy](https://github.com/harryzcy), [@walterl](https://github.com/walterl), [@hugoduncan](https://github.com/hugoduncan), other contributors, and sponsors ([Clojurists Together](https://www.clojuriststogether.org/), GitHub Sponsors, and everyone who keeps Clojure OSS sustainable).

## Highlights (from the announcement / changelog)

### New linters

| Linter | Default | What it does |
|--------|---------|--------------|
| [`:not-nil?`](https://github.com/clj-kondo/clj-kondo/pull/2789) | **`:off`** | Prefer idiomatic checks such as `(some? x)` over `(not (nil? x))`, including under `when-not` / `if-not`. |
| [`:protocol-method-arity-mismatch`](https://github.com/clj-kondo/clj-kondo/pull/2804) | (on) | Warns when a protocol implementation’s arity does not match **any** arity declared on the protocol. |
| [`:missing-protocol-method-arity`](https://github.com/clj-kondo/clj-kondo/pull/2804) | **`:off`** | Warns when a method is implemented but **not all** declared arities are covered. |
| [`:redundant-declare`](https://github.com/clj-kondo/clj-kondo/pull/2769) | (on) | Warns when `declare` appears **after** a var is already defined. |

### Tooling & libraries

- **[#1878](https://github.com/clj-kondo/clj-kondo/pull/2806)** — Understands **potemkin** `import-fn`, `import-macro`, and `import-def` so imported vars resolve for linting and navigation.
- **[#2498](https://github.com/clj-kondo/clj-kondo/pull/2805)** — Understands **potemkin** `import-vars` with **`:refer`** and **`:rename`** (require-style vectors).
- **[#2579](https://github.com/clj-kondo/clj-kondo/issues/2579)** — Avoids redundant config merging when switching namespaces (faster analysis).

### Correctness & types

- **[#2762](https://github.com/clj-kondo/clj-kondo/pull/2763)** — **CLJS**: `throw` with a **string** no longer triggers a misleading type-mismatch warning.
- **Types** — Better support for **`pmap`** (including arity) and **future**-related core functions (`future`, `future-call`, `future-done?`, `future-cancel`, `future-cancelled?`).

### Reliability & packaging

- **[#2770](https://github.com/clj-kondo/clj-kondo/pull/2771)** — Linter-specific ignores for **`:unused-excluded-var`** and **`:unresolved-excluded-var`** now respect the listed linters instead of suppressing everything.
- **[#2773](https://github.com/clj-kondo/clj-kondo/pull/2773)** — Container images expose the binary at **`/bin/clj-kondo`**.
- **[#2621](https://github.com/clj-kondo/clj-kondo/pull/2797)** — Config discovery follows **symlinked** config directories when globbing imports.
- **[#2798](https://github.com/clj-kondo/clj-kondo/pull/2799)** — **StackOverflowError** during analysis reports the correct **filename** and error context.
- **Hooks** — Caches **hook-fn** lookups to cut repeated **SCI** evaluation.

### Performance

Refactors and micro-optimizations across the analyzer and rewrite path (for example [#2759](https://github.com/clj-kondo/clj-kondo/pull/2759) on `lint-cond-constants!`, and work by [@alexander-yakushev](https://github.com/alexander-yakushev) in [#2779](https://github.com/clj-kondo/clj-kondo/pull/2779), [#2780](https://github.com/clj-kondo/clj-kondo/pull/2780), [#2781](https://github.com/clj-kondo/clj-kondo/pull/2781), [#2782](https://github.com/clj-kondo/clj-kondo/pull/2782), [#2783](https://github.com/clj-kondo/clj-kondo/pull/2783), [#2785](https://github.com/clj-kondo/clj-kondo/pull/2785), [#2786](https://github.com/clj-kondo/clj-kondo/pull/2786), [#2794](https://github.com/clj-kondo/clj-kondo/pull/2794), [#2800](https://github.com/clj-kondo/clj-kondo/pull/2800), [#2801](https://github.com/clj-kondo/clj-kondo/pull/2801)).

Other merged work includes extracting var definitions from **AOT-only** jars ([#2793](https://github.com/clj-kondo/clj-kondo/pull/2793) [@hugoduncan](https://github.com/hugoduncan)).

---

## Code examples

### `:not-nil?` — use `some?` (linter default **`:off`**)

```clojure
;; clj-kondo may suggest simplifying when :not-nil? is enabled
(defn process [x]
  (when-not (nil? x)
    (str "ok " x)))

;; idiomatic equivalent
(defn process [x]
  (when (some? x)
    (str "ok " x)))

;; same idea with if-not
(defn label [y]
  (if-not (nil? y)
    (name y)
    "—"))

(defn label [y]
  (if (some? y)
    (name y)
    "—"))
```

Enable in **`.clj-kondo/config.edn`**:

```clojure
{:linters {:not-nil? {:level :info}}}
```

### `:protocol-method-arity-mismatch` — implementation arity not in the protocol

```clojure
(defprotocol Fetch
  (pull [this id]))

(defrecord BadClient []
  Fetch
  ;; protocol only defines [this id] — this 3-arg form does not match
  (pull [this id opts]
    nil))
```

### `:missing-protocol-method-arity` — not every declared arity implemented (default **`:off`**)

```clojure
(defprotocol Describe
  (label [this] [this locale]))

(defrecord Item [name]
  Describe
  ;; implements only the 1-arg version; 2-arg [this locale] is missing
  (label [this]
    name))
```

### `:redundant-declare` — `declare` after the var exists

```clojure
(defn api-version [] "1.0")

;; redundant: api-version is already defined above
(declare api-version)
```

### potemkin — `import-fn`, `import-macro`, `import-def`

```clojure
(ns my.api
  (:require [potemkin :refer [import-fn import-macro import-def]]))

(import-fn other.impl/foo)
(import-macro other.impl/with-resource)
(import-def other.impl/DEFAULT-TIMEOUT)
```

After this release, clj-kondo can treat these like real definitions for unresolved-symbol and related checks (subject to your hooks and classpath).

### potemkin — `import-vars` with **`:refer`** and **`:rename`**

```clojure
(ns my.walk-bridge
  (:require [potemkin :refer [import-vars]]))

(import-vars
  [clojure.walk :refer [prewalk postwalk]]
  [clojure.string :refer [join split] :rename {join str-join}])
```

---

## Install / upgrade

- **CLI / CI**: see [installation](https://github.com/clj-kondo/clj-kondo/blob/master/doc/install.md) and [release assets](https://github.com/clj-kondo/clj-kondo/releases/tag/v2026.04.15) (standalone JAR, native builds, **LSP** server JAR).
- **Editor**: bump your **clojure-lsp** / editor plugin bundle if it pins a clj-kondo version.

## Links

- [GitHub — clj-kondo/clj-kondo](https://github.com/clj-kondo/clj-kondo)  
- [Release v2026.04.15](https://github.com/clj-kondo/clj-kondo/releases/tag/v2026.04.15) · [Compare v2026.01.19…v2026.04.15](https://github.com/clj-kondo/clj-kondo/compare/v2026.01.19...v2026.04.15) · [CHANGELOG](https://github.com/clj-kondo/clj-kondo/blob/master/CHANGELOG.md)  
- [Sponsor @borkdude](https://github.com/sponsors/borkdude)

## Credits

**[clj-kondo](https://github.com/clj-kondo/clj-kondo)** maintainers, contributors, and sponsors; announcement summarized for **[community notes](../README.md#community-notes)**.
