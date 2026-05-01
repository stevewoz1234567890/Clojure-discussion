# Lazytest — **`current-ns` macros in `.cljc`**, **shadow‑cljs `:undeclared-var`**, and **“can't take value of macro”**

**[Lazytest](https://github.com/NoahTheDuke/lazytest)** uses **`.cljc`** so **`defdescribe`** / runner plumbing can compile on **CLJ** and **CLJS**. This note summarizes a Clojurians thread where **[Noah Bogart](https://github.com/NoahTheDuke)** surfaced **shadow-cljs** warnings (**`:undeclared-var`**) that **plain ClojureScript** compilation did **not** emit—and **[Thomas Heller](https://github.com/thheller)** (**thheller**) explained how **macros** behave when they appear inside ordinary **`defn`** bodies.

Defer to **[shadow-cljs](https://github.com/thheller/shadow-cljs)** and **[Lazytest sources](https://github.com/NoahTheDuke/lazytest)** when behaviour shifts.

---

## Symptom

In **`lazytest.core`**.cljc‑style sources, a macro:

```clojure
(defmacro current-ns []
  (ns-name *ns*))
```

…was **self‑required** in the **`ns`** form (**`:require-macros`** / `:self` pattern) so **`cljs`** could see **`current-ns`** at macro expansion.

Inside a **runtime** function (e.g. **`merged-data`**) Noah wrote **`{:ns (current-ns)`** inside **`merge`**—no warning from **`cljs`** in their setup, but **shadow-cljs** reported **`Can't take value of macro lazytest.core/current-ns`** (roughly **`:undeclared-var`**). “**Take value of macro**” means the analyzer treats **`current-ns`** like a **value** (symbol / function slot) rather than a **macro invocation** cleanly expanded away (**analyzer / emitter** ordering can differ across toolchains).

---

## What **`(current-ns)` in a `defn` actually means**

**Thomas** clarified the pragmatic behaviour: **`(current-ns)`** still **macro‑expands** while **`merged-data`** is compiled, but **`(ns-name *ns*)`** is evaluated **in the namespace where the defining form lives** — almost always **`lazytest.core`** itself:

```clojure
;; effectively
:ns "lazytest.core"
```

That is seldom what you intend for **`defdescribe`** callers in **their** namespaces; it reflects **`lazytest.core`’s `*ns*`**, not **call‑site namespaces**.

---

## Better patterns (**Thomas’ guidance`)

1. **Stay in macro‑expanding surfaces** (**`defdescribe`** / **`it`** wrappers and similar): **`(ns-name *ns*)`** often matches the **calling** expansion context on CLJ; on CLJS **`(:name (:ns &env))`** from **`&env`** can expose the analogous name—contrast **`defn`** bodies, which compile once and freeze **`*ns*`** to the defining namespace unless you thread values explicitly.
2. **You cannot magically smuggle caller `*ns*` through a bare `defn`** without threading **arguments** / **macros** deliberately.
3. **CLJS rejects some JVM literals** emitted into code—e.g. **`Namespace`** objects—as **invalid compile‑time constants** (`failed compiling constant ... clojure.lang.Namespace`). Prefer **`ns-name`** (a **keyword / string‑like** datum) over leaking **`Namespace` instances** across **`#?(:clj …)`** boundaries.
4. **Isolate Clojure‑only emits** behind **`#?(:clj …)`** so **shadow-cljs** analyzer paths never traverse invalid forms.

Thomas pointed at [`lazytest/core.cljc` (main branch)](https://github.com/NoahTheDuke/lazytest/blob/main/src/clojure/lazytest/core.cljc): **`*ns*`** at macro expansion time fits APIs that remain **macros** end‑to‑end.

---

## Related community note

- **[lazytest-describe-expect-it.md](lazytest-describe-expect-it.md)** — structural **`describe` / `expect-it`** ergonomics (**different** thread).

Thanks **Noah Bogart**, **Thomas Heller**, and thread participants (**thheller** / shadow-cljs + Lazytest context).
