# clj-kondo — **`--copy-configs`**, **`:config-paths`**, and **`.clj-kondo/imports/`** duplication

This note summarizes a **Clojurians Slack** thread about **`clj-kondo --copy-configs`** when **`:config-paths`** already points at **local** exported config (example: **`../resources/clj-kondo.exports/this-repo`** from **`.clj-kondo/config.edn`**): copied hooks land under **`.clj-kondo/imports`**, **duplicating** the same definitions and risking **drift** while editing **hooks**.

## Practical guidance (as of thread)

**Michiel Borkent** ([@borkdude](https://github.com/borkdude)):

- Prefer **not** running **`--copy-configs`** when you do not want that copy behavior.
- If **clojure-lsp** triggers **`copy-configs`** for you, alternatives floated include **excluding** the relevant path **from the classpath** clojure-lsp sees (**“somehow”** — workspace-specific; confirm current **clojure-lsp** settings/docs).

**Noah Bogart** ([@NoahTheDuke](https://github.com/NoahTheDuke)) echoed **avoid `--copy-configs`** while noting **clojure-lsp** may run it **automatically**, which creates friction for this workflow.

## Design discussion

**Noah** observed that a common motivation for **`copy-configs`** is skipping repeated **JAR** scanning for bundled **`clj-kondo.exports`** — and wondered whether **`:config-paths`** that reference **plain directories** (not JARs) ought to participate in the same copy step.

**Borkdude**: exported configs are **not only** pulled from JARs; the distinction is not as simple as “local vs jar.”

## Roadmap hint

**Borkdude** expects **less reliance on copying** later when **clj-kondo** grows configuration that **explicitly separates dependency-derived exports from local sources** — tentatively **later the same year** as the thread (informal). Separate quip: **Babashka** **`bb` configuration** is slated **first** (**“first .. bb conf :)”**).

**Noah**: comfortable **not** filing GitHub issues immediately and **waiting** on that direction.

---

## Links

- [clj-kondo — GitHub](https://github.com/clj-kondo/clj-kondo)  
- [clojure-lsp — GitHub](https://github.com/clojure-lsp/clojure-lsp) (behavior around **`copy-configs`** may evolve — check docs/issues)  

Thanks to thread opener, **[@NoahTheDuke](https://github.com/NoahTheDuke)**, and **[@borkdude](https://github.com/borkdude)**.

*Tooling behavior changes release to release — validate against your **clj-kondo** and **clojure-lsp** versions.*
