# clj-kondo #2807 — suggesting `cond->` for repeated `let`/`if`/`assoc` map build-up — community thread

**Upstream:** [Issue #2807 — “New linter: conditional build-up”](https://github.com/clj-kondo/clj-kondo/issues/2807): detect **`let`** blocks where **the same binding** is repeatedly rewritten with **`(if pred (assoc sym …) sym)`** and propose **`cond->`** (**`assoc`** threading) instead. Maintainer feedback favors **`:info`**-style severity (**cosmetic**) and accounting for **`#?(:clj …)`** reader conditionals during implementation.

This note summarizes a short **Slack/Clojurians exchange** reacting to **`cond->`**-style suggestions, not the issue text alone. Informal synthesis; defer to **`clj-kondo`** maintainers for shipping behavior.

---

## What the linter targets (conceptual)

Roughly the refactor from successive rebinding:

```clojure
(defn foo [in]
  (let [m {:k0 (f0 in)}
        m (if (p1 in) (assoc m :k1 (f1 in)) m)
        m (if (p2 in) (assoc m :k2 (f2 in)) m)]
    m))
```

toward:

```clojure
(defn foo [in]
  (cond-> {:k0 (f0 in)}
    (p1 in) (assoc :k1 (f1 in))
    (p2 in) (assoc :k2 (f2 in))))
```

Real-world cites in-thread (and expanded in-issue) include **`overtone`** ([`handlers.clj#L45`](https://github.com/overtone/overtone/blob/master/src/overtone/libs/handlers.clj#L45)), **`babashka`** CircleCI codegen ([`gen_ci.clj#L134`](https://github.com/babashka/babashka/blob/master/.circleci/script/gen_ci.clj#L134)), and others.

---

## Reactions — when the nudge shines vs when **`let`** stays clearer

### Broadly positive toward the linter

**[@seancorfield](https://github.com/seancorfield)** would welcome the suggestion **if it targets precisely** the sequence of **conditional bindings** assembling **one map**—not an over-broad rewrite of unrelated **`let`** steps (after checking the GitHub discussion).

**Harold:** the **“same binding, repeated optional `assoc`”** shape was exactly what led him to **`cond->`** earlier (via someone else’s review); a gentle **`clj-kondo`** hint would help others similarly.

### Nuance — not every **`if`/map spine** should become **`cond->`**

**[@juhoteperi](https://github.com/juhoteperi):** **[Overtone](https://github.com/overtone/overtone)** illustrates a clear **`cond->`** candidate, yet he has retained **`let`** + **`if`** where **additional logic lives between branches**—e.g. **`yaml/yamlscript`** [`cli.clj` around binding lines **565–576**](https://github.com/yaml/yamlscript/blob/v0/ys/src/yamlscript/cli.clj#L566) (**auxiliary binds** wedged inside the **`let`**). **`GokuRakuJoudo`** [`conditions.clj` **L40**](https://github.com/yqrashawn/GokuRakuJoudo/blob/master/src/karabiner_configurator/conditions.clj#L40) repeats the pattern (**`rst`**, **`result`** in an outer **`for`** **`:let`**), and **`for`** **`:let`** areas with lots of incidental bindings may still favor **explicit `if`s** even amid several near-adjacent branches (**L63–89** cited). **re-frame-10x** was another cite where ancillary **`let`** content made plain branching preferable in his reading.

So: **narrow pattern matching** aligns with practitioner instinct—pure “stack optional keys on one map symbol” differs from **`let`**s that weave **checks with side computations**.

### Two refactor geometries

**imre** distinguishes:

1. Rewrite each **`(if … (assoc …) …)`** limb toward **`cond->`** while keeping **intermediate `let`** bindings where needed; or  
2. A **single `cond->` chain** when the whole step sequence is local enough.

Either can beat the status quo; an **`if`** that returns either the unchanged value **`or`** the **`assoc`**’d update of that value often lends itself to **`cond->`** (or **`cond->>`** when the “thread position” warrants it).

---

## Who said what (names as in-thread)

| Name | Contribution (summary) |
|------|---------------------------|
| **[@borkdude](https://github.com/borkdude)** | Pointed threads at **`overtone`**, **`babashka`** examples and **#2807** (issue proposes **`:conditional-build-up`**; `:info`; reader-conditionals caveat). |
| **[@juhoteperi](https://github.com/juhoteperi)** | Overtone = clear **`cond->`** fit; keep **`let`/`if`** when **bindings between** predicates matter (**yamlscript**, **`Goku`** **`for`** **`:let`**). |
| **[@seancorfield](https://github.com/seancorfield)** | **Yes**, if rewriter stays scoped to **multi-step conditional map assembly** (**map-building** **`let`** vein). |
| **Harold** | Personal learning path via same pattern ⇒ **`cond->`**; positive on **education-by-linter** for repetition on **`m`**. |
| **imre** | **Two refactor geometries** (**piecemeal `cond->` vs single chain**) — both situational upgrades over noisy **`if`** / rebinding patterns. |

---

## See also

- [clj-kondo/clj-kondo issue #2807](https://github.com/clj-kondo/clj-kondo/issues/2807)
- Clojure guide — [Threading macros](https://clojure.org/guides/threading_macros#_conditional_threading) (**`cond->`** and **`cond->>**)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
