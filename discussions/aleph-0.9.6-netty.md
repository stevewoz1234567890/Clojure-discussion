# Aleph 0.9.6 — Netty CVE fix, Netty 4.2 roadmap, `data.json` vs Jackson

**[Aleph](https://github.com/clj-commons/aleph) [0.9.6](https://github.com/clj-commons/aleph/releases)** bumps **Netty** to **4.1.132.Final** to address **CVE-2026-33870** and **CVE-2026-33871** ([PR #781](https://github.com/clj-commons/aleph/pull/781)).

## Community thread (releases channel)

- **Netty 4.2.x:** A maintainer (**Moritz**) confirmed that a **move to Netty 4.2** is **underway**. For teams that only integrate with Netty directly, **4.1 → 4.2** may be straightforward; for **Aleph**, the migration is **more involved** (deeper coupling).
- **Upgrade risk:** Even **patch** releases of Java libraries can be breaking in edge cases; **minor** bumps add more surface area.
- **Staying current vs hard upgrades:** One shop tries to stay on latest versions where possible but keeps **“laggards”** when breaking changes are too costly—**Jackson** was named as a common pain point.
- **Avoiding Jackson:** **[Sean Corfield](https://github.com/seancorfield)** prefers not to depend on Jackson; **[ring-data-json](https://github.com/seancorfield/ring-data-json)** is **ring-json**-shaped middleware using **`org.clojure/data.json`** instead of Cheshire.
- **`data.json`:** Recent releases brought **large performance improvements**, which made switching viable; it also has **zero** extra Maven dependencies. Historically it was slower and more lenient than some alternatives.
- **Duplicate keys:** Jackson’s **`STRICT_DUPLICATE_DETECTION`** rejects duplicate object keys; the docs note a **~20–30%** parsing-time cost for basic parsing when enabled. **`data.json`** does not expose the same knob; duplicate-key handling was discussed as relevant for **security** (e.g. mitigating **parser differential** issues), with speculation that a native check in **`data.json`** might be **cheaper** than Jackson’s path. Someone planned to check **JIRA** for an existing enhancement request.

## Links

- [Aleph on GitHub](https://github.com/clj-commons/aleph) · [Releases](https://github.com/clj-commons/aleph/releases) · [PR #781](https://github.com/clj-commons/aleph/pull/781)  
- [ring-data-json](https://github.com/seancorfield/ring-data-json)  
- [Jackson `JsonParser.Feature.STRICT_DUPLICATE_DETECTION` (javadoc)](https://fasterxml.github.io/jackson-core/javadoc/2.9/com/fasterxml/jackson/core/JsonParser.Feature.html#STRICT_DUPLICATE_DETECTION)  
- [`org.clojure/data.json` on Clojars](https://clojars.org/org.clojure/data.json)

## Credits

Thread summarized from an **Aleph 0.9.6** release announcement and follow-up replies (**Sean Corfield**, **Moritz**). Thanks to **clj-commons** maintainers and participants.
