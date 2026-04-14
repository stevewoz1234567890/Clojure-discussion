# cljfmt 0.16.4 — config validation & alignment fixes

**[cljfmt](https://github.com/weavejester/cljfmt)** ([James Reeves / Weavejester](https://github.com/weavejester)) is a **Clojure** source **formatter** (library, **CLI**, **Leiningen** plugin, **`clj -T`** tool, **Babashka** support). **[v0.16.4](https://github.com/weavejester/cljfmt/releases/tag/0.16.4)** (2026-04-13) is a small **bugfix / quality** release.

## Changes (from the announcement / CHANGELOG)

- **User configuration validation** — invalid or inconsistent **EDN** config is caught earlier ([#406](https://github.com/weavejester/cljfmt/pull/406)).
- **`:split-keypairs-over-multiple-lines`** — fixed behavior on the **first** key in a map ([#409](https://github.com/weavejester/cljfmt/pull/409)).
- **`:align-single-column-lines?`** — fixed handling for **last** elements in the relevant forms ([#411](https://github.com/weavejester/cljfmt/pull/411)).

## Coordinates

```clojure
dev.weavejester/cljfmt {:mvn/version "0.16.4"}
```

## Links

- [GitHub — weavejester/cljfmt](https://github.com/weavejester/cljfmt)  
- [Release 0.16.4](https://github.com/weavejester/cljfmt/releases/tag/0.16.4) · [CHANGELOG](https://github.com/weavejester/cljfmt/blob/master/CHANGELOG.md)  
- [Clojars — `dev.weavejester/cljfmt`](https://clojars.org/dev.weavejester/cljfmt)

## Credits

**[weavejester/cljfmt](https://github.com/weavejester/cljfmt)** maintainers and contributors.
