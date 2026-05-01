# Clara Rules — **Oracle / Cerner** banner, stewardship, community forks

**[Clara Rules](https://www.clara-rules.org)** is a **forward-chaining** **[Rete](https://en.wikipedia.org/wiki/Rete_algorithm)**‑style rule engine for **Clojure**(Script).

The canonical OSS home is **[oracle-samples/clara-rules](https://github.com/oracle-samples/clara-rules)** (often still discovered via historical **`cerner/clara-rules`** URLs that redirect).

This note summarizes a **Clojurians** thread on **why the GitHub org looks “Oracle”**, how that lines up with the **Cerner** acquisition, and where **alternate codebases** are gathering while people ask whether the ecosystem can **converge** again.

---

## When did it land under **Oracle**?

Chat recollection aligns with **[Oracle's acquisition of Cerner](https://www.oracle.com/corporate/acquisitions/cerner/)**: **[Ethan Christian](https://github.com/EthanEChristian)** ([@EthanEChristian](https://github.com/EthanEChristian)) placed repo moves around **June/July 2022** — i.e., the period when Cerner folded into Oracle’s **`oracle-samples`** GitHub umbrella. Double-check **`git remote` history** when you care about precise transfer dates.

---

## What people worry about upstream

Observers noted **still‑active commits** beside **red CI** badges, **`CONTRIBUTING`** hoops (e.g. **Oracle contributor agreement** workflows), and a general feeling that **`oracle-samples/clara-rules` needs “more love”**. Treat **`main` / Clojars** and **Issues** as the live signal—forum chat goes stale overnight.

Ethan flagged **interesting community offshoots** (see below); **Ryan / devn** asked whether maintainers might **coordinate on one successor** (“answer … in progress” was the upbeat read).

---

## Public forks mentioned in‑thread

These are independent efforts—**different scope, licences, Clojars coordinates** apply; validate before pinning production deps:

| Fork | Maintainer / org | Rough focus (from README) |
|------|-------------------|---------------------------|
| **[k13labs/clara-rules](https://github.com/k13labs/clara-rules)** (*also forked historically as **`[gateless/clara-rules](https://github.com/gateless/clara-rules)`***) | **k13labs** | **Performance** work; **`tools.deps`** / **Make** ergonomics — **note: JVM‑only**, **no ClojureScript** in README. **[cljdoc artifact](https://cljdoc.org/d/com.github.gateless/clara-rules)** cites **`com.github.gateless`** — confirm current coordinates when migrating. |
| **[mrrodriguez/concrete-rules](https://github.com/mrrodriguez/concrete-rules)** | **Mike Rodriguez** ([@mrrodriguez](https://github.com/mrrodriguez), long‑time **`clara-rules`** contributor upstream) | **Forward‑chaining** rules (**“Concrete”** lineage) surfaced in chat as a **sovereign** experiment for users who want a **non‑Oracle** path. |

**`defrule`** + **clj-kondo** ergonomics surfaced in **`#clara`** around **October 2025** (`defrule` docstring shape vs **`Function name must be simple symbol`**); that **`#clara`** tangent is adjacent context for toolchain pain, not a fork endorsement.

---

## Thanks

Thanks to **`#clara`** participants — especially **Ethan Christian**, **Ryan (devn)**, and other voices mapping **ownership changes** onto **day‑to‑day** tooling choices—for keeping the lore honest.
