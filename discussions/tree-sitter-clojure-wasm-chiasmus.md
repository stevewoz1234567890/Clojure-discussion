# `tree-sitter-clojure` (WASM) and Chiasmus — graphs, Prolog, and MCP

**[yogthos](https://github.com/yogthos)** published **[`@yogthos/tree-sitter-clojure`](https://www.npmjs.com/package/@yogthos/tree-sitter-clojure)** on npm: a **WebAssembly** build of **[tree-sitter-clojure](https://github.com/tree-sitter/tree-sitter-clojure)** with **no native addon dependencies**, intended for **JavaScript** sandboxes and tooling that cannot ship native Tree-sitter bindings.

## Chiasmus — MCP + Tree-sitter + logic

**[chiasmus](https://github.com/yogthos/chiasmus)** (npm: **`chiasmus`**) is an **MCP** server that exposes **formal verification** via **Z3** (SMT) and **Tau Prolog**, plus **Tree-sitter**–based **source analysis**. It lets an **LLM** work in a **JS sandbox** with parsed code and answer **structural** questions—**call graphs**, reachability, **impact** / **dead-code** analysis—instead of **ad hoc grep**. Motivation ties to **neurosymbolic** ideas (perception vs cognition); background reading: **[Neurosymbolic AI — Why, What, and How](https://arxiv.org/abs/2305.00813)** (arXiv:2305.00813).

### How the pieces fit (from maintainer + thread)

- **Tree-sitter** supplies an initial **syntax graph** of the code; that graph is adapted for downstream reasoning.
- **Prolog** (**Tau Prolog** in the shipped tool) is emphasized for **graph-shaped questions** (reachability, paths, taint-style flows) compared to raw grepping.
- **Z3** handles **SMT** / constraint problems (configs, RBAC, cross-validation, and similar).
- **Templates** (for the **solver** / **Prolog** side, not Tree-sitter itself): the model can **pick a template** close to the intent and **fill in parameters** (`chiasmus_formalize`, `chiasmus_skills`, etc.), rather than hand-writing specs from scratch every time.
- **Verification-style** queries: e.g. whether **only specific paths** can reach a particular region of code.

Examples and rationale for **graph-based queries vs grep** are in the **[Chiasmus README](https://github.com/yogthos/chiasmus?tab=readme-ov-file#why-chiasmus_graph-over-grep)**.

### Tree-sitter queries vs Prolog templates

The thread contrasted **[Tree-sitter query syntax](https://tree-sitter.github.io/tree-sitter/using-parsers/queries/1-syntax.html)** (pattern matching on concrete parse trees) with **Prolog**-backed **templates**: Tree-sitter gives the **language-agnostic parse layer**; **logic + templates** aim to express **higher-level** graph questions more naturally for agents.

### Edits and round-tripping

**Mutating** the graph and **serializing** back to source is **possible in principle** but needs more machinery (whitespace, formatting). A related direction is **text edits** with **Tree-sitter feedback** for validation. For **Clojure**, the **REPL** can act as the workspace for execution-grounded workflows.

## Sporulator — workflow UI and constrained LLM loops

**[mycelium-clj/sporulator](https://github.com/mycelium-clj/sporulator)** (separate project) combines a **UI** (**[React Flow](https://reactflow.dev/)**) with a **server-side API**: a **declarative graph** of execution flow, with the **LLM** implementing **each step** as an isolated component (state in → state out). **Mechanical verification** and **tight feedback** keep the model in a **fast loop** (generate → run → explain failures). Screenshots and discussion appeared in community channels (not duplicated here).

## Editors, grep, and “graphs you already have”

**IDEs** already build **graphs** (e.g. **find usages**); **IntelliJ** has added **structural search** in recent releases. The gap discussed in the thread is less “no graph exists” and more **exposing that graph to a logic engine** (or agent-friendly query layer) instead of **repeated grepping**—for humans as well as LLM tools.

## Links

- [npm — `@yogthos/tree-sitter-clojure`](https://www.npmjs.com/package/@yogthos/tree-sitter-clojure)  
- [npm — `chiasmus`](https://www.npmjs.com/package/chiasmus) · [GitHub — yogthos/chiasmus](https://github.com/yogthos/chiasmus)  
- [GitHub — mycelium-clj/sporulator](https://github.com/mycelium-clj/sporulator)  
- [arXiv — Neurosymbolic AI (2305.00813)](https://arxiv.org/abs/2305.00813)  
- [Upstream — tree-sitter/tree-sitter-clojure](https://github.com/tree-sitter/tree-sitter-clojure)

## Credits

Thread summarized from a **Clojure** community discussion (**yogthos**, **Tommy**, **JAtkins**). Thanks to **[Dmitri Sotnikov](https://github.com/yogthos)** ([@yogthos](https://github.com/yogthos)) for the packages and explanations.
