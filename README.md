# Clojure Development

**Reliable systems. Clear reasoning. Long-term maintainability.**

We help teams design, build, and evolve production software in **Clojure** and the **JVM ecosystem**—from greenfield products to critical legacy systems that need careful modernization.

## About this repository

Alongside the overview below, this repo holds **[community notes](discussions/)** (releases, threads, and tooling write-ups) and **[runnable examples](examples/README.md)** you can try with the [Clojure CLI](https://clojure.org/guides/install_clojure).

**Contents:** [Why Clojure](#why-clients-choose-clojure) · [What we deliver](#what-we-deliver) · [Engagement](#engagement-models) · [Tech stack](#tech-we-commonly-work-with) · [How to start](#how-to-start) · [Community notes](#community-notes) · [License](#license--confidentiality)

---

## Why clients choose Clojure

| Priority | What you get |
|----------|----------------|
| **Correctness** | Immutable data and expressive models reduce whole classes of bugs before they reach production. |
| **Throughput** | The JVM gives mature performance, observability, and integration with enterprise infrastructure. |
| **Velocity** | A small, consistent language and rich standard library keep teams focused on domain problems, not ceremony. |
| **Stability** | Clojure values backward compatibility; upgrades are predictable, not fire drills. |

Clojure is a strong fit when reliability, data-heavy workflows, and integration with Java or existing JVM services matter more than chasing the latest framework cycle.

---

## What we deliver

- **Product engineering** — Features, APIs, and services built with tests, clear boundaries, and operational clarity.
- **Architecture & review** — Design for concurrency, failure modes, and growth; code and system reviews that improve outcomes without bike-shedding.
- **Legacy & integration** — Interop with Java, Kafka, databases, and message pipelines; incremental refactors instead of risky big-bang rewrites.
- **Tooling & developer experience** — Build pipelines, REPL-driven workflows, **AI-assisted editing** (including agents such as [Pi](https://github.com/badlogic/pi-mono/tree/main/packages/coding-agent) / [Psi](https://github.com/hugoduncan/psi) with Clojure-aware skills), and documentation so your team owns the system confidently.

We work the way serious Clojure teams work: **REPL-first**, **data-oriented design**, and **small, composable modules**—so changes stay understandable years from now.

---

## Engagement models

- **Fixed-scope milestone** — Defined deliverable (MVP slice, migration phase, audit + roadmap).
- **Ongoing partnership** — Embedded or fractional capacity for sustained delivery and technical leadership.
- **Advisory** — Architecture sessions, hiring support, and escalation for high-stakes decisions.

Tell us your constraints—timeline, compliance, stack, and team size—and we will propose a concrete plan with transparent scope and communication rhythm.

---

## Tech we commonly work with

Clojure, ClojureScript, **Ring / Reitit / Pedestal**, **Datomic & SQL** (e.g. PostgreSQL), **Kafka & messaging**, **Docker & cloud-native** deployments, **observability** (metrics, tracing, structured logging), and **data / stats interop** (e.g. [ClojisR](https://scicloj.github.io/clojisr/) for R) where projects span the JVM and statistical stacks.

*Stack-specific needs?* We adapt to your existing choices rather than forcing a rewrite.

---

## How to start

1. **Brief** — Share goals, current architecture (even informally), and what “done” looks like.  
2. **Discovery call** — We align on risks, timeline, and the smallest valuable next step.  
3. **Proposal** — You receive a short written scope, milestones, and how we will communicate progress.

---

## Community notes

Concise write-ups—announcements, library pointers, and threads worth keeping—live under [`discussions/`](discussions/). Runnable Clojure examples are under [`examples/`](examples/) (see the [index](examples/README.md)).

- **[Aleph 0.9.6 — Netty CVEs, 4.2 roadmap, `data.json` vs Jackson](discussions/aleph-0.9.6-netty.md)** — Netty **4.1.132.Final** for CVE-2026-33870 / CVE-2026-33871 ([#781](https://github.com/clj-commons/aleph/pull/781)); Netty **4.2** migration underway; `ring-data-json`, **`org.clojure/data.json`** performance and zero deps; Jackson **`STRICT_DUPLICATE_DETECTION`** vs duplicate-key security. Thanks to [clj-commons/aleph](https://github.com/clj-commons/aleph) maintainers and thread participants.
- **[Practicalli nvim-astro — rich Clojure config on Neovim 0.12](discussions/practicalli-nvim-astro.md)** — [practicalli/nvim-astro](https://github.com/practicalli/nvim-astro) ([release 2026-04-08](https://github.com/practicalli/nvim-astro/releases/tag/2026-04-08)): **AstroNvim v6**, **Conjure**, paredit/parinfer (**par-par**), Tree-sitter, **Mason** + **`clojure-lsp`**, lazy loading, **LazyGit** / **Neogit**; neo-tree → **`snacks.picker.explorer`**, markdown (**marksman**) & bash packs. Thanks to [Practicalli](https://github.com/practicalli).
- **[`tree-sitter-clojure` (WASM) & Chiasmus](discussions/tree-sitter-clojure-wasm-chiasmus.md)** — npm [`@yogthos/tree-sitter-clojure`](https://www.npmjs.com/package/@yogthos/tree-sitter-clojure) (no native deps); **[chiasmus](https://github.com/yogthos/chiasmus)** MCP: JS sandbox + Tree-sitter + **Prolog**/**Z3**, templates vs Tree-sitter queries, agents/editors vs grep ([arXiv:2305.00813](https://arxiv.org/abs/2305.00813)); **[sporulator](https://github.com/mycelium-clj/sporulator)** (**React Flow**). Thanks to [@yogthos](https://github.com/yogthos), **Tommy**, and **JAtkins**.
- **[Pomegranate 1.3.26 — Maven resolver & classpath](discussions/pomegranate-1.3.26.md)** — [clj-commons/pomegranate](https://github.com/clj-commons/pomegranate) **v1.3.26**: **[RFC 9457](https://www.rfc-editor.org/rfc/rfc9457.html)** problem details, **`HttpTransport`** default ([#233](https://github.com/clj-commons/pomegranate/pull/233) [@tcrawley](https://github.com/tcrawley)), **plexus-utils** / **CVE-2025-67030** ([#234](https://github.com/clj-commons/pomegranate/pull/234)), dependency cleanup ([#237](https://github.com/clj-commons/pomegranate/pull/237), [#239](https://github.com/clj-commons/pomegranate/pull/239)), SCM POM fix ([#229](https://github.com/clj-commons/pomegranate/pull/229) [@ambrosebs](https://github.com/ambrosebs)). Chat: **`#pomegranate`**.
- **[`ordered-collections` 0.2.0 — ropes & ordered structures](discussions/ordered-collections-0.2.0.md)** — [`com.dean/ordered-collections`](https://clojars.org/com.dean/ordered-collections) **0.2.0** ([dco-dev/ordered-collections](https://github.com/dco-dev/ordered-collections)): **rope**, **ordered-set/map**, intervals, range maps, segment trees, fuzzy & parallel **`r/fold`**; thread vs [IGJoshua/ropes](https://github.com/IGJoshua/ropes), [bifurcan](https://github.com/phronmophobic/bifurcan), [clobber](https://github.com/phronmophobic/clobber). Thanks to [@danlentz](https://github.com/danlentz) and thread participants.
- **[`csp-clj` — CSP with JDK virtual threads](discussions/csp-clj.md)** — [fgasperino/csp-clj](https://github.com/fgasperino/csp-clj): PoC **CSP** channels for **Clojure** on **JDK 24+** (**virtual threads**, blocking ops vs **`core.async`** parking); [`org.clojars.fgasperino/csp-clj`](https://clojars.org/org.clojars.fgasperino/csp-clj). Thanks to [@fgasperino](https://github.com/fgasperino).
- **[Garden `run` vs `deploy` — local deps & aliases](discussions/garden-cli-local-deps-vs-prod.md)** — Synthesized thread: **`:local/root`** vs production coords with **[Garden CLI](https://github.com/nextjournal/garden-cli)**; **`deps.edn` aliases** vs **`garden run`** parity (**storage**, **Garden ID**); feature request [**#9**](https://github.com/nextjournal/garden-cli/issues/9); **[Launchpad](https://github.com/lambdaisland/launchpad)** and **classpath vs prod**. Thanks to **teodorlu**, **jackrusher**, and **JAtkins**.
- **[Clojure REPL DX, errors, and Java interop](discussions/repl-dx-errors-java-interop.md)** — Synthesized thread: **REPL-first** workflow vs debuggers, **error readability** (including from a **Racket** background), and **Java boundary** / **`NullPointerException`** pain; vector update confusion. Thanks to **Eddie**, **Robert Stefanic**, and **Joaquín Pérez**.
- **[Pi, Psi, and Clojure tooling](discussions/pi-agent-clojure-tooling.md)** — Synthesized thread: Pi vs MCP, `clojure-lsp`, nREPL skills, [oh-my-pi](https://github.com/can1357/oh-my-pi), [pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter), FastMCP CLI, and related links.
- **[clojisr 1.1.0 — docstrings on `require-r`](discussions/clojisr-1.1.0-docstrings.md)** — Clojure ↔ R interop; optional R help text as var metadata. Runnable **[example](examples/clojisr-docstrings/)** (`scicloj/clojisr` **1.1.0**). Thanks to [@Carsten Behring](https://github.com/behrica).
- **[datomic-pro-manager 1.1.0 — Datomic from your app](discussions/datomic-pro-manager-1.1.0-in-app.md)** — [`io.github.filipesilva/datomic-pro-manager`](https://github.com/filipesilva/datomic-pro-manager) **v1.1.0**: `dpm/up`, `dpm/wait-for-up`, `dpm/db-uri` for embedded transactor startup. Thanks to [@filipesilva](https://github.com/filipesilva).
- **[k7 — mmap’d disk queue](discussions/k7-disk-queue.md)** — [`com.s-exp/k7`](https://clojars.org/com.s-exp/k7): append-only log, zero-copy `ByteBuffer` reads, consumer groups, ack/nack/seek, CRC32C recovery; ~20M msg/s enqueue / ~6.5M poll+ack (M1, JVM 20, `:flush`) per [upstream benches](https://github.com/mpenet/k7/blob/main/bench-output.txt). Runnable **[example](examples/k7-hello/)**. Thanks to [@mpenet](https://github.com/mpenet).
- **[Eido — declarative EDN images & scenes](discussions/eido-declarative-images.md)** — [`io.github.leifericf/eido`](https://github.com/leifericf/eido): 2D/3D, particles, CPU rendering, zero deps; [gallery](https://eido.leifericf.com/gallery/) & [docs](https://eido.leifericf.com/docs/). Runnable **[example](examples/eido-circle/)**. Thanks to [@leifericf](https://github.com/leifericf).
- **[Clojure 1.12.5-alpha1 — `reify` metadata & `constantly`](discussions/clojure-1.12.5-alpha1.md)** — [CLJ-2945](https://clojure.atlassian.net/browse/CLJ-2945) (reader metadata no longer on reified objects), [CLJ-2228](https://clojure.atlassian.net/browse/CLJ-2228) (`constantly` rest-args); tooling feedback thread ([@ericdallo](https://github.com/ericdallo), [@seancorfield](https://github.com/seancorfield)). Runnable **[example](examples/reify-meta/)**.
- **[Epupp — Scittle REPL & userscripts in the browser](discussions/epupp-browser-extension.md)** — [PEZ/epupp](https://github.com/PEZ/epupp) **v0.0.15**: `epupp://` local libs, SHA-pinned GitHub/gist HTTPS deps, `document-start` double-run fix; [Chrome](https://chromewebstore.google.com/detail/bfcbpnmgefiblppimmoncoflmcejdbei) · [Firefox](https://addons.mozilla.org/firefox/addon/epupp/). Thanks to [@PEZ](https://github.com/PEZ).
- **[javadbf + Clojure — `:import` vs `:require`](discussions/javadbf-clojure-import.md)** — [albfernandez/javadbf](https://github.com/albfernandez/javadbf): Maven `com.github.albfernandez/javadbf` vs Java package `com.linuxense.javadbf` (solution: **Erik Colson**). Runnable **[example](examples/javadbf-roundtrip/)**. Thanks to **Erik Colson** and thread participants.

---

## License & confidentiality

Unless otherwise noted, content in this repository is provided for discussion and demonstration. Client work is covered by separate agreements and is not published here without permission.

---

*Clojure® is a trademark of Rich Hickey. This README describes independent professional services.*
