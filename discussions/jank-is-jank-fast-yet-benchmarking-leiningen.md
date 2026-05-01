# jank — **is-jank-fast-yet** public benchmarks (**Kyle Cesare**, **Jeaye**, Leiningen vs **`clojure` CLI**)

**[kylc/is-jank-fast-yet](https://github.com/kylc/is-jank-fast-yet)** — live Quarto-rendered comparisons: **[kylc.github.io/is-jank-fast-yet/](https://kylc.github.io/is-jank-fast-yet/)** (maintainer **[Kyle Cesare](https://github.com/kylc)** / **`kylc`**; thread voiced alongside **Jeaye**, Jank creator).

Informal recap of a slack thread (**Clojurians / `#jank`** lineage):

## Runner & flags

**Hardware** (publisher): **Intel i3-8100**, **8 GiB RAM**. Results are regenerated on **one local box** (“as long as the suite doesn’t get too huge”), not pitched as CI-stable yet—**Jeaye** flagged future **CI/stable machine** ergonomics separately.

For Jank publishes, rerun with **`jank -O3 --eagerness eager`** (use **`jank --help`** on your build—the flag spellings have evolved alongside the optimiser knobs).

Built Jank artifact for the harness: **`nix`/cache-backed** **`jank-lang`** package on the reporter side.

## Methodology / UX

Harmonised harness across dialects (**Criterium** for Clojure JVM, **`jank.perf/benchmark`** for Jank)—**Jeaye**:

```text
(jank.perf/benchmark {:label "something"}
  (do-work-son))
```

**Kyle Cesare** asked for **`jank.perf`** to optionally return **statistics as data** (like **criterium**’s richer return path), not only pretty-print—**Jeaye** agreed to approximate the same **[criterium `benchmark`/`quick-bench` data shape](https://github.com/hugoduncan/criterium/blob/cf1c70fa765dae2c05b7b33746b8775e6d15aa78/src/criterium/core.clj#L491-L499)** where practical.

Plots link out to benchmark **source** (**title/header links**) per suggestion.

Dialect toggles/global visibility for extra languages (**Scheme**, etc.) was floated; **Jeaye** opted out of widening scope personally; Kyle remains open to small PRs that keep parity via **reader-conditional deltas**.

## Recursive **fib** caveat

Naïve **`fib`** (mutual recursion, **no** **`recur`** / linear impl) called **`fib_naive`** is acknowledged as **not representative** workloads—yet **Jeaye** is actively optimising exactly that hotspot as one microbenchmark; **Jeaye** floated **macro‑scale** workloads next: a **ray tracer** with **paired Jank and Clojure** versions, **`ruuter`** (**Clojure routing**, Jank‑supported benchmarks already exist elsewhere), **`cljcc`** (C compiler implemented in Clojure; **Jank** port pending)—anything stressing **maps**, **vectors**, **keywords**, **GC**, not only numeric kernels. **`babashka`** compatibility where possible was hoped for portability.

Sleep-based joke bench gave Jank apparent bragging rights in-thread.

## ⚠ **`lein repl`** vs **`clojure`** distorts **`quick-bench`**

Huge apparent mismatch (**~260 ms vs ~10–25 ms** class spread for **`(fibonacci 30)`** with identical **`fib.jank`**) boiled down **not** to ARM vs **x86_64**, **Clojure 1.12.2** vs **1.12.4**, or “page vs local”, but **`lein repl` default JVM ergonomics**:

- **`lein repl`** path produced **multi-hundred‑ms** means for Jeaye/Kyle probes.
- Plain **`clojure -Sdeps … criterium`** produced **single‑digit to low‑tens of ms** on the same forms.
- **Kyle** linked classic thread: **[Leiningen #1149 — JVM opts kneecap JIT when benchmarking from `lein repl`](https://github.com/technomancy/leiningen/issues/1149#issuecomment-16596462)**.

**Takeaway:** compare languages with **equivalent launchers**; treat Leiningen REPL numbers as **optimisation-off** unless you override profile/JVM flags.

## Links

- [kylc/is-jank-fast-yet](https://github.com/kylc/is-jank-fast-yet) · [site](https://kylc.github.io/is-jank-fast-yet/)  
- [jank-lang/jank](https://github.com/jank-lang/jank)  
- [criterium](https://github.com/hugoduncan/criterium) · [Leiningen #1149](https://github.com/technomancy/leiningen/issues/1149#issuecomment-16596462)  

Thanks to **[Kyle Cesare](https://github.com/kylc)** and **[Jeaye](https://github.com/jeaye)**.
