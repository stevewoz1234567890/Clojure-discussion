# Calva v2.0.573 — REPL UI defaults & official Try Clojure starter

This note summarizes a **#calva** thread around **[Calva](https://github.com/BetterThanTomorrow/calva) [v2.0.573](https://github.com/BetterThanTomorrow/calva/releases/tag/v2.0.573)** (2026-04-12): aligning **REPL UI defaults** with **Getting Started** / starter-project behavior, and swapping the **mini Clojure project** template for the **`clojure/try-clojure`** project. It also captures a short follow-up on **why existing users might not see “new” defaults** after upgrading. Informal chat, not a substitute for the [release notes](https://github.com/BetterThanTomorrow/calva/releases/tag/v2.0.573) or Calva docs.

---

## Release intent (PEZ)

**[@PEZ](https://github.com/PEZ)** described the release as part of preparing Calva for **Clojure Documentary**-driven onboarding: **install Java, VS Code, Calva → Try Clojure** with a straighter **zero-to-REPL** path than older flows. Feedback was invited in **#calva** and **#backseat-driver**.

Calva is **open to bundling more quick-start projects**; as of the thread, that still meant **updating an index in Calva’s source**, with room to move to a **fully dynamic / external index** if the list grows unwieldy.

---

## Changelog highlights (v2.0.573)

Per the **[GitHub release](https://github.com/BetterThanTomorrow/calva/releases/tag/v2.0.573)** (and upstream issues **[#3166](https://github.com/BetterThanTomorrow/calva/issues/3166)**, **[#3168](https://github.com/BetterThanTomorrow/calva/issues/3168)**):

- **REPL UI configuration defaults** updated to match the **Getting Started REPL**.
- **Terminal** is the default destination for **all output categories**.
- **Echoing evaluated code** to the **output / evaluation result** destination is **on by default**.
- The configured **output evaluation result** destination **always** shows code evaluated via the **API** (e.g. **Backseat Driver** evaluations).
- **Do not auto-reveal** the **Calva Inspector** on REPL connect.
- **Mini Clojure project starter** now uses the **official** **[`clojure/try-clojure`](https://github.com/clojure/try-clojure)** project.

---

## Thread: defaults after upgrade

**[@seancorfield](https://github.com/seancorfield)** upgraded and expected **code echo** to appear when eval’ing to a connected REPL, but **echo stayed off**—their setting was already **`false`**, and they wondered whether an **older Calva** had once written that or they had toggled it long ago.

**PEZ** explained that editing `settings.json` via VS Code’s **JSON editor** can **insert a key’s default** when **autocomplete “completes”** a setting—so a user might set **`false`** **unintentionally** while looking for something else. **Users who never touched those keys** will get the **new defaults**. He also noted **Copilot** still sees **evaluated code echoed**, and recalled **Copilot’s own evaluations** showing up echoed as well. He admitted **nervousness** about the default change after **years** of knowing it should happen but delaying.

**Sean** found the JSON-completion explanation plausible and said **finding and changing a setting** in VS Code is easy—not to worry too much about the default flip for experienced users.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|--------------------------|
| **PEZ** | Shipped **v2.0.573**; **Try Clojure** path; changelog; open to **more bundled starters**; **JSON settings completion** can paste **`false`**; **Copilot** / API echo behavior; candid about **default-change** risk. |
| **seancorfield** | After upgrade, **echo** still off—likely **existing `settings.json`**; appreciated the **completion** hypothesis; relaxed about **migrating** power users. |

---

## See also

- [Calva — GitHub](https://github.com/BetterThanTomorrow/calva)  
- [Release v2.0.573](https://github.com/BetterThanTomorrow/calva/releases/tag/v2.0.573)  
- [Issue #3166 — REPL UI defaults / Getting Started](https://github.com/BetterThanTomorrow/calva/issues/3166)  
- [Issue #3168 — Try Clojure starter](https://github.com/BetterThanTomorrow/calva/issues/3168)  
- [`clojure/try-clojure`](https://github.com/clojure/try-clojure)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
