# clojisr 1.1.0 — docstrings for required R functions

**[ClojisR](https://scicloj.github.io/clojisr/)** ([`scicloj/clojisr`](https://github.com/scicloj/clojisr)) is the main **Clojure ↔ R** interoperability library in the SciCloj ecosystem: run R from the JVM, convert data, and treat many R functions as ordinary Clojure functions.

## What’s new in 1.1.0

You can attach **docstrings** to vars created by `require-r`, so R help text surfaces in Clojure metadata (documentation in the REPL, editor integration, etc.).

```clojure
(require '[clojisr.v1.r :refer [require-r]]
         '[clojure.string :as str])

(require-r '[ggplot2 :as gg :docstrings? true])
(-> #'gg/ggplot meta :doc str/split-lines first)
;; => "Create a new ggplot"
```

The `:docstrings? true` option is the new piece; the rest matches existing `require-r` syntax (see [§2.8 Requiring R packages](https://scicloj.github.io/clojisr/clojisr.v1.tutorials.main.html) in the official tutorial).

## Credits

Thanks to **[Carsten Behring](https://github.com/behrica)** for this release and to everyone maintaining clojisr.

## Runnable example in this repo

See **[examples/clojisr-docstrings](../examples/clojisr-docstrings/)** (`deps.edn` + `-main` demo). You still need **R**, **ggplot2**, and a working clojisr/R session (often **Rserve**) on your machine.

## Links

- [ClojisR documentation](https://scicloj.github.io/clojisr/)
- [GitHub — scicloj/clojisr](https://github.com/scicloj/clojisr)
- [Clojars — scicloj/clojisr](https://clojars.org/scicloj/clojisr) (includes **1.1.0**)
