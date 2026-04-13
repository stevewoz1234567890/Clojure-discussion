# Raster 0.1.1 — Fast numerical computing for Clojure

**[Raster](https://github.com/replikativ/raster)** ([replikativ](https://github.com/replikativ)) is a **JVM Clojure** library for **numerical and scientific** work. **[v0.1.1](https://github.com/replikativ/raster/releases)** is the **first public release**: write math with **`deftm`** (typed multi), aim for **Julia / JAX–class** performance while keeping **full REPL** interactivity.

**Coordinates:** [`org.replikativ/raster`](https://clojars.org/org.replikativ/raster) on Clojars.

## Release highlights (from the announcement)

### Core ideas

- **`deftm`** — **Typed multiple dispatch** (Julia-inspired). The compiler **resolves dispatch at definition time**, emits **JVM bytecode**, and **fuses array operations** across function boundaries.
- **`raster.par`** — **`par/map`**, **`par/reduce`**, and **`par/scan`** (Futhark-inspired). The compiler treats them as **first-class IR**, applies **SOAC fusion** on producer–consumer chains, and lowers to **SIMD** loops or **GPU** kernels from the **same** source.
- **`compile-aot`** — Can inline a whole call chain (e.g. forward pass, **reverse-mode AD**, optimizer update) into **one** JVM method, with **no heap allocations** on the hot path (as described upstream).

### Example: typed `relu` on a double array

```clojure
(deftm relu [xs :- (Array double)] :- (Array double)
  (par/map [i (alength xs)]
    (Math/max 0.0 (aget xs i))))
```

### Published benchmarks (upstream)

On **Valhalla JDK 27**, **single-threaded CPU** (unless noted otherwise on upstream docs):

| Workload | Raster | Reference |
|----------|--------|-----------|
| ODE solve (DP5 Lorenz) | 432 µs | Julia ~583 µs — **1.4× faster** |
| LeNet-5 train step (f32) | 148 µs | JAX ~356 µs — **2.4× faster** |
| LeNet-5 train step (f64) | 222 µs | JAX ~370 µs — **1.7× faster** |
| MLP train step (f64) | 136 µs | JAX ~86 µs — **JAX ~1.6× faster** |
| AD sensitivity (Lotka–Volterra) | 15 µs | Julia ForwardDiff ~16 µs — **1.1× faster** |

Deep-learning rows are described upstream as the **full compiled** path: **forward**, **backward** (reverse-mode AD), and **SGD** update.

Treat numbers as **benchmark- and environment-dependent**; confirm on your JDK, CPU, and workload.

### What’s included (high level)

- Typed multiple dispatch with **method specialization**
- **Forward-** and **reverse-mode** automatic differentiation
- **Nanopass** compiler: SOAC fusion, buffer fusion, **SIMD** vectorization
- **ODE / PDE / SDE** solvers (Euler, RK4, DP5, Tsit5, Rosenbrock, etc.)
- **Optimization** (L-BFGS, Nelder–Mead, Newton)
- **Linear algebra** with **LAPACK** via **Panama FFM** (SVD, QR, eigendecomposition, Krylov, etc.)
- **Deep learning** layers (linear, conv1d/2d, attention, normalization) with **compiled** training
- **GPU** backends (OpenCL, Level Zero, Vulkan compute)
- **Symbolic** computation, **geometric algebra**, **agent-based** simulation
- **Interactive notebooks** (Clay / Kindly)

## Try it

```bash
git clone https://github.com/replikativ/raster.git
cd raster && clojure -M:nREPL
```

Then open upstream notebooks (getting started, autodiff, ODE solvers, linear algebra, optimization, deep learning) under the repo’s `notebooks/` tree.

**Requirements:** **JDK 24+** (ClassFile API, Panama FFM). **Valhalla JDK 27** recommended upstream for best performance.

## Links

- [GitHub — replikativ/raster](https://github.com/replikativ/raster)  
- [Docs / notebooks — replikativ.github.io/raster](https://replikativ.github.io/raster/)  
- [Clojars — `org.replikativ/raster`](https://clojars.org/org.replikativ/raster)  
- Clojurians Slack: **`#simmis`**

## Coordinates

```clojure
org.replikativ/raster {:mvn/version "0.1.1"}
```

## Credits

Announcement: **[replikativ/raster](https://github.com/replikativ/raster)** maintainers and community. Feedback and issues welcome on GitHub.
