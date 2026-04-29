# Rama replication docs — “two workers” vs diagram, and **`workers × replication factor`**

This note summarizes a short **community thread** about **[Red Planet Labs / Rama replication documentation](https://redplanetlabs.com/docs/~/replication.html#_how_data_is_replicated)**: prose under the diagram described **sixteen tasks, eight threads, two workers, replication factor two**, while readers saw **four** worker-like blocks—**matching** **`2 configured workers × 2 replication = 4` processes**, not contradicting arithmetic once **`replication`** is included.

Informal synthesis; authoritative behavior is **[Rama docs](https://redplanetlabs.com/)** plus your cluster config—not this note.

---

## What confused readers

Caption paraphrase: *“sixteen tasks, eight threads, two workers, replication factor two …”*

If **workers** means only **configured** count (forgetting **replication**), **four** boxed processes in the figure looks inconsistent; multiplying **configured workers × replication factor** (**2 × 2 = 4**) matches the visualization.

**Nathan Marz** ([**@nathanmarz**](https://github.com/nathanmarz)) clarified in-thread:

1. **Number of worker processes actually launched** = **configured workers × replication factor**.
2. The **physical thread count likewise scales × replication factor** (mirroring that expansion).

So **“two”** in prose was **configured** worker count alongside **`replication`** **factor two**, not implying only **two JVM-style worker processes cluster-wide**.

---

## Task / thread intuition ([**hlship**](https://github.com/hlship))

**Howard Lewis Ship** ([**@hlship**](https://github.com/hlship)) restated the task model (see **[task model](https://redplanetlabs.com/docs/~/tutorial3.html#task-model)**):

- A **worker** ⇢ typically **one process** somewhere in the mesh.
- It runs **a fixed pool of threads**; **tasks** **partition evenly** → **task groups**.
- Each **thread** serves **event-queue** plumbing for **its** partition’s workload.

The tutorial’s **[task-model section](https://redplanetlabs.com/docs/~/tutorial3.html#task-model)** felt sparse to **@hlship** on **replication × workers** at first; **`replication.html`** already spells out that **launched** workers follow **configured workers × replication factor**—easy to read past when tired.

---

## Links

- [Replication — how data is replicated](https://redplanetlabs.com/docs/~/replication.html#_how_data_is_replicated) (**diagram**, worker scaling prose)
- [Tutorial — task model](https://redplanetlabs.com/docs/~/tutorial3.html#task-model)

---

## Credits (thread voices)

**[@nathanmarz](https://github.com/nathanmarz)** · **[@hlship](https://github.com/hlship)** · original asker on Clojurians.

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
