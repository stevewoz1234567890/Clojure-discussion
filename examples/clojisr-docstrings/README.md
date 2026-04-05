# clojisr 1.1.0 — docstrings on `require-r`

[ClojisR](https://scicloj.github.io/clojisr/) bridges Clojure and R. **1.1.0** adds optional **docstrings** for functions brought in with `require-r`, so tools and humans can read R help text from `meta` (e.g. for `clojure.repl/doc` or editor hints).

Thanks to **[@Carsten Behring](https://github.com/behrica)** and the [scicloj/clojisr](https://github.com/scicloj/clojisr) maintainers.

## Try it

**Prerequisites:** R on your `PATH`, R package **ggplot2** installed, and clojisr’s usual backend (often **Rserve** — see the [Clojisr tutorial — setup](https://scicloj.github.io/clojisr/clojisr.v1.tutorials.main.html)).

From this directory:

```bash
clojure -M -m clojisr-example.docstrings-demo
```

Expected output (first line of R’s `ggplot` help):

```text
Create a new ggplot
```

## REPL one-liner

Equivalent to the announcement snippet:

```clojure
(require '[clojisr.v1.r :refer [require-r]]
         '[clojure.string :as str])

(require-r '[ggplot2 :as gg :docstrings? true])
(-> #'gg/ggplot meta :doc str/split-lines first)
;; => "Create a new ggplot"
```

## Coordinates

```clojure
scicloj/clojisr {:mvn/version "1.1.0"}
```

On Clojars: [scicloj/clojisr](https://clojars.org/scicloj/clojisr).
