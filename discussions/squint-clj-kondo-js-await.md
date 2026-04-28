# Squint — shipped clj-kondo config for `js-await` / `await`

This note summarizes a **Clojurians Slack** thread about **clj-kondo** reporting **`:unresolved-symbol`** on **`js-await`** (and related **async** surface) in **`.cljs` / `.cljc`** when using **[Squint](https://github.com/squint-cljs/squint)** (and the same ecosystem concerns **Cherry**). Consumers could silence it locally with **`{:linters {:unresolved-symbol {:exclude [js-await await]}}}`**, but repeating that in every project is noisy.

## Maintainer take

**Miguel Muñoz** said a **PR** was welcome if it stayed **minimal and targeted**, and that **`js-await`** / **`await!`** are **user-facing** surface—shipping the suppression in **Squint / Cherry’s** bundled **clj-kondo** config avoids **repetitive friction** for everyone. (The filed issue repro uses **`await`** in **`:cljs`** branches; align excludes with whatever the compiler documents.)

## Upstream change

**Will Cohen** ([@willcohen](https://github.com/willcohen)) opened **[squint-cljs/squint#814](https://github.com/squint-cljs/squint/pull/814)** (“Exclude **js-await** and **await** from kondo linting”), linked to **[#813](https://github.com/squint-cljs/squint/issues/813)**, with **CHANGELOG** and a **`kondo_export_test`** guard.

*Validate against the merged tree and clj-kondo import path you use; Cherry may mirror the same pattern in its own resources.*

## Links

- [Squint — squint-cljs/squint](https://github.com/squint-cljs/squint)  
- [PR #814 — kondo exclusions](https://github.com/squint-cljs/squint/pull/814) · [Issue #813](https://github.com/squint-cljs/squint/issues/813)

## Credits

Thread participants (**Miguel Muñoz**, **Will Cohen**, and the reporter) and **Squint** maintainers.
