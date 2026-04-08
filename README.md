# Clojure Development

**Reliable systems. Clear reasoning. Long-term maintainability.**

We help teams design, build, and evolve production software in **Clojure** and the **JVM ecosystem**—from greenfield products to critical legacy systems that need careful modernization.

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

- **[Pi, Psi, and Clojure tooling](discussions/pi-agent-clojure-tooling.md)** — Synthesized thread: Pi vs MCP, `clojure-lsp`, nREPL skills, [oh-my-pi](https://github.com/can1357/oh-my-pi), [pi-mcp-adapter](https://github.com/nicobailon/pi-mcp-adapter), FastMCP CLI, and related links.
- **[clojisr 1.1.0 — docstrings on `require-r`](discussions/clojisr-1.1.0-docstrings.md)** — Clojure ↔ R interop; optional R help text as var metadata. Runnable **[example](examples/clojisr-docstrings/)** (`scicloj/clojisr` **1.1.0**). Thanks to [@Carsten Behring](https://github.com/behrica).
- **[datomic-pro-manager 1.1.0 — Datomic from your app](discussions/datomic-pro-manager-1.1.0-in-app.md)** — [`io.github.filipesilva/datomic-pro-manager`](https://github.com/filipesilva/datomic-pro-manager) **v1.1.0**: `dpm/up`, `dpm/wait-for-up`, `dpm/db-uri` for embedded transactor startup. Thanks to [@filipesilva](https://github.com/filipesilva).
- **[k7 — mmap’d disk queue](discussions/k7-disk-queue.md)** — [`com.s-exp/k7`](https://clojars.org/com.s-exp/k7): append-only log, zero-copy `ByteBuffer` reads, consumer groups, ack/nack/seek, CRC32C recovery; ~20M msg/s enqueue / ~6.5M poll+ack (M1, JVM 20, `:flush`) per [upstream benches](https://github.com/mpenet/k7/blob/main/bench-output.txt). Runnable **[example](examples/k7-hello/)**. Thanks to [@mpenet](https://github.com/mpenet).
- **[Eido — declarative EDN images & scenes](discussions/eido-declarative-images.md)** — [`io.github.leifericf/eido`](https://github.com/leifericf/eido): 2D/3D, particles, CPU rendering, zero deps; [gallery](https://eido.leifericf.com/gallery/) & [docs](https://eido.leifericf.com/docs/). Runnable **[example](examples/eido-circle/)**. Thanks to [@leifericf](https://github.com/leifericf).
- **[Clojure 1.12.5-alpha1 — `reify` metadata & `constantly`](discussions/clojure-1.12.5-alpha1.md)** — [CLJ-2945](https://clojure.atlassian.net/browse/CLJ-2945) (reader metadata no longer on reified objects), [CLJ-2228](https://clojure.atlassian.net/browse/CLJ-2228) (`constantly` rest-args); tooling feedback thread ([@ericdallo](https://github.com/ericdallo), [@seancorfield](https://github.com/seancorfield)). Runnable **[example](examples/reify-meta/)**.
- **[Epupp — Scittle REPL & userscripts in the browser](discussions/epupp-browser-extension.md)** — [PEZ/epupp](https://github.com/PEZ/epupp) **v0.0.15**: `epupp://` local libs, SHA-pinned GitHub/gist HTTPS deps, `document-start` double-run fix; [Chrome](https://chromewebstore.google.com/detail/bfcbpnmgefiblppimmoncoflmcejdbei) · [Firefox](https://addons.mozilla.org/firefox/addon/epupp/). Thanks to [@PEZ](https://github.com/PEZ).
- **[javadbf + Clojure — `:import` vs `:require`](discussions/javadbf-clojure-import.md)** — [albfernandez/javadbf](https://github.com/albfernandez/javadbf): Maven `com.github.albfernandez/javadbf` vs Java package `com.linuxense.javadbf` (solution: **Erik Colson**). Runnable **[example](examples/javadbf-roundtrip/)**.

---

## License & confidentiality

Unless otherwise noted, content in this repository is provided for discussion and demonstration. Client work is covered by separate agreements and is not published here without permission.

---

*Clojure® is a trademark of Rich Hickey. This README describes independent professional services.*
