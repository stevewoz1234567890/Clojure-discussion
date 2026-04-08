# Clojure 1.12.5-alpha1 — `reify` metadata

Minimal program showing [CLJ-2945](https://clojure.atlassian.net/browse/CLJ-2945): reader line/column metadata is not attached to reified instances; explicit metadata like `^{:a 1}` is kept.

Requires **Clojure 1.12.5-alpha1** (see `deps.edn`).

## Run

```bash
cd examples/reify-meta
clojure -M -m reify-example.main
```

Expected on **1.12.5-alpha1**:

```text
meta (reify Serializable) => nil
meta ^{:a 1} (reify Serializable) => {:a 1}
```

On older Clojure versions the first line would include `:line` / `:column` instead of `nil`.
