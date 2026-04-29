# Datomic Pro 1.0.7266 — read-only connections and `rseek-datoms`

[Datomic Pro 1.0.7266](https://forum.datomic.com/t/datomic-1-0-7266-pro-now-available/2620) adds **read-only connections** against storage and backups, **`rseek-datoms`** as a complement to **`seek-datoms`** for reverse-index traversal, plus other tweaks listed in Cognitect materials for that build—see **forum announcement** and **`docs.datomic.com`** for your toolchain.

This is a concise community announcement note—not a substitute for Cognitect docs or deployment checklists.

---

## Read-only connections

Peers can open connections that intentionally **omit write paths**. Release messaging highlights scenarios such as sharing **read-only** access to archived databases (for example **S3** backups), running **without** a transactor when appropriate, benchmarking against **cold** snapshots, and **incident** workflows that must **not** write to production.

Documentation: start from **[docs.datomic.com](https://docs.datomic.com/)** and Cognitect-supplied artifacts for Build **7266**.

---

## `rseek-datoms`

`rseek-datoms` complements `seek-datoms` against the reverse indexes—handy wherever you already leaned on `seek-datoms` for forward traversal. The forum thread promised **recipe-style guidance for common patterns in the weeks after release.**

See **`datomic.api`** documentation for **`rseek-datoms`** on **[docs.datomic.com](https://docs.datomic.com/)** for arity and semantics on your toolchain.

---

## Other changes (summary label)

Upstream also cited **performance** work to **reduce log write amplification** on workloads with **many transactions**.

---

## Community reactions

- **Chris Houser (@cch1)** asked whether analogous **read-only** stories might ever reach **Datomic Cloud peers** atop **S3** segments—with different latency profiles—and mused implications for **Datomic Analytics** ("jealous").
- **Robert Stuttaford** congratulated the team and flagged excitement for continued **reverse-index** investment.

Links: [@cch1](https://github.com/cch1), [@robert-stuttaford](https://github.com/robert-stuttaford).

---

## Links

| Resource | URL |
|----------|-----|
| Forum announcement | [Datomic **1.0.7266** Pro **now available**](https://forum.datomic.com/t/datomic-1-0-7266-pro-now-available/2620) |
| Documentation root | **[docs.datomic.com](https://docs.datomic.com/)** |

Pro binaries and release notes distribute through Cognitect—use your organisation’s authenticated download portal.

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
