# `{:keys [::ns/x]}` — namespace-qualified symbols in `:keys` — community thread

This note summarizes a short exchange about **whether it is intentional** that **auto-resolved namespace symbols** may appear in **`:keys`** vectors in **map destructuring**, e.g.:

```clojure
(let [{:keys [::foo/bar]} {::foo/bar 1}] bar)
;; => 1
```

A **proposed linter rule** (thread context: discourage or forbid such keys in **`:keys`**) prompted pushback: the behavior is **deliberate** in Clojure, and **style** vs **lint policy** split the thread. Informal chat, not specification text; see official references.

---

## Intent (Clojure team)

**Alex Miller** described the behavior as **intentional** for **auto-resolved keyword key destructuring**; he favors **`::keys`** idioms wherever they clearly apply, though **`{:keys [::foo/bar]}`** can still suit **mixed-namespace** keywords in the map (his summary in-thread).

He would **not** lint this pattern by default and considers it **perfectly acceptable**.

---

## Tests in `clojure.core` vs “spec”

**lassemaatta** noted that **`clojure.core`** includes **tests for this behavior**; **Michiel Borkent** agreed that matters for tooling discussion.

Separately, **lassemaatta** warned that core **tests codify behavior** without always implying that behavior is something **to depend on** in application code—with **singleton-map-in-destructuring** context cited as another **“works in tests, odd in practice”** example (**`:slightly_smiling_face:`** in the original thread).

---

## Linters and consistency

**Noah Bogart** argued that deliberate language features could still merit an **optional linter** rule—with **good rationale** (**Borkdude** agreed in principle)—and floated **project-level configuration** listing which **`:keys` shapes are acceptable** for stylistic uniformity, **not necessarily** as the default linter behavior.

**Borkdude's** caution: tooling that encodes **too many narrowly personal preferences** can **reduce Clojure-wide stylistic coherence** as a whole.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|---------------------------|
| **Thread OP** | Asked whether **`{:keys [::foo/bar]}`** against **`{::foo/bar …}`** is by design and mentioned a linter issue that might forbid similar patterns. |
| **lassemaatta** | Pointed at **core tests**; cautioned tests ≠ always **product guidance** (**singleton‑map‑in‑destructuring** aside). |
| **Michiel Borkent** | Confirmed relevance of **core tests**; agreed deliberate features **can** be linted **with rationale**; worried overly **fine‑grained** preferences **harm** shared norms among projects. |
| **Noah Bogart** | **Opt‑in** linters vs **defaults**; **configurable** allowed **`:keys` styles**. |
| **Alex Miller** | **Intentional** semantics; **`::keys`/namespaced‑key styles** generally preferred personally; **`::foo/bar` in `:keys`** still justified for **mixed‑namespace keyword** destructuring; **would not** default‑lint against it. |

---

## See also

- Clojure reference — **[Special Forms — binding / destructuring](https://clojure.org/reference/special_forms)** (canonical behavior)
- Guide — **[Destructuring](https://clojure.org/guides/destructuring)** (readable intro)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
