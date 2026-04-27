# `org.clj-commons/pretty` 3.8.0 — bridge namespaces split out

**[`org.clj-commons/pretty`](https://clojars.org/org.clj-commons/pretty)** **[3.8.0](https://github.com/clj-commons/pretty/releases/tag/3.8.0)** — Pretty-printed exceptions (**ANSI**, smarter ordering, **name demangling**), **Hiccup-inspired** terminal markup, tabular/binary helpers, and related formatting utilities. Release highlights below paraphrase upstream **[CHANGELOG](https://github.com/clj-commons/pretty/blob/main/CHANGELOG.adoc)** / announcement materials.

## Breaking changes

- **Removed** namespaces **`io.aviso.exception`** and **`io.aviso.repl`** from this artifact.
- **Migration:** use **[`clj-commons/pretty-aviso-bridge`](https://github.com/clj-commons/pretty-aviso-bridge)** when you still need **`io.aviso.*`** compatibility layered on **`org.clj-commons/pretty`**.

This aligns with separating **`io.aviso`** compatibility from the core library so classpath clashes between legacy **`io.aviso/pretty`** and **`org.clj-commons/pretty`** are easier to reason about—see also **[Pretty — `io.aviso` vs `org.clj-commons`, overlapping namespaces](pretty-io-aviso-vs-clj-commons-namespaces.md)** for historical context.

## Other changes

- **`markdown`-style output** added for **`clj-commons.format.table`**, producing **Markdown-compatible** table syntax (when used with that style).

## Links

- [GitHub — clj-commons/pretty](https://github.com/clj-commons/pretty) · [Releases](https://github.com/clj-commons/pretty/releases)  
- [GitHub — clj-commons/pretty-aviso-bridge](https://github.com/clj-commons/pretty-aviso-bridge)  
- [Clojars — org.clj-commons/pretty](https://clojars.org/org.clj-commons/pretty)

---

*Community note; verify APIs and namespaces against the released JAR and upstream changelog.*
