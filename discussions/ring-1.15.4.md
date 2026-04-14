# Ring 1.15.4 — Jetty & Apache Commons dependency bumps

**[Ring](https://github.com/ring-clojure/ring)** is the de facto **Clojure** HTTP request/response abstraction and a common foundation for **Ring**-compatible adapters and middleware. **[v1.15.4](https://github.com/ring-clojure/ring/blob/master/CHANGELOG.md#1154-2026-04-13)** (2026-04-13) is a **patch** release: **no API changes**, only **dependency** updates.

## Changes (from the announcement / CHANGELOG)

| Dependency | From | To | Notes |
|------------|------|-----|--------|
| **Eclipse Jetty** | 12.1.0 | **12.1.8** | [#552](https://github.com/ring-clojure/ring/pull/552) |
| **Apache Commons FileUpload** | 2.0.0-M4 | **2.0.0-M5** | [#551](https://github.com/ring-clojure/ring/pull/551) |
| **Apache Commons IO** | 2.20.0 | **2.21.0** | (changelog entry; no separate PR linked upstream) |

If you pin **Ring** artifacts explicitly, bump **`ring/ring-core`**, **`ring/ring-jetty-adapter`**, and related modules together per your usual stack (see [Clojars — Ring](https://clojars.org/ring)).

## Links

- [GitHub — ring-clojure/ring](https://github.com/ring-clojure/ring)  
- [CHANGELOG — 1.15.4](https://github.com/ring-clojure/ring/blob/master/CHANGELOG.md#1154-2026-04-13)  
- [PR #551 — FileUpload](https://github.com/ring-clojure/ring/pull/551) · [PR #552 — Jetty](https://github.com/ring-clojure/ring/pull/552)

## Credits

**[ring-clojure](https://github.com/ring-clojure)** maintainers and contributors.
