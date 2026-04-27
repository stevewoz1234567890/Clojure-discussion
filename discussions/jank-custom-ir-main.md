# jank — custom IR on `main` (PR #735)

**[jank](https://github.com/jank-lang/jank)** is a **native Clojure** dialect (compiled **C++** / **LLVM**). **Custom intermediate representation (IR)** support has landed on **`main`**: the pipeline no longer lowers an **AST** straight to **C++** and **LLVM IR** for codegen; it now uses an **SSA-form** **jank-specific IR** with **dedicated C++ codegen**, so **optimization passes** can run **before** final code generation.

## What changed (summary)

Per **[#735 — Add custom IR](https://github.com/jank-lang/jank/pull/735)**:

- **Removed** the previous **AST-based** codegen path (**C++** and **LLVM IR** emitted from the AST).
- **Added** an **SSA-based** **custom jank IR** plus its **own C++ codegen**.
- **Goal:** enable **optimization passes** ahead of codegen.
- **Semantics:** jank’s IR is intentionally at the **Clojure semantic level**—**much higher level** than **LLVM IR**—so optimizations can mirror **Clojure** semantics that **LLVM** alone cannot express.

Upstream described the PR as on the order of **several weeks** of work.

## Documentation

- **[Developer guide — Intermediate Representation (IR)](https://book.jank-lang.org/dev/ir.html)** — concepts and structure of the custom IR (`book.jank-lang.org`).

## Links

- [GitHub — jank-lang/jank](https://github.com/jank-lang/jank)  
- [Pull request #735 — Add custom IR](https://github.com/jank-lang/jank/pull/735)  
- [IR documentation — book.jank-lang.org](https://book.jank-lang.org/dev/ir.html)

---

*Community note; refer to upstream repos and docs for current behavior.*
