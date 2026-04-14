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

Clojure, ClojureScript, **Ring / Reitit / Pedestal**, **Datomic & SQL** (e.g. PostgreSQL), **Kafka & messaging**, **Docker & cloud-native** deployments, **observability** (metrics, tracing, structured logging), and **data / stats / numerical** interop (e.g. [ClojisR](https://scicloj.github.io/clojisr/) for R, [Raster](https://github.com/replikativ/raster) for JVM scientific computing) where projects span the JVM and quantitative stacks.

*Stack-specific needs?* We adapt to your existing choices rather than forcing a rewrite.

---

## How to start

1. **Brief** — Share goals, current architecture (even informally), and what “done” looks like.  
2. **Discovery call** — We align on risks, timeline, and the smallest valuable next step.  
3. **Proposal** — You receive a short written scope, milestones, and how we will communicate progress.

---

## Community notes

Concise write-ups—announcements, library pointers, and threads worth keeping—live under [`discussions/`](discussions/). Runnable Clojure examples are under [`examples/`](examples/) (see the [index](examples/README.md)). The index below is **alphabetical by note title** (case-insensitive).

- **[Aleph 0.9.6 — Netty CVEs, 4.2 roadmap, `data.json` vs Jackson](discussions/aleph-0.9.6-netty.md)** — Netty **4.1.132.Final** for CVE-2026-33870 / CVE-2026-33871 ([#781](https://github.com/clj-commons/aleph/pull/781)); Netty **4.2** migration underway; `ring-data-json`, **`org.clojure/data.json`** performance and zero deps; Jackson **`STRICT_DUPLICATE_DETECTION`** vs duplicate-key security. Thanks to [clj-commons/aleph](https://github.com/clj-commons/aleph) maintainers and thread participants.
- **[Calva v2.0.573 — REPL UI defaults & Try Clojure starter](discussions/calva-2.0.573-repl-ui-try-clojure.md)** — [BetterThanTomorrow/calva](https://github.com/BetterThanTomorrow/calva) **v2.0.573**: defaults aligned with **Getting Started**; **terminal** output; **echo eval** on by default; **API** evals always visible; no auto-**Inspector**; mini starter → **[`clojure/try-clojure`](https://github.com/clojure/try-clojure)**; thread on **`settings.json`** completion vs upgraded users. Thanks to [@PEZ](https://github.com/PEZ) and [@seancorfield](https://github.com/seancorfield).
- **[cljfmt 0.16.4 — config validation & alignment fixes](discussions/cljfmt-0.16.4.md)** — [`dev.weavejester/cljfmt`](https://clojars.org/dev.weavejester/cljfmt) **0.16.4** ([weavejester/cljfmt](https://github.com/weavejester/cljfmt)): **config validation** ([#406](https://github.com/weavejester/cljfmt/pull/406)); **`:split-keypairs-over-multiple-lines`** fix for first map key ([#409](https://github.com/weavejester/cljfmt/pull/409)); **`:align-single-column-lines?`** fix for last elements ([#411](https://github.com/weavejester/cljfmt/pull/411)). [Release 0.16.4](https://github.com/weavejester/cljfmt/releases/tag/0.16.4). Thanks to [@weavejester](https://github.com/weavejester) and contributors.
- **[clojisr 1.1.0 — docstrings on `require-r`](discussions/clojisr-1.1.0-docstrings.md)** — Clojure ↔ R interop; optional R help text as var metadata. Runnable **[example](examples/clojisr-docstrings/)** (`scicloj/clojisr` **1.1.0**). Thanks to [@Carsten Behring](https://github.com/behrica).
- **[clj-xref 0.1.0 — cross-reference DB for Clojure (clj-kondo)](discussions/clj-xref-0.1.0.md)** — [`com.github.danlentz/clj-xref`](https://clojars.org/com.github.danlentz/clj-xref) **0.1.0** ([danlentz/clj-xref](https://github.com/danlentz/clj-xref)): **`lein xref`** / **`clj -T:xref generate`** → query **`who-calls`**, **`calls-who`**, **`who-implements`**, **`unused-vars`**, **`call-graph`**, **`ns-deps`**, etc.; **LLM**-oriented **smaller context** (experiment; **~66–80%** char reduction on self-host per upstream); **DOT** graphs, **CI**. Built on **clj-kondo** — [sponsor @borkdude](https://github.com/sponsors/borkdude). Thanks to [@danlentz](https://github.com/danlentz).
- **[Clojure 1.12.5-alpha1 — `reify` metadata & `constantly`](discussions/clojure-1.12.5-alpha1.md)** — [CLJ-2945](https://clojure.atlassian.net/browse/CLJ-2945) (reader metadata no longer on reified objects), [CLJ-2228](https://clojure.atlassian.net/browse/CLJ-2228) (`constantly` rest-args); tooling feedback thread ([@ericdallo](https://github.com/ericdallo), [@seancorfield](https://github.com/seancorfield)). Runnable **[example](examples/reify-meta/)**.
- **[Clojure REPL DX, errors, and Java interop](discussions/repl-dx-errors-java-interop.md)** — Synthesized thread: **REPL-first** workflow vs debuggers, **error readability** (including from a **Racket** background), and **Java boundary** / **`NullPointerException`** pain; vector update confusion. Thanks to **Eddie**, **Robert Stefanic**, and **Joaquín Pérez**.
- **[`csp-clj` — CSP with JDK virtual threads](discussions/csp-clj.md)** — [fgasperino/csp-clj](https://github.com/fgasperino/csp-clj): PoC **CSP** channels for **Clojure** on **JDK 24+** (**virtual threads**, blocking ops vs **`core.async`** parking); [`org.clojars.fgasperino/csp-clj`](https://clojars.org/org.clojars.fgasperino/csp-clj). Thanks to [@fgasperino](https://github.com/fgasperino).
- **[datomic-pro-manager 1.1.0 — Datomic from your app](discussions/datomic-pro-manager-1.1.0-in-app.md)** — [`io.github.filipesilva/datomic-pro-manager`](https://github.com/filipesilva/datomic-pro-manager) **v1.1.0**: `dpm/up`, `dpm/wait-for-up`, `dpm/db-uri` for embedded transactor startup. Thanks to [@filipesilva](https://github.com/filipesilva).
- **[Eido — declarative EDN images & scenes](discussions/eido-declarative-images.md)** — [`io.github.leifericf/eido`](https://github.com/leifericf/eido): 2D/3D, particles, CPU rendering, zero deps; [gallery](https://eido.leifericf.com/gallery/) & [docs](https://eido.leifericf.com/docs/). Runnable **[example](examples/eido-circle/)**. Thanks to [@leifericf](https://github.com/leifericf).
- **[Epupp — Scittle REPL & userscripts in the browser](discussions/epupp-browser-extension.md)** — [PEZ/epupp](https://github.com/PEZ/epupp) **v0.0.15**: `epupp://` local libs, SHA-pinned GitHub/gist HTTPS deps, `document-start` double-run fix; [Chrome](https://chromewebstore.google.com/detail/bfcbpnmgefiblppimmoncoflmcejdbei) · [Firefox](https://addons.mozilla.org/firefox/addon/epupp/). Thanks to [@PEZ](https://github.com/PEZ).
- **[Garden `run` vs `deploy` — local deps & aliases](discussions/garden-cli-local-deps-vs-prod.md)** — Synthesized thread: **`:local/root`** vs production coords with **[Garden CLI](https://github.com/nextjournal/garden-cli)**; **`deps.edn` aliases** vs **`garden run`** parity (**storage**, **Garden ID**); feature request [**#9**](https://github.com/nextjournal/garden-cli/issues/9); **[Launchpad](https://github.com/lambdaisland/launchpad)** and **classpath vs prod**. Thanks to **teodorlu**, **jackrusher**, and **JAtkins**.
- **[Grid Coordination — OpenADR 3 VTN & California price server](discussions/grid-coordination-openadr3-vtn-price-server.md)** — [`energy.grid-coordination/clj-oa3-vtn`](https://clojars.org/energy.grid-coordination/clj-oa3-vtn) ([grid-coordination/clj-oa3-vtn](https://github.com/grid-coordination/clj-oa3-vtn)): **OpenADR 3.1.0** VTN (**BL** + **VEN** ports), **MQTT** (Mosquitto), **DynamoDB** or memory, **Legba** routes from spec + **clj-oa3**. Live **[price server](https://price.grid-coordination.energy/openadr3/3.1.0/)** (CAISO day-ahead, PG&E/SCE, **492** programs, no auth); **[Clojure tutorial](https://github.com/grid-coordination/price-server-user-guide/blob/main/tutorial-clojure.md)** (**clj-oa3-client**, **oz** / Vega-Lite). [Home](https://grid-coordination.energy/) · [software](https://grid-coordination.energy/software). Thanks to [grid-coordination](https://github.com/grid-coordination).
- **[javadbf + Clojure — `:import` vs `:require`](discussions/javadbf-clojure-import.md)** — [albfernandez/javadbf](https://github.com/albfernandez/javadbf): Maven `com.github.albfernandez/javadbf` vs Java package `com.linuxense.javadbf` (solution: **Erik Colson**). Runnable **[example](examples/javadbf-roundtrip/)**. Thanks to **Erik Colson** and thread participants.
- **[k7 — mmap’d disk queue](discussions/k7-disk-queue.md)** — [`com.s-exp/k7`](https://clojars.org/com.s-exp/k7): append-only log, zero-copy `ByteBuffer` reads, consumer groups, ack/nack/seek, CRC32C recovery; ~20M msg/s enqueue / ~6.5M poll+ack (M1, JVM 20, `:flush`) per [upstream benches](https://github.com/mpenet/k7/blob/main/bench-output.txt). Runnable **[example](examples/k7-hello/)**. Thanks to [@mpenet](https://github.com/mpenet).
- **[`ordered-collections` 0.2.0 — ropes & ordered structures](discussions/ordered-collections-0.2.0.md)** — [`com.dean/ordered-collections`](https://clojars.org/com.dean/ordered-collections) **0.2.0** ([dco-dev/ordered-collections](https://github.com/dco-dev/ordered-collections)): **rope**, **ordered-set/map**, intervals, range maps, segment trees, fuzzy & parallel **`r/fold`**; thread vs [IGJoshua/ropes](https://github.com/IGJoshua/ropes), [bifurcan](https://github.com/phronmophobic/bifurcan), [clobber](https://github.com/phronmophobic/clobber). Thanks to [@danlentz](https://github.com/danlentz) and thread participants.
- **[Pi, Psi, and Clojure tooling](discussions/pi-agent-clojure-tooling.md)** — Synthesized thread: Pi vs MCP, `clojure-lsp`, nREPL skills, [oh-my-pi](https://github.com/can1357/oh-my-pi), [pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter), FastMCP CLI; **[Mario Zechner on Pi & agents (YouTube)](https://www.youtube.com/live/_zdroS0Hc74?si=KyYQqxOACvO3nEHq&t=3638)**; Jira MCP **context cost**, [sift-gateway](https://github.com/lourencomaciel/sift-gateway), [clojure-mcp-light](https://github.com/bhauman/clojure-mcp-light).
- **[Pomegranate 1.3.26 — Maven resolver & classpath](discussions/pomegranate-1.3.26.md)** — [clj-commons/pomegranate](https://github.com/clj-commons/pomegranate) **v1.3.26**: **[RFC 9457](https://www.rfc-editor.org/rfc/rfc9457.html)** problem details, **`HttpTransport`** default ([#233](https://github.com/clj-commons/pomegranate/pull/233) [@tcrawley](https://github.com/tcrawley)), **plexus-utils** / **CVE-2025-67030** ([#234](https://github.com/clj-commons/pomegranate/pull/234)), dependency cleanup ([#237](https://github.com/clj-commons/pomegranate/pull/237), [#239](https://github.com/clj-commons/pomegranate/pull/239)), SCM POM fix ([#229](https://github.com/clj-commons/pomegranate/pull/229) [@ambrosebs](https://github.com/ambrosebs)). Chat: **`#pomegranate`**.
- **[Practicalli nvim-astro — rich Clojure config on Neovim 0.12](discussions/practicalli-nvim-astro.md)** — [practicalli/nvim-astro](https://github.com/practicalli/nvim-astro) ([release 2026-04-08](https://github.com/practicalli/nvim-astro/releases/tag/2026-04-08)): **AstroNvim v6**, **Conjure**, paredit/parinfer (**par-par**), Tree-sitter, **Mason** + **`clojure-lsp`**, lazy loading, **LazyGit** / **Neogit**; neo-tree → **`snacks.picker.explorer`**, markdown (**marksman**) & bash packs. Thanks to [Practicalli](https://github.com/practicalli).
- **[Raster 0.1.1 — Fast numerical computing for Clojure](discussions/raster-0.1.1.md)** — [`org.replikativ/raster`](https://clojars.org/org.replikativ/raster) **0.1.1** ([replikativ/raster](https://github.com/replikativ/raster)): **`deftm`** typed dispatch, **`par/map`** / SOAC fusion, forward & reverse AD, nanopass compiler, ODE/optim/linalg/deep learning, GPU backends; **`compile-aot`** for hot paths. **JDK 24+** (Valhalla **JDK 27** recommended). [Docs & notebooks](https://replikativ.github.io/raster/). Chat: **`#simmis`** on Clojurians. Thanks to [replikativ](https://github.com/replikativ), Raster contributors, and early thread voices ([@teodorlu](https://github.com/teodorlu), [@whilo](https://github.com/whilo)).
- **[Ring 1.15.4 — Jetty & Apache Commons bumps](discussions/ring-1.15.4.md)** — [ring-clojure/ring](https://github.com/ring-clojure/ring) **v1.15.4** ([CHANGELOG](https://github.com/ring-clojure/ring/blob/master/CHANGELOG.md#1154-2026-04-13)): **Jetty** **12.1.0 → 12.1.8** ([#552](https://github.com/ring-clojure/ring/pull/552)), **Commons FileUpload** **2.0.0-M4 → 2.0.0-M5** ([#551](https://github.com/ring-clojure/ring/pull/551)), **Commons IO** **2.20.0 → 2.21.0**. Patch-only; bump Ring artifacts together. Thanks to [ring-clojure](https://github.com/ring-clojure) maintainers.
- **[`tree-sitter-clojure` (WASM) & Chiasmus](discussions/tree-sitter-clojure-wasm-chiasmus.md)** — npm [`@yogthos/tree-sitter-clojure`](https://www.npmjs.com/package/@yogthos/tree-sitter-clojure) (no native deps); **[chiasmus](https://github.com/yogthos/chiasmus)** MCP: JS sandbox + Tree-sitter + **Prolog**/**Z3**, **Claude Code** **`chiasmus_review`** workflow, scale via **[#17](https://github.com/yogthos/chiasmus/pull/17)** (native **O(V+E)** graphs), tool-call **UX**; agents/editors vs grep ([arXiv:2305.00813](https://arxiv.org/abs/2305.00813)); **[sporulator](https://github.com/mycelium-clj/sporulator)** (**React Flow**). Thanks to [@yogthos](https://github.com/yogthos), **Tommy**, **JAtkins**, [@cfleming](https://github.com/cfleming), and **Wanishing**.

---

## License & confidentiality

Unless otherwise noted, content in this repository is provided for discussion and demonstration. Client work is covered by separate agreements and is not published here without permission.

---

*Clojure® is a trademark of Rich Hickey. This README describes independent professional services.*
