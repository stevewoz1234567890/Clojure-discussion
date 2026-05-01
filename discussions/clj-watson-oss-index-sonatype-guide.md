# clj-watson — OSS Index migration to Sonatype Guide (2026)

**[clj-watson](https://github.com/clj-holmes/clj-watson)** wires **[OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)** into Clojure dependency trees. When you enable the **OSS Index** analyzer, credentials and endpoint behavior changed in **April 2026** because Sonatype **retired the classic OSS Index API** in favor of **Sonatype Guide**.

Upstream documentation was updated in **[README commit d1889ec](https://github.com/clj-holmes/clj-watson/commit/d1889ecf2e1b67d58937d5141cb2ef0822834c14)** ([@seancorfield](https://github.com/seancorfield)). This note restates the operational steps and adds context from community chat.

---

## What broke and why

OSS Index as previously integrated (URL + password-style auth) **stopped working** when the service moved. Tooling that still points at the old OSS Index host or uses only a legacy password will fail until you **retarget the analyzer** and **use a new API token**.

**OWASP Dependency-Check** is expected to adopt the new endpoint in **12.2.2**; until that (or a newer **clj-watson** default) is on your classpath, you should **override the analyzer URL** yourself if you rely on OSS Index data.

---

## What to configure

1. **Override the OSS Index analyzer URL** (for example in **`clj-watson.properties`**):

   - Property: **`analyzer.ossindex.url`**
   - Value: **`https://api.guide.sonatype.com`**

   Sonatype documents the migration at **[OSS Index migration steps](https://help.sonatype.com/en/oss-index-migration-steps.html)**.

2. **Account and token**
   - Sign in (or register) at **[Sonatype Guide](https://guide.sonatype.com/)**.
   - Create an **API token** (tokens typically start with **`sonatype_pat_`**). In the Guide UI this is under **API → Manage your tokens** after login.
   - Use that token as the **password** for OSS Index configuration (not your old OSS Index-only password). Username / env var names for **clj-watson** are unchanged from upstream docs: e.g. **`CLJ_WATSON_ANALYZER_OSSINDEX_USER`** and **`CLJ_WATSON_ANALYZER_OSSINDEX_PASSWORD`**, or **`analyzer.ossindex.user`** / **`analyzer.ossindex.password`** in **`clj-watson.properties`**.

3. **Optional: pin a newer Dependency-Check core**
   - You can add an explicit dependency on **`org.owasp/dependency-check-core`** if you want a newer engine than the one **clj-watson** pulls by default—at the cost of **you** keeping that coordinate current. As of the README update above, **`{:mvn/version "12.2.1"}`** was cited as the then-current line to consider.

---

## Hygiene

Do **not** commit tokens or passwords. Treat **`clj-watson.properties`** with secrets as **local-only** or inject credentials via environment variables in CI.

---

## Thanks

Thanks to [@seancorfield](https://github.com/seancorfield) for the upstream README update and public heads-up, [@lread](https://github.com/lread) and others for surfacing the timeline reality check in community channels, and the **clj-holmes** maintainers for **clj-watson**.
