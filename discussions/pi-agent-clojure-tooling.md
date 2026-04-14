# Pi, Psi, and Clojure: community tooling notes

This note summarizes a community thread about using **[Pi](https://github.com/badlogic/pi-mono/tree/main/packages/coding-agent)** (and related “coding agent” harnesses) with **Clojure** projects: LSP, nREPL, MCP vs shell, and practical workflows. It is **not** official documentation for any project; treat links as the source of truth.

---

## Context: what people are trying to solve

- **Pi’s model:** heavy emphasis on **customization for advanced users**, with an **LSP plugin**—though at the time of the discussion, **no one had wired up `clojure-lsp`** in the same way as in some other assistants.
- **No first-class MCP in Pi:** Pi steers toward **bash (and similar) as the integration surface** rather than MCP servers—partly by design; see Mario Zechner’s post below.
- **Open questions from the thread:** How should an agent **explore a Clojure codebase** under those constraints? Could **JavaScript extensions** (and ideas like **[Squint](https://github.com/squint-cljs/squint)** for Clojure-syntax → JS) be a good fit for Pi-style extension?

---

## Mario Zechner — live talk (Pi, design, and working with agents)

**[@badlogic](https://github.com/badlogic)** (Mario Zechner, Pi’s author) discusses **how Pi works**, **why it is shaped that way**, and **how he thinks we should work with agents** in a segment of a longer YouTube live (~**20 minutes**). Deep link (timestamp from the shared thread): **[youtube.com/live/_zdroS0Hc74](https://www.youtube.com/live/_zdroS0Hc74?si=KyYQqxOACvO3nEHq&t=3638)**.

---

## Related projects Clojurians mentioned

| Project | Role |
|--------|------|
| **[Psi](https://github.com/hugoduncan/psi)** | Emacs-oriented harness sharing conceptual DNA with Pi; discussed as **more polished around Emacs**. |
| **[oh-my-pi](https://github.com/can1357/oh-my-pi)** | Fork-style harness; one contributor reported **good results on a Clojure monorepo** with **custom extensions** (e.g. paren helpers, repo-specific tooling). Described as **not** trying to stay in lockstep with upstream Pi. |
| **[pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter)** | Mentioned as a bridge for those who **do** want MCP-style capabilities alongside Pi. |
| **[pandō](https://clojure.getpando.ai)** | Commercial **MCP** server focused on **structural** (AST) **Clojure** edits and refactors for agents; see **[community note](pando-structural-clojure-mcp.md)**. |

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

### Follow-up thread (after the video): MCP adapters, fat servers, and “Clojure in Pi”

A **Clojurians** side thread reacted to the same clip:

- **MCP as a deal-breaker:** **sheluchin** said **excluding MCP** makes Pi a **non-starter** for them (they rely on MCPs); they asked whether **token-efficiency** critiques of MCP really hold, and whether **[pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter)** is **effective in practice**.
- **Colin Fleming** had **not** needed MCP personally but suspected the **adapter works** for people who use it. He drew a parallel to **Clojure**: *if you can build it with the primitives **core** gives you, it doesn’t belong in **core***—similar to Pi’s integration philosophy. He planned a **write-up of his Pi config** and recommended watching the **next segment** in the **same live stream** with **Armin** (described as a **similar message**).
- **salam** sided with **shell commands / REST + a few skills**, for reasons overlapping Mario’s **MCP blog post**; they moved from **[clojure-mcp](https://github.com/bhauman/clojure-mcp)** to **[clojure-mcp-light](https://github.com/bhauman/clojure-mcp-light)**. At work they kept a **Jira MCP** server (**off by default**) mainly out of **convenience** vs wiring Jira with their own credentials—and noted about **13% of the model context** consumed to **fetch one ticket**, which pushed them toward **dropping it**.
- **didibus** called **Jira MCP** notorious for **huge payloads**, and stressed that **bad MCP server design** is part of the pain—**your own** CLI/REST wrappers can be **tighter**. They linked **[sift-gateway](https://github.com/lourencomaciel/sift-gateway)** as **promising** (they had **not** tried it).
- **ericdallo** noted that **many MCP servers have matured**, and that **dynamic tool discovery** in clients has **improved token usage** compared to earlier setups.
- **awb99** agreed Pi matches **Clojure’s philosophy** and asked **how to integrate Clojure into Pi**—calling for **Pi Clojure extensions**.

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
| **awb99** | OP; Pi customization, LSP, no MCP; **nREPL / socket REPL** as “MCP replacement”; **Squint** angle; later: **Pi Clojure extensions**. |
| **hugod** (Hugo Duncan) | **Psi** author perspective; **nREPL skill**, **clojure-lsp** depth, **API stability** caveat; **FastMCP CLI**. |
| **didibus** | MCP still useful; **CLI gap** for many services; **MCP-from-CLI** pipe dream; **Jira MCP** payloads; **server design** matters; **[sift-gateway](https://github.com/lourencomaciel/sift-gateway)** (not tried). |
| **sheluchin** | **No MCP → Pi** is a non-starter for them; questions **token** arguments; **pi-mcp-adapter** in practice? |
| **salam** | Shared the **video**; **shell/API/skills** camp; **clojure-mcp** → **clojure-mcp-light**; **Jira MCP** ~**13% context** / ticket. |
| **ericdallo** | **MCP servers** evolved; **dynamic tool discovery** eases **token** pressure. |
| **Tom H.** | **pi-mcp-adapter**. |
| **sundbp** | **Pi** and **oh-my-pi** on a **Clojure monorepo**; custom extensions; prefers **avoiding MCP** for context. |
| **Mario Trost** | Linked Mario Zechner’s MCP post and **mcporter**; uses Pi, likes **`/fork` and `/tree`**. |
| **bhauman** | **Claude Code** **lazy MCP loading**. |
| **cfleming** | **Pi as daily driver**; **Clojure** analogy (“**build with `core` primitives** → doesn’t belong in **`core`**”); **adapter** works for others; **Pi config** write-up planned; **Armin** segment in same stream; **Clojure code generation** when needed. |

---

## See also

- [Mario Zechner — Pi & agents (~20 min, YouTube live)](https://www.youtube.com/live/_zdroS0Hc74?si=KyYQqxOACvO3nEHq&t=3638)
- [Pi — `packages/coding-agent`](https://github.com/badlogic/pi-mono/tree/main/packages/coding-agent)
- [Psi](https://github.com/hugoduncan/psi)
- [oh-my-pi](https://github.com/can1357/oh-my-pi)
- [pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter)
- [FastMCP client CLI](https://gofastmcp.com/cli/client)
- [mcporter](https://github.com/steipete/mcporter)
- [What if you don’t need MCP at all?](https://mariozechner.at/posts/2025-11-02-what-if-you-dont-need-mcp/) (Mario Zechner)
- [clojure-mcp](https://github.com/bhauman/clojure-mcp) · [clojure-mcp-light](https://github.com/bhauman/clojure-mcp-light)
- [pandō — structural Clojure MCP](https://clojure.getpando.ai) · [note in this repo](pando-structural-clojure-mcp.md)
- [sift-gateway](https://github.com/lourencomaciel/sift-gateway) (context/filtering experiment; mentioned as promising)
- [Squint](https://github.com/squint-cljs/squint) (Clojure/script → JS)

---

*If you spot a factual error or want your project linked here, open a PR or issue on this repository.*
