# Clojure 1.12.5-alpha1 — `reify` metadata, `constantly` allocation

**Clojure [1.12.5-alpha1](https://github.com/clojure/clojure/blob/master/changes.md)** is available (pre-release). Highlights from the announcement:

## [CLJ-2945](https://clojure.atlassian.net/browse/CLJ-2945) — `reify` no longer transfers reader metadata to the runtime object

**Issue:** Reader-supplied **`:line` / `:col`** metadata on the `reify` form was being attached to the **reified instance** in an unintended way.

**Change:** `reify` now aligns with **`fn`**: it does **not** transfer reader metadata to the runtime object. **User metadata** on the form (e.g. `^{:a 1}`) is still transferred, per the docstring.

**Motivation:** Smaller compiled classes and fewer runtime allocations for reified objects.

### Before (Clojure 1.12.4)

```clojure
user=> (meta (reify java.io.Serializable))
{:line 1, :column 7}
user=> (meta ^{:a 1} (reify java.io.Serializable))
{:line 1, :column 7, :a 1}
```

### After (1.12.5-alpha1)

```clojure
user=> (meta (reify java.io.Serializable))
nil
user=> (meta ^{:a 1} (reify java.io.Serializable))
{:a 1}
```

The core team asked for feedback from anyone building **editors**, **linters**, or other tools that might have depended on the old behavior. Early responses suggested low impact for common tooling.

## [CLJ-2228](https://clojure.atlassian.net/browse/CLJ-2228) — `constantly` — unroll to avoid rest-args allocation

`constantly` is implemented so that the common case does not allocate the **rest-args** array (implementation detail; see the ticket).

## Dependency coordinates

```clojure
org.clojure/clojure {:mvn/version "1.12.5-alpha1"}
```

## Runnable example in this repo

**[examples/reify-meta](../examples/reify-meta/)** — `clojure -M -m reify-example.main` prints `meta` for plain and annotated `reify` forms (uses **1.12.5-alpha1**).

## Community notes

- **Eric Dallo** ([@ericdallo](https://github.com/ericdallo)): no recollection of **clojure-lsp** or **clj-kondo** relying on `reify` carrying that reader metadata in a way that would break.  
- **Sean Corfield** ([@seancorfield](https://github.com/seancorfield)): no regressions in an internal test suite; planning to run on QA to surface anything unexpected (expected unlikely).

## Links

- [Clojure changelog / release notes](https://github.com/clojure/clojure/blob/master/changes.md) (authoritative version history)  
- [CLJ-2945](https://clojure.atlassian.net/browse/CLJ-2945) · [CLJ-2228](https://clojure.atlassian.net/browse/CLJ-2228)  
- [Clojars — org.clojure/clojure](https://clojars.org/org.clojure/clojure)  
