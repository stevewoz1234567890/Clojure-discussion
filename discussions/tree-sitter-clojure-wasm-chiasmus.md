# `tree-sitter-clojure` (WASM) and Chiasmus — graphs, Prolog, and MCP

**[yogthos](https://github.com/yogthos)** published **[`@yogthos/tree-sitter-clojure`](https://www.npmjs.com/package/@yogthos/tree-sitter-clojure)** on npm: a **WebAssembly** build of **[tree-sitter-clojure](https://github.com/tree-sitter/tree-sitter-clojure)** with **no native addon dependencies**, intended for **JavaScript** sandboxes and tooling that cannot ship native Tree-sitter bindings. The motivation was a **project-specific** need, but it may be **generally useful** anywhere you want Tree-sitter for Clojure **without** native addons.

> *"I ended up porting tree-sitter-clojure to wasm for a project I'm working on, might be generally useful since it doesn't have native dependencies."* — **[@yogthos](https://github.com/yogthos)** (thread introducing the npm package)

## Chiasmus — MCP + Tree-sitter + logic

**[chiasmus](https://github.com/yogthos/chiasmus)** (npm: **`chiasmus`**) is an **MCP** server that exposes **formal verification** via **Z3** (SMT) and **Tau Prolog**, plus **Tree-sitter**–based **source analysis**. It lets an **LLM** load source into a **JS sandbox**, parse it with **Tree-sitter**, and answer **structural** questions—**call graphs**, reachability, **impact** / **dead-code** analysis, **function usage**—instead of **ad hoc grep**. Context from reading **[Neurosymbolic AI — Why, What, and How](https://arxiv.org/abs/2305.00813)** (arXiv:2305.00813): **neurosymbolic** ideas (perception vs cognition).

### How the pieces fit (from maintainer + thread)

- **Tree-sitter** supplies an initial **syntax graph** of the code; that graph is adapted for downstream reasoning (a **language-agnostic** parse layer, not the template mechanism itself).
- **Z3** (SMT) is in the loop for **formal verification**-style analysis; in practice **Prolog** (**Tau Prolog**) has looked **more useful** so far for **graph-shaped questions** (reachability, paths, "only certain paths reach this code") because those map naturally to logic queries.
- **Templates** are for the **solver** and **Prolog**, **not** for Tree-sitter: the model can **search for a template** that roughly matches the task and **fill in the blanks**, rather than hand-writing specs every time—useful for things like **call chains** if paired with clear **skill/tool** descriptions and **example questions**.
- **Verification-style** queries: e.g. whether **only specific paths** can reach a particular region of code.

**Tree-sitter [query syntax](https://tree-sitter.github.io/tree-sitter/using-parsers/queries/1-syntax.html)** (pattern matching on parse trees) and **Prolog templates** solve different problems: Tree-sitter gives concrete structure; **logic + templates** aim at higher-level graph questions for agents.

Examples and rationale for **graph-based queries vs grep** are in the **[Chiasmus README](https://github.com/yogthos/chiasmus?tab=readme-ov-file#why-chiasmus_graph-over-grep)**.

### Agents, editors, humans, and grep

The thread argued that **agent** tools which mostly **grep** files are a weak default when **loading the project into a graph** and **querying that** would fit better—**humans** often grep too. **IDEs** already build graphs (**find usages**); **IntelliJ** has added **structural search**. The gap is less “no graph exists” and more **exposing that graph to a logic engine** (or agent-friendly query layer) instead of **repeated grepping**—and that **many people may not know** these options exist.

### Edits and round-tripping

**Mutating** the graph and **serializing** back to source is **possible in principle** but needs more machinery (whitespace, formatting). A related direction is **text edits** with **Tree-sitter feedback** for validation. For **Clojure**, **syntax / paren errors** from a **Tree-sitter** view can be easier to work with than **explosive** reader failures elsewhere; the **REPL** remains a natural **workspace** for execution-grounded workflows.

## Chiasmus in the wild — Claude Code, scale, and MCP observability

Thread snippets from **#ai-assisted-coding** (and cross-posted channels) on **using Chiasmus with Claude Code** and **operational** pain points.

### Guided solver review via MCP

A participant reported that **forcing Claude to review code with the solver** often surfaced real issues. They added a **`chiasmus_review`** command to their **MCP** setup that **walks the agent through a sequence of steps**—which **almost always** turned up **something wrong** in their experience.

### Scale: Prolog, fact explosion, and native graph algorithms

**[@cfleming](https://github.com/cfleming)** asked how large a codebase Chiasmus had been tried on, and whether **Tau Prolog** slowed down when **fact counts** grew (anticipating a **huge** set even for **medium** projects).

**[@yogthos](https://github.com/yogthos)** confirmed that **large graphs** were a problem in practice and pointed to **[PR #17](https://github.com/yogthos/chiasmus/pull/17)** (*merged*): hot-path **reachability / cycles / impact / dead-code / callers / callees** analyses that previously went through **Prolog** were replaced with **native TypeScript** **O(V+E)** implementations (**Tarjan SCC**, **BFS**, indexed lookups). **Prolog** rules are still **emitted** for **`chiasmus_verify`** custom queries; **`facts`** responses gained a **size cap** (structured error above the limit) and **graph** handling trims **pretty-print** overhead to shrink payloads. Maintainer-reported **synthetic scale tests** (hundreds to ~1000 nodes) dropped from **multi-second timeouts** to on the order of **~30ms** for the combined analyses.

### Pretty-printing tool traffic (feature discussion)

**Wanishing** asked for a **pretty-printed** view of **tool calls and responses** so they could **audit** how **Claude Code** uses Chiasmus—raw logs were **hard to parse**, and they wanted to **validate** that the client was using the MCP **reasonably**.

**yogthos** called it a **good idea** and figured it should be **straightforward**; at the time he **mostly trusted** Claude Code when he saw tool activity and **occasionally** asked the model **what worked and what didn’t**. He also invited **more field reports**—Chiasmus as a **large experiment** benefits from **real-world** scenarios.

## Sporulator — workflow UI and constrained LLM loops

**[mycelium-clj/sporulator](https://github.com/mycelium-clj/sporulator)** is a separate experiment in a **more restricted** setting: a **declarative graph** of **execution flow**, with the **LLM** implementing **each step** as an **isolated** component (resources + state in → new state out), a **workflow engine** dispatching to the next node, and **mechanical verification** so the model stays in a **tight loop** (generate → execute → structured feedback). There are **two parts**: a **UI** (built with **[React Flow](https://reactflow.dev/)**) and a **server-side API** that does the heavy lifting. Maintainer-posted **screenshots** of the UI appeared in community channels (e.g. **#ai-assisted-coding**).

## Links

- [npm — `@yogthos/tree-sitter-clojure`](https://www.npmjs.com/package/@yogthos/tree-sitter-clojure)  
- [npm — `chiasmus`](https://www.npmjs.com/package/chiasmus) · [GitHub — yogthos/chiasmus](https://github.com/yogthos/chiasmus) · [PR #17 — native O(V+E) graph analyses (scale)](https://github.com/yogthos/chiasmus/pull/17)  
- [GitHub — mycelium-clj/sporulator](https://github.com/mycelium-clj/sporulator)  
- [arXiv — Neurosymbolic AI (2305.00813)](https://arxiv.org/abs/2305.00813)  
- [Upstream — tree-sitter/tree-sitter-clojure](https://github.com/tree-sitter/tree-sitter-clojure)

## Credits

Thread summarized from **Clojure** / **ai-assisted-coding** discussions (**yogthos**, **Tommy**, **JAtkins**, **cfleming**, **Wanishing**, and others). Thanks to **[Dmitri Sotnikov](https://github.com/yogthos)** ([@yogthos](https://github.com/yogthos)) for **@yogthos/tree-sitter-clojure**, **chiasmus**, and explanations; to **Tommy**, **JAtkins**, **Colin Fleming**, **Wanishing**, and thread participants for questions and battle reports.
