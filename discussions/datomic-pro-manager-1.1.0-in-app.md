# datomic-pro-manager 1.1.0 — start Datomic from your app

**[Datomic Pro Manager (DPM)](https://github.com/filipesilva/datomic-pro-manager)** ([`io.github.filipesilva/datomic-pro-manager`](https://github.com/filipesilva/datomic-pro-manager)) downloads, configures, and runs **Datomic Pro** backed by **SQLite** (or other SQL storage via config). It is a thin wrapper around the same commands you would run manually; it prints what it runs so you can reproduce the workflow without DPM later.

## What’s new in 1.1.0

You can depend on DPM **as a library** and **start the transactor from your application** instead of only using the `dpm` CLI.

- **`dpm/up`** — downloads and starts the transactor if needed (async-friendly when wrapped as in the example below).
- **`dpm/wait-for-up`** — blocks until the transactor is ready.
- **`dpm/db-uri`** — takes a database name and returns the full Datomic URI for that name (aligned with the URI DPM prints in its help output).

## Example (Peer + SQLite)

```clojure
(ns app
  (:require
   [datomic.api :as d]
   [filipesilva.datomic-pro-manager :as dpm]))

(defn start []
  (future (dpm/up))
  (dpm/wait-for-up)
  (let [uri (dpm/db-uri "app")]
    (d/create-database uri)
    (d/connect uri)))
```

The upstream README uses `(def conn (d/connect ...))` inside `start` for a minimal paste; in application code you usually return the connection, use `defonce`, or store it in a component map so you do not create a new var on every call.

You still need **`com.datomic/peer`** and, for SQLite storage, **`org.xerial/sqlite-jdbc`** in your `deps.edn`. If you are unsure of versions, run **`dpm`** once (CLI or after adding the library): it prints the **Deps** block with pinned coordinates. As of DPM **v1.1.0**’s documented flow, typical versions look like:

```clojure
{:deps
 {io.github.filipesilva/datomic-pro-manager {:git/tag "v1.1.0" :git/sha "1bb3ed5"}
  com.datomic/peer {:mvn/version "1.0.7277"}
  org.xerial/sqlite-jdbc {:mvn/version "3.47.0.0"}}}
```

(Adjust to match what `dpm` reports for your environment.)

You also need **`sqlite3`** on your `PATH` for DPM’s SQLite setup, and the usual **Datomic Pro** licensing terms apply for production use.

## Local CLI alias (same release)

If you only use the CLI, the same tag/sha works as a **`deps.edn`** alias:

```clojure
{:aliases
 {:dpm
  {:deps {io.github.filipesilva/datomic-pro-manager {:git/tag "v1.1.0" :git/sha "1bb3ed5"}}
   :main-opts ["-m" "filipesilva.datomic-pro-manager"]}}}
```

## Credits

Thanks to **[Filipe Silva](https://github.com/filipesilva)** for DPM and the **1.1.0** library integration.

## Links

- [GitHub — filipesilva/datomic-pro-manager](https://github.com/filipesilva/datomic-pro-manager) (installation, `dpm.edn`, backups, moving off SQLite)
- [Datomic documentation](https://docs.datomic.com)
- [Datomic Peer API (Clojure)](https://docs.datomic.com/clojure/index.html)
