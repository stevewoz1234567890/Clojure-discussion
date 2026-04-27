# Lazytest — `describe`, `expect`, and `expect-it` (Slack thread)

This note summarizes a short **Clojurians Slack** exchange about **[Lazytest](https://github.com/NoahTheDuke/lazytest)** while **rewriting tests from `clojure.test`**. Lazytest is a **behavior-driven** alternative with **`defdescribe`**, **`describe`**, **`it`**, **`expect`**, and **`expect-it`**. It is informal chat; confirm behavior against **[upstream docs](https://github.com/NoahTheDuke/lazytest/blob/main/docs/core.md)** and **`lazytest.core`**.

---

## Themes

### What `describe` is for

Someone migrating tests asked whether **`describe`** was optional and how **`expect`** relates to it—specifically whether **`expect`** must sit inside **`describe`**, or whether tests can live **without** a parent **`describe`**.

**Noah Bogart** ([@NoahTheDuke](https://github.com/NoahTheDuke)), Lazytest’s maintainer, replied that **`describe`** is part of Lazytest’s **richer** model than bare `clojure.test`: it establishes structure so the runner has **named suites** and **nested groups**. In Lazytest’s model, **`it`** and **`expect-it`** define **test cases** (each backed by a function the runner invokes); **`describe`** builds **suites** that contain those cases or further nested suites. Upstream emphasizes that **`defdescribe`** / **`describe`** / **`it`** / **`expect-it`** build **data** (suite and test-case maps) that **`lazytest.runner`** traverses—side effects and assertions belong in **`it`** / **`expect-it`** bodies, not in loose code beside them.

### One assertion without a separate `it`

**Sean Corfield** ([@seancorfield](https://github.com/seancorfield)) pointed out **`expect-it`**: for **single-shot** tests (**one assertion per test case**), you can **combine** **`expect`** and **`it`** and **omit** a standalone **`it`** wrapping a single **`expect`**. **`expect-it`** takes a docstring and the assertion expression (see **`docs/core.md`** — “Combination of `expect` and `it`”).

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|-------------------------|
| **p-himik** | Migrating another project from **`clojure.test`** to Lazytest; asked whether **`describe`** is optional and how **`expect`** nests under it. |
| **Noah Bogart** | Explained **`describe`** as Lazytest’s structured grouping (“fancier” than plain `clojure.test`); assertions run in **test case** bodies the runner executes. |
| **Sean Corfield** | Mentioned **`expect-it`** for **one assertion per test** so **`it`** around a lone **`expect`** is unnecessary. |
| **p-himik** | Acknowledged both replies. |

---

## See also

- [GitHub — NoahTheDuke/lazytest](https://github.com/NoahTheDuke/lazytest)  
- [`docs/core.md`](https://github.com/NoahTheDuke/lazytest/blob/main/docs/core.md) — **`describe`**, **`it`**, **`expect-it`**, **`defdescribe`**

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
