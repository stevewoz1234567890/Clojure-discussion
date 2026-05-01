# re-frame 2026 — **re-frame-pair**, `re-frame.tooling`, and **`core-instrumented`** follow-up (**1.4.6** / **1.4.7**)

**[day8/re-frame](https://github.com/day8/re-frame)** shipped a coordinated **April 2026** release wave aimed at richer **debugging**, **tracing**, and **AI-assisted** workflows—chiefly to support **[re-frame-pair](https://github.com/day8/re-frame-pair)** (workflow / **SKILL**-style guidance for agents; still evolving but usable per maintainers).

**Announcement / changelog hub:** [April 29, 2026 release note](https://day8.github.io/re-frame/releases/2026/#146-2026-04-29) on [day8.github.io/re-frame/releases/2026](https://day8.github.io/re-frame/releases/2026/).

Coordinated artefacts mentioned upstream include **re-frame-10x**, **re-com**, **re-frame-debux**, and **re-frame-template** — pin versions from [Clojars **re-frame**](https://clojars.org/re-frame) / each project’s README.

---

## **1.4.6 (2026-04-29)** — tooling surface for **`re-frame-pair`**

Highlights from the published notes:

- **`re-frame.tooling`** — single **discoverability** namespace re-exporting the supported contract (**dispatch overrides**, trace callbacks/schema, subscription cache accessors, registrar atom) without moving canonical definitions ([**API**](https://day8.github.io/re-frame/api-re-frame.tooling/)).
- **Source-metadata capture** via a **`re-frame.macros`** mirror of **`re-frame.core`** (swap **`:require`** for **`dispatch`**, **`subscribe`**, **`reg-*`** forms so **`{:file :line}`** attaches for traces / **re-frame-10x** / **pair** semantics). **DCE** trims meta work in **`goog.DEBUG=false`** CLJS builds.
- **`dispatch-with`**, **`dispatch-sync-with`**, **`dispatch-and-settle`** — scoped **`:fx`** stubbing / async **settlement** diagnostics (tests, REPL, dry-runs).
- **`live-query-vs`**, **`query-v-for-reaction`** — stable subscription-cache accessors for tools.
- **Trace schema** **`tag-schema`**, optional **`validate-trace?`**, **epoch callbacks** alongside **`register-trace-cb`**.
- **`re-frame.config/version`** (**`version`** surfaced for tooling probes), duplicate registration warnings in **`re-frame.registrar`**.

See **Debugging & Instrumentation:** [Debugging](https://day8.github.io/re-frame/Debugging/).

---

## **1.4.7 (2026-04-30)** — Slack feedback folded in overnight

Maintainer **[Mike Thompson](https://github.com/mikethompson)** shipped **1.4.7** after thread feedback from **rolt** ([@RolT](https://github.com/RolT)):

1. **`reg-sub-raw`** was missing from the instrumented **`re-frame.macros`** surface.
2. **Alias-only** ergonomics struggled in some setups (e.g. **`re-frame.alpha`** namespaces + JVM macro expansion / **`ns`-alias** quirks).
3. Partial mirrors made swapping **`re-frame.core`** wholesale risky in larger apps (**`inject-cofx`**, **`path`**, **`console`**, clears, tracing helpers…).

**1.4.7** addresses this by renaming **`re-frame.macros`** → **`re-frame.core-instrumented`** (explicit purpose), exporting **pure `def` passthroughs** for essentially every remaining **`re-frame.core`** public, adding **macro coverage** for **`reg-sub-raw`**, **`reg-cofx`**, **`reg-event-error-handler`**, **`dispatch-with`**, **`dispatch-sync-with`**, **`dispatch-and-settle`**, and shipping **`re-frame.alpha-instrumented`** as the parallel **`re-frame.alpha`** mirror (advanced alpha macros deferred where still unstable (`reg`/`sub`/`reg-flow`) per notes).

Migrating: **`[re-frame.macros :as rf]`** → **`[re-frame.core-instrumented :as rf]`**.

A fresh **re-frame-pair** release was promised to trail **1.4.7**.

---

## Reception

rolt’s “**thanks — that was fast**” underscored the turnaround from report to patched release — treat **semver + changelog** as canonical; this note only **indexes** community context.

Thanks **Mike Thompson**, **rolt**, **`#re-frame`** chatters, and **day8** for publishing **paired programming** infra in the open.
