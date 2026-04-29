# `sqlatom` 1.2.0 — faster serdes — and EDN **writing** after **`fast-edn` reads**

[**`sqlatom`**](https://github.com/filipesilva/sqlatom) (SQLite-backed durable refs for Clojure), **maintained by [@filipesilva](https://github.com/filipesilva)** — announced **version 1.2.0** with **faster serialization/deserialization** on the persistence path (**EDN**).

Reported on a **~20 MB EDN** payload (author’s figures, not independently verified here):

| Op | Before | After |
|----|--------|--------|
| **`reset!`** | ~314 ms | ~76 ms |
| **`swap!`** | ~649 ms | ~140 ms |

The project already depends on **[`io.github.tonsky/fast-edn`](https://github.com/tonsky/fast-edn)** for **reading** EDN (`deps.edn` on **`master`**: **`fast-edn`** **`1.1.3`**). **1.2.0** pushes that serde story forward on persistence.

**Thanks** from the maintainer to helpful **Clojurians** in **#clojure** for tips around the release — see also this **[Slack permalink](https://clojurians.slack.com/archives/C03S1KBA2/p1777375536274579)**.

---

## Open thread: faster **writer** than **`pr-str`** with **`*print-meta*`**?

The maintainer asked whether an analogue to **`fast-edn`** exists for **emitting** EDN—keeping **`*print-meta* true`** semantics and **tagged literals** for **round trips**, along the lines of:

```clojure
(defn- pr-str-meta [v]
  (binding [*print-meta* true]
    (pr-str v)))
```

No single **`fast-write`** surfaced as unanimous in the excerpt captured here; worth watching **#clojure** or upstream **release notes**.

---

## Git tag **`v1.2.0`**

**neumann** (in-thread) noted **`v1.2.0`** was **not visible** among [Git **`tags`**](https://github.com/filipesilva/sqlatom/tags) when checked (only **`v1.1.0`** / **`v1.0.0`** listed). Pin **`:git/sha`** / **`deps`** coordinates per maintainer guidance until a **`v1.2.0`** tag exists.

---

## Links

- [filipesilva/sqlatom](https://github.com/filipesilva/sqlatom)  
- [tonsky/fast-edn](https://github.com/tonsky/fast-edn)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
