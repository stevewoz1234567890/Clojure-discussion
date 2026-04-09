# Pomegranate 1.3.26 — RFC 9457, Maven transport, CVE hygiene

**[Pomegranate](https://github.com/clj-commons/pomegranate)** ([clj-commons](https://clj-commons.org/)) exposes a **Clojure**-friendly API over the **Maven Artifact Resolver** and supports **dynamic classpath** changes at runtime. **[v1.3.26](https://github.com/clj-commons/pomegranate/releases/tag/v1.3.26)** is the current line; the jump from **1.2.x** to **1.3.x** was driven by a **behavioral / transport** change (below). Full notes: **[CHANGELOG — v1.3.26](https://github.com/clj-commons/pomegranate/blob/master/CHANGELOG.adoc#v1.3.26)**.

## Highlights

### RFC 9457 “Problem Details” and default transport (**#233**)

Errors can be reported using **HTTP Problem Details** as defined in **[RFC 9457](https://www.rfc-editor.org/rfc/rfc9457.html)**. To support that pipeline, Pomegranate now defaults to **`HttpTransport`** instead of **`HttpWagon`**, and upgrades **`org.apache.maven.resolver/maven-resolver-transport-file`** and related resolver libraries. Contributed by **[@tcrawley](https://github.com/tcrawley)**.

### Security: `plexus-utils` (**#234**)

A **Maven dependency override** for **`org.codehaus.plexus/plexus-utils`** addresses **CVE-2025-67030**.

### Dependency hygiene (**#237**, **#239**)

Dependencies were **reviewed**: redundant artifacts removed, and items that remain **without direct code references** are **documented** so consumers understand why they stay on the classpath.

### Packaging: SCM URLs (**#229**)

**SCM** connection URLs in Pomegranate’s **POM** were corrected. Thanks to **[@ambrosebs](https://github.com/ambrosebs)**.

## Community

Discussion and support: **`#pomegranate`** on Clojurians Slack (and the wider **[clj-commons](https://clj-commons.org/)** ecosystem).

## Links

- [GitHub — clj-commons/pomegranate](https://github.com/clj-commons/pomegranate)  
- [Release v1.3.26](https://github.com/clj-commons/pomegranate/releases/tag/v1.3.26) · [CHANGELOG](https://github.com/clj-commons/pomegranate/blob/master/CHANGELOG.adoc#v1.3.26)  
- [PR #233](https://github.com/clj-commons/pomegranate/pull/233) · [PR #234](https://github.com/clj-commons/pomegranate/pull/234) · [PR #237](https://github.com/clj-commons/pomegranate/pull/237) · [PR #239](https://github.com/clj-commons/pomegranate/pull/239) · [PR #229](https://github.com/clj-commons/pomegranate/pull/229)  
- [RFC 9457 — Problem Details for HTTP APIs](https://www.rfc-editor.org/rfc/rfc9457.html)
