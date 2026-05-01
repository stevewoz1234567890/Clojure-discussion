# Clojure-lsp — **startup / init** integration performance tests (**#2282**, **#2259**, **`performance-helper`**)

**[Juan Andrés Rodríguez](https://github.com/Jimmysit0)** (Slack/GitHub **`jimmysit0`**), **[Eric Dallo](https://github.com/ericdallo)**, and **JR** (contrib author of the helpers) iterated in **[#2259](https://github.com/clojure-lsp/clojure-lsp/issues/2259)** on exercising the **`performance-helper`** namespace (**[PR #2257](https://github.com/clojure-lsp/clojure-lsp/pull/2257)**)—notably **`execute-multiple`**, **`compute-percentile`**, **`report-percentiles`**—against the existing **LSP** harness (**`integration.lsp`**, **`integration.fixture`**), with the goal of a maintained **initialization** latency budget.

## Maintainer guidance (**Slack/GitHub crossover**)

**Eric** and **JR**: using those helpers this way is aligned with **[#2259](https://github.com/clojure-lsp/clojure-lsp/issues/2259)**; once baseline **startup perf** tests land, regressions become measurable fixes rather than anecdotes.

Integration **settings** ambiguity for contributors: see the authoritative catalogue at **[`docs/all-available-settings.edn`](https://github.com/clojure-lsp/clojure-lsp/blob/master/docs/all-available-settings.edn)**.

## ⚠ **`deftest`** is **one** server lifecycle (today)

**`jimmysit0`** asked whether **`lsp/notify!` `[:exit {}]`**, background thread cleanup, or similar could hot-restart **`clojure-lsp`** mid-**`deftest`** for repeated timings.

**Eric**: **today you get only one lifecycle per `deftest`**: slower, but avoids **in‑memory `atom`** state leaking between cases. **`:exit`/shutdown cleanup** remains a plausible future enhancement (**not** implemented when this thread ran).

Attempts at **`exit`** surfaced **`NullPointerException`** (uncaught) in experimentation—plan on **fixture-per-test** unless upstream teardown improves.

---

## PR #2282 — init performance integration tests

[**Add Performance integration tests for server initialization**](https://github.com/clojure-lsp/clojure-lsp/pull/2282) closes **`#2259`** and adds **integration** timings for the **initialization** phase and asserts **P50** latency stays within budget to guard **startup**. **Eric Dallo** called the approach promising in-thread.

Upstream run hints (PR body): **`bb native-cli`** → **`bb performance-test ./clojure-lsp`**.

Check **[#2282](https://github.com/clojure-lsp/clojure-lsp/pull/2282)** for merge status—it was **open** when this recap was drafted.

---

## Links

- [PR #2282 — init performance integration tests](https://github.com/clojure-lsp/clojure-lsp/pull/2282) · [Issue #2259](https://github.com/clojure-lsp/clojure-lsp/issues/2259) · [PR #2257 (`performance-helper`)](https://github.com/clojure-lsp/clojure-lsp/pull/2257)  
- [**`all-available-settings.edn`**](https://github.com/clojure-lsp/clojure-lsp/blob/master/docs/all-available-settings.edn)

Thanks to **[Jimmysit0](https://github.com/Jimmysit0)** for the PR spike, **[ericdallo](https://github.com/ericdallo)** maintainership, **JR**, and **`#clojure-lsp`** thread readers.
