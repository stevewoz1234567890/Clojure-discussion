# JSON in Clojure — nested lookups, `for`, `get-in`, and membership — community thread

This note summarizes a short exchange about **navigating nested maps** parsed from **JSON**, **readability** when code reads “inside-out,” and idioms (**`some`**, **`get-in`**, **`for`**) that avoid hand-rolled **`member`** and fragile **`first`/`filter`** chains. Informal chat, not authoritative guidance.

---

## The shape of the problem

Given parsed JSON (`get-json-data` produces Clojure data), one participant wanted **`get-closing-date`** to descend **assignments** → find by **assignment slug** and **login** in **subscriptions** → locate a **submission** whose **`tagPrefix`** matches → read **`closingAt`** from **`dates`**, with recursion for **`late-`–prefixed** assignment names.

Early code used **`filter`/`first`** nested deeply; readers noted that **labeled `let`** steps or **`for`** with **guard clauses** read more clearly than reversing the nesting in the outer form.

---

## Themes

### “Find first satisfying a predicate”

Several messages circled **`filter` → `first`**: there is **`some`** for “first truthy predicate result,” and **no generic `find-first`** in **`clojure.core`** by design—the core team prefers data structures that support **fast lookup** over making **O(n) linear scans** a default habit (contrast **JavaScript `indexOf`**-heavy style).

### Refactors that read forward

Suggestions included:

1. Extract **“find first item matching pred”** as a named helper (or rely on **`some`** semantics where appropriate).
2. **Bind intermediates with `let`** so the narrative runs top-to-bottom instead of **`(:a (:b (:c ...))))`**.
3. For keyword paths through maps, prefer **`get-in`** **`[m k1 k2 k3]`** or the threading macro **`-> m :k3 :k2 :k1`** (when keys are keywords), instead of **`(:k1 (:k2 (:k3 …)))`** read inside-out.

### A correctness caveat: chained **`first`** on layered collections

Suppose you **first** narrow to a matching **assignment**, **then** take **`first`** of **submissions** under that assignment. If multiple top-level siblings could match separately, you might **miss** an inner hit that lives **under** a **later** sibling whose outer row “matched” earlier but contained **empty** inner lists.

Rough sketch of the hazard:

```clojure
[{:matches? true  :items []}
 {:matches? true  :items [{:matches? true}]}]
```

Depending on traversal, “first match everywhere” semantics may **not** be “search every subtree.” If the **domain** guarantees **at most one** path (participant’s belief about their JSON), documenting that via **`assert`** or a **`the-only!`** helper makes the invariant obvious to reviewers.

### **`for`** comprehension with guards

A sketch using **`for`**. Bind each **`submission`** from **`(:submissions as)`** in the **`for`** vector (`submission (:submissions as)`), not **` :let`** if you meant “loop over submissions” (adjust if **`submissions`** is a single map in your schema):

```clojure
(first
 (for [as (:assignments (get-json-data json-file-name))
       :when (= assignment (:slug as))
       :when (some #{login} (:logins (:subscriptions as)))
       submission (:submissions as)
       :when (= (str assignment "-") (:tagPrefix submission))]
   (get-in submission [:dates :closingAt])))
```

Adjust predicates to match the real **`submissions`** shape (scalar vs seq); the thread debated **`member`** vs **`some`**.

### **`first-and-only`** / **`the-only!`**

Naming a helper that asserts **exactly one** match is reasonable; **`!`** often signals **effects or failure** (**`throw`**), **not necessarily** **`swap!`**-style mutation—see **`io!`**, **`run!`**, **`volatile!`**.

### Replacing **`member`** for JSON-derived data

A custom **`member`** used **`reduce`** to **short-circuit** (avoiding **`filter`** that might scan further). **`some`** with **`#(= % target)`** (or **`(partial = target)`**) also short-circuits once a match exists.

For **`set?`**, **`contains?`** is appropriate; **`contains? (set seq)`** on large sequences allocates and was called **extra work** compared to **`some`** on the original collection.

Aside: **`contains?`** is **not** “element in sequential collection” semantics on vectors—that’s **`some`** **`#{x}`** or **`=**.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|---------------------------|
| **Jim Newton** | Original **`get-closing-date`** nesting; **`member`** rationale (expensive predicates, short-circuit); **`for`** draft; **`late-`** recursion; **`!`** semantics question; asked for clarification on **“wrong”** search order. |
| **p-himik** | Orthogonality of JSON vs Clojure data; refactor with **`let`/labels`; **`some`** vs heavy **`member`**; **`first`**/**`filter`** caveat with sibling structures; **`get-in`** / **`->`** preference; **`!`** meaning **effects** broadly; **`the-only!`** over **`assert`**. |
| **noisesmith** | **`member`** → **`contains?`** **`(set items)`** **`target`** (when semantics apply). |

---

## See also

- [`clojure.core/get-in`](https://clojure.github.io/clojure/clojure.core-api.html#clojure.core/get-in), [`some`](https://clojure.github.io/clojure/clojure.core-api.html#clojure.core/some)
- [`clojure.walk`](https://clojure.github.io/clojure/clojure.walk-api.html) — only if you need whole-tree traversal; not assumed in this thread

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
