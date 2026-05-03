# Clojure-mode, cljfmt, and indent spec tuples — Elisp vectors vs quoted lists — community thread

This note summarizes a short exchange about the **“modern” tuple-style indent spec** surfaced in **[cljfmt](https://github.com/weavejester/cljfmt)** configuration (e.g. **vector-of-tuples** in **EDN**), and whether **GNU Emacs** **[clojure-mode](https://github.com/clojure-emacs/clojure-mode)** could accept **Elisp vector literals** for the same shapes so tooling docs match **without** heavy quoting. Informal chat; **no** shipped change was claimed in-thread.

---

## The idea

**cljfmt** documents indent rules using a **tuple** representation (keywords such as **`:block`** / **`:inner`** plus numeric arguments) that in **Clojure** / **EDN** naturally appear as **vectors**, for example:

```clojure
[[:block 1] [:inner 2 0]]
```

In **Emacs Lisp**, **`put-clojure-indent`** and related conventions often show **quoted lists**:

```elisp
'((:block 1))
```

**yuhan** noted that **vector literals** in Elisp—e.g. **`[[:block 1] [:inner 2 0]]`**—would give **near–syntax parity** with **non-Emacs** contexts (**cljfmt** / **clojure-mode**-adjacent docs), avoid **`'`** on the outer form, and read more clearly as **distinct from legacy** indent forms. Ordinary **Elisp `eval`** foot-guns seem **less relevant** here because these values are typically **static literals**, not built by **dynamic splicing**.

---

## Counterpoint and refinement

**yuhan** (same thread) softened the idea: **lists** may still be the better **internal** representation for **programmatic** edits, **serialization** (**bencode**, etc.). A plausible direction is a **normalization** step (e.g. where **`put-clojure-indent`** consumes spec values) so callers may pass **either** **vectors** or **traditional quoted lists**, with **documentation** preferring the **vector** spelling for **cross-platform** alignment—if the tuple format is treated as a **shared** convention across **clojure-mode**, **cljfmt**, and prose.

---

## Maintainer stance

**Bozhidar Batsov** (**[@bbatsov](https://github.com/bbatsov)**) replied that he is **open to discussion**, with one caveat: **vectors are uncommon** in typical **Elisp** code, so style guides may still bias toward **lists**. He acknowledged that in **this** narrow context vectors could still be **useful** and **visually familiar** to **Clojure** programmers.

**yuhan** floated opening a **GitHub issue**, noting the topic touches **several** repositories.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|------------------------|
| **yuhan** | Vectors in Elisp for tuple specs → parity with cljfmt; eval weirdness mostly irrelevant; optional **normalize** in **`put-clojure-indent`**; prefer vector form in **docstrings** for shared docs; may file **GH** issue. |
| **Bozhidar Batsov** | Open to discussing; vectors **rare** in Elisp overall; still can help Clojure-oriented readers. |

---

## See also

- [clojure-emacs/clojure-mode](https://github.com/clojure-emacs/clojure-mode) — indentation helpers such as **`put-clojure-indent`**
- [weavejester/cljfmt](https://github.com/weavejester/cljfmt) — **`:indents`** / tuple-style rules
- Community note — **[cljfmt 0.16.4](cljfmt-0.16.4.md)** (release-focused; not specific to this Emacs/formatting-language thread)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
