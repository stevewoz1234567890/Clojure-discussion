# Garden `run` vs `deploy` — local `:local/root` deps and production parity

This note summarizes a short **Clojurians** thread about using **different dependencies** when developing locally (e.g. **`garden run`**) versus what ships after **`garden deploy`**: in particular, a **normal coordinate** in production and a **`:local/root`** override while hacking on a library. It is informal chat, not product documentation; confirm behavior against **[Garden CLI](https://github.com/nextjournal/garden-cli)** releases and your own `deps.edn`.

---

## Problem (as stated)

Someone maintains a library they consume from an app: they want a **published / versioned dependency** in **production**, but **`:local/root`** to the checkout when working locally. Their workaround was **editing `deps.edn` by hand** (commenting lines in and out) and **avoiding commits** for those edits.

They valued **`garden run`** so the **local environment matches production** (they use **Garden storage** and planned to use **Garden ID**). They were unsure whether **`garden run`** could select a **`deps.edn` alias** (they suggested something like **`garden run --alias :dev`** would be ideal).

---

## Takeaways from the thread

### Aliases and a plain REPL

**[@jackrusher](https://github.com/jackrusher)** suggested the usual **tools.deps** pattern: put **local overrides** in a **`:dev`** (or similar) **alias** and start the **REPL** with that alias, instead of mutating the base `deps.edn`.

That trades **Garden-managed `run`** for **Clojure CLI + alias** on the machine where you want local paths.

### Feature request: alias support on `garden run`

The original poster planned to **open a GitHub issue** asking for a way to pass **deps aliases** into **`garden run`** so local overrides do not require abandoning Garden’s local orchestration.

**Tracking:** [nextjournal/garden-cli#9 — *Request: ability to run garden with extra aliases*](https://github.com/nextjournal/garden-cli/issues/9) (opened by **[@teodorlu](https://github.com/teodorlu)**); example desired UX: **`garden run --aliases :dev`**.

### Lambda Island Launchpad (and prod classpath)

**JAtkins** mentioned **[Lambda Island Launchpad](https://github.com/lambdaisland/launchpad)** as their default workflow; they ran into **classpath** surprises when **deploying to production** compared to what Launchpad assembled locally, and floated the idea of a **plugin** that could **compare or warn** about **classpath divergence vs prod**.

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|--------------------------|
| **teodorlu** | Wants **prod coords** + **`:local/root`** locally; manual `deps.edn` toggles today; prefers **`garden run`** for parity with **storage** / **Garden ID**; filed **garden-cli#9** for **`garden run`** alias passthrough. |
| **jackrusher** | Use a **local-dev alias** and start the **REPL** with it; or **request** **`garden run`** support for choosing an alias. |
| **JAtkins** | **Launchpad** for daily dev; watch for **classpath vs prod** when using tooling that assembles the classpath differently. |

---

## See also

- [Garden CLI repository](https://github.com/nextjournal/garden-cli)  
- [Issue #9 — extra aliases for `garden run`](https://github.com/nextjournal/garden-cli/issues/9)  
- [Clojure CLI — deps.edn reference](https://clojure.org/reference/deps_and_cli) (aliases, `:local/root`)  
- [Lambda Island / Launchpad](https://github.com/lambdaisland/launchpad)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
