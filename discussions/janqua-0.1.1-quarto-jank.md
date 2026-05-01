# Janqua 0.1.1 — Quarto literate notebooks for **[Jank](https://jank-lang.org)**

**[Janqua](https://github.com/scicloj/janqua)** is a **[Quarto](https://quarto.org)** extension for evaluating **[Jank](https://jank-lang.org)** in **visual documents**. The first published bundle is version **0.1.1** (**[`scicloj/janqua`](https://github.com/scicloj/janqua)** `_extensions/jank/_extension.yml`).

## What it does

During **`quarto render`** / **`quarto preview`**, each fenced **`{.clojure .jank}`** block inside a **`.qmd`** file runs against a **Jank nREPL** (started on first use, reused for faster re-render). Expressions can produce plain output, structured results, HTML, charts, diagrams, tables, etc.

Upstream highlights from the README and announcement:

| Output family | Engines named |
|---------------|---------------|
| **Charts** | **Plotly**, **Vega-Lite**, **ECharts**, **Cytoscape**, **Highcharts** |
| **Diagrams** | **Mermaid**, **Graphviz** |

Rendering aligns with the **[Kindly](https://scicloj.github.io/kindly-noted/)** convention—same surface area **[Clay](https://scicloj.github.io/clay/)** popularized for **Clojure** notebooks—so **Clay** workflows port mentally, while standalone **Quarto** authors get publishable docs without reinventing toolchain glue.

[**Project documentation**](https://scicloj.github.io/janqua) · **`quarto add scicloj/janqua`**

## Example (Kindly + Plotly from Jank)

The screenshot matches the canonical Quadratic **`^:kind/plotly`** pattern: generate **`xs`** / **`ys`**, attach **`^:kind/plotly`**, return a Plotly-compatible map (**Quadratic from Jank**, parabola **y = x² − 0.5x** over stepped **x**).

![Janqua-rendered Quarto snippet: `^:kind/plotly` quadratic chart titled “Quadratic from Jank”](images/janqua-0.1.1-kindly-plotly-quadratic.png)

## Prerequisites (upstream)

[**Quarto**](https://quarto.org/docs/get-started/), [**Jank**](https://jank-lang.org/), [**Babashka**](https://github.com/babashka/babashka#installation) + [**bbin**](https://github.com/babashka/bbin#installation), and **`clj-nrepl-eval`** from **[clojure-mcp-light](https://github.com/bhauman/clojure-mcp-light)** (`bb install` — see README for pinned tag / `--main-opts`).

Upstream marks the project **experimental** (currently **Linux-tested**; **macOS** unverified) and welcomes feedback via **GitHub issues** or **[Scicloj community chat](https://scicloj.github.io/docs/community/chat/)**.

## Clojure Civitas angle

[**Clojure Civitas](https://clojurecivitas.org/)** (**[repo](https://github.com/ClojureCivitas/clojurecivitas.github.io)**) hosts shared **Clay + Quarto** posts; Janqua is positioned as bringing the same publishing story to **Jank** notebooks and widening what can ship in that communal space once workflows land.

---

## Reception (informal thread)

Responses included praise from **[jeaye](https://github.com/jeaye)** (Jank creator) and **Steven Lombardi**.

Thanks **[Scicloj](https://scicloj.github.io)** / Janqua authors and early readers on the announcement thread.
