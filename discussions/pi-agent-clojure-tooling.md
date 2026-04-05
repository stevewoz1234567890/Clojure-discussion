# Pi, Psi, and Clojure: community tooling notes

This note summarizes a community thread about using **[Pi](https://github.com/badlogic/pi-mono/tree/main/packages/coding-agent)** (and related “coding agent” harnesses) with **Clojure** projects: LSP, nREPL, MCP vs shell, and practical workflows. It is **not** official documentation for any project; treat links as the source of truth.

---

## Context: what people are trying to solve

- **Pi’s model:** heavy emphasis on **customization for advanced users**, with an **LSP plugin**—though at the time of the discussion, **no one had wired up `clojure-lsp`** in the same way as in some other assistants.
- **No first-class MCP in Pi:** Pi steers toward **bash (and similar) as the integration surface** rather than MCP servers—partly by design; see Mario Zechner’s post below.
- **Open questions from the thread:** How should an agent **explore a Clojure codebase** under those constraints? Could **JavaScript extensions** (and ideas like **[Squint](https://github.com/squint-cljs/squint)** for Clojure-syntax → JS) be a good fit for Pi-style extension?

---

## Related projects Clojurians mentioned

| Project | Role |
|--------|------|
| **[Psi](https://github.com/hugoduncan/psi)** | Emacs-oriented harness sharing conceptual DNA with Pi; discussed as **more polished around Emacs**. |
| **[oh-my-pi](https://github.com/can1357/oh-my-pi)** | Fork-style harness; one contributor reported **good results on a Clojure monorepo** with **custom extensions** (e.g. paren helpers, repo-specific tooling). Described as **not** trying to stay in lockstep with upstream Pi. |
| **[pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter)** | Mentioned as a bridge for those who **do** want MCP-style capabilities alongside Pi. |

---

## nREPL, LSP, and “skills”

- **nREPL:** Hugo Duncan noted that something like **`clj-nrepl-eval` is straightforward to expose as a skill**—giving the agent a controlled way to evaluate Clojure without reinventing the REPL story.
- **`clojure-lsp`:** Also plausible **as a skill**; for **Claude-like LSP behaviour** (deep editor integration semantics), several people expected **deeper integration** or a **Psi/Pi extension** might be warranted—not only a thin wrapper.
- **Psi maturity:** Hugo also cautioned that **Psi is young**, the **extension API is not stable**, and **usage outside the author’s own experiments** may still be limited—worth checking current READMEs and issues before investing heavily.

---

## MCP vs bash: two camps (and a middle ground)

**Skepticism toward “MCP everywhere” (Pi-aligned view):**

- Mario Zechner (Pi’s author) argues that **Bash + a code interpreter** often suffices and that MCP can be **heavy on context**; see **[What if you don’t need MCP at all?](https://mariozechner.at/posts/2025-11-02-what-if-you-dont-need-mcp/)**.
- Mario Trost summarized that view and pointed to **[mcporter](https://github.com/steipete/mcporter)** as an example of **putting a layer** between the model and raw MCP so you **don’t blow up context**.

**Counterpoints from the thread:**

- Some argued **models are increasingly trained on MCP**, so **dismissing MCP entirely** may age poorly.
- **Bruce Hauman** noted that **Claude Code lazy-loads MCP tools** so **not every tool sits in context at once**—changing the “context bloat” calculus for that stack.
- **didibus** raised a practical gap: **many services lack a good CLI or curl-friendly API**, which makes “just use bash” harder unless someone builds a thin CLI façade.

**Bridging MCP from the shell:**

- **[FastMCP CLI / client](https://gofastmcp.com/cli/client)** was mentioned as a way to **list tools, call them, and discover configured servers** from the command line—potentially **pipe-friendly** for Pi’s bash-centric model.

---

## Letting Pi explore a Clojure project (pragmatic checklist)

Without prescribing a single stack, thread participants implicitly converged on **exposing the repo through commands the agent can already run**:

1. **Static structure:** `clojure -M:...`, **`clj-kondo`**, **`deps` / `lein` graphs**, **`git`**—anything that prints **actionable, copy-pastable** output.
2. **Tests as spec:** **`clojure -M:test`** or **`kaocha`**, etc., give **fast feedback** loops the agent can drive from bash.
3. **REPL evaluation:** a **small skill or script** around **nREPL** (or **socket REPL**) for **targeted evaluation**, not full IDE parity.
4. **LSP (optional):** **`clojure-lsp`** subcommands or a **wrapper skill** if you need **definitions / refs** without starting a full LSP UI inside the agent.

Exact flags depend on your **`deps.edn` / `lein` / `bb`** layout; keep scripts **one-purpose** so logs stay readable.

---

## Squint and JS extensions

The original poster suggested: **Pi is extended via JavaScript**, so **Squint** (Clojure-like syntax compiling to JS) might be a **natural fit** for authors who want **Clojure-flavoured** extension code. That remains an **idea to validate**—no linked production example appeared in the thread.

---

## Who said what (handles only; paraphrased)

| Handle | Contribution (summary) |
|--------|-------------------------|
| **john** | Pointed to **Psi**. |
| **awb99** | OP; Pi customization, LSP, no MCP; **nREPL / socket REPL** as “MCP replacement”; **Squint** angle. |
| **hugod** (Hugo Duncan) | **Psi** author perspective; **nREPL skill**, **clojure-lsp** depth, **API stability** caveat; **FastMCP CLI**. |
| **didibus** | MCP still useful; **CLI gap** for many services; **MCP-from-CLI** pipe dream. |
| **Tom H.** | **pi-mcp-adapter**. |
| **sundbp** | **Pi** and **oh-my-pi** on a **Clojure monorepo**; custom extensions; prefers **avoiding MCP** for context. |
| **Mario Trost** | Linked Mario Zechner’s MCP post and **mcporter**; uses Pi, likes **`/fork` and `/tree`**. |
| **bhauman** | **Claude Code** **lazy MCP loading**. |
| **cfleming** | **Pi as daily driver**; aligned with Mario’s philosophy; **Clojure code generation** when needed. |

---

## See also

- [Pi — `packages/coding-agent`](https://github.com/badlogic/pi-mono/tree/main/packages/coding-agent)
- [Psi](https://github.com/hugoduncan/psi)
- [oh-my-pi](https://github.com/can1357/oh-my-pi)
- [pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter)
- [FastMCP client CLI](https://gofastmcp.com/cli/client)
- [mcporter](https://github.com/steipete/mcporter)
- [What if you don’t need MCP at all?](https://mariozechner.at/posts/2025-11-02-what-if-you-dont-need-mcp/) (Mario Zechner)
- [Squint](https://github.com/squint-cljs/squint) (Clojure/script → JS)

---

*If you spot a factual error or want your project linked here, open a PR or issue on this repository.*
