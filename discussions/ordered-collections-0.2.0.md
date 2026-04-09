# `ordered-collections` 0.2.0 — ropes, ordered sets/maps, parallel folds

**[`com.dean/ordered-collections` 0.2.0](https://clojars.org/com.dean/ordered-collections)** ([GitHub](https://github.com/dco-dev/ordered-collections)) is a **JVM Clojure** library of **fast, persistent ordered structures**—including **ropes**, **ordered sets/maps**, **interval** and **range** maps, **segment trees**, **priority queues**, **multisets**, and **fuzzy** sets/maps—with **parallel** `r/fold`, **EDN** tagged literals, **Java serialization**, and **full** `java.util` / Clojure collection semantics across the **eleven** public types.

## Release highlights (from the announcement)

- **Rope** — Persistent, **vector-like** sequence for structural editing: **O(log n)** concat, split, splice, insert. Claimed **10–5000×** faster than `PersistentVector` **at scale** on the project’s benchmarks (see upstream docs for scenarios and sizes).
- **Set algebra** — Claimed **15–57×** vs `sorted-set`, **7–42×** vs `data.avl`, **3–20×** vs `hash-set` when using **parallel fork-join**; **O(log n)** positional access (`nth`, rank, median, `split-at`), floor/ceiling navigation, structure-sharing **subranges**.
- **Eleven types** — `ordered-set`, `ordered-map`, **rope**, `interval-set`, `interval-map`, `range-map`, **segment-tree**, **priority-queue**, `ordered-multiset`, `fuzzy-set`, `fuzzy-map`.
- **Cross-cutting** — Tree-based **parallel** `r/fold` on all types; **O(log n)** access patterns (rank, median, percentile, `split-key` / `split-at`); **nearest** (floor/ceiling); **EDN** round-trip for every type; **Long** / **Double**–specialized nodes for numeric workloads (**~15–25%** faster in the author’s tests).
- **Domain-specific** — **Interval** sets/maps: **O(log n + k)** overlap queries; **range maps**: non-overlapping regions with automatic carve-out (**Guava `TreeRangeMap`** semantics); **segment trees**: **O(log n)** range aggregation with any **associative** op; **fuzzy** sets/maps: nearest-neighbor by distance.

Treat published speedups as **benchmark- and workload-dependent**; validate on your own data and JVM.

## Community thread

- **Ropes elsewhere:** **[Noah Bogart](https://github.com/NoahTheDuke)** pointed **[Joshua Suskalo](https://github.com/IGJoshua)** at the project; Joshua noted his own **[ropes](https://github.com/IGJoshua/ropes)** library and welcomed the new work. **[phronmophobic](https://github.com/phronmophobic)** suggested comparing with **[bifurcan](https://github.com/phronmophobic/bifurcan)** for benchmarks and equivalence.
- **Author follow-up ([danlentz](https://github.com/danlentz)):** Bifurcan was “not on the radar” but should have been—good for **benchmarking** and **equivalence** tests; a **string-specialized** rope may land in a **future** release.
- **Editor-oriented rope use:** phronmophobic uses a **fork of Bifurcan’s rope** in the **[clobber](https://github.com/phronmophobic/clobber)** IDE. For **code editing**, slicing/indexing by **byte** (Tree-sitter), **UTF-16 code unit** (Java `String`), and **grapheme cluster** (user-visible editing) is valuable; **code point** indexing has rarely been needed in practice.
- **Open questions ([xificurC](https://github.com/xificurC)):** Which **operations** and **collection sizes** justify the “10–5000×” rope claim (e.g. is **`PersistentVector`** still faster for **small** `n`)? Any **ClojureScript** plans? (These were left as questions in the thread snapshot—check **issues** / newer releases for answers.)

## Coordinates

```clojure
com.dean/ordered-collections {:mvn/version "0.2.0"}
```

## Links

- [Clojars — `com.dean/ordered-collections`](https://clojars.org/com.dean/ordered-collections)  
- [GitHub — dco-dev/ordered-collections](https://github.com/dco-dev/ordered-collections)  
- Related: [IGJoshua/ropes](https://github.com/IGJoshua/ropes) · [phronmophobic/bifurcan](https://github.com/phronmophobic/bifurcan) · [phronmophobic/clobber](https://github.com/phronmophobic/clobber)

## Credits

Announcement and thread participants: **[danlentz](https://github.com/danlentz)**, **[Noah Bogart](https://github.com/NoahTheDuke)**, **[Joshua Suskalo](https://github.com/IGJoshua)**, **phronmophobic**, **xificurC**. Library: **[dco-dev](https://github.com/dco-dev)**.
