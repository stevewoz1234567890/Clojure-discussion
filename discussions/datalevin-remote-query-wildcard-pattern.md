# Datalevin — **`d/q`** wildcard **`_`** on **`dtlv://`** vs local storage

Informal recap of a thread about **[Datalevin](https://github.com/juji-io/datalevin)** (`juji-io/datalevin`): **`datomic.api/q`**-style queries behave differently when the database is **local** (`./data`) versus **remote** (**`dtlv://…`**).

## Symptom

With an entity **`{:db/id 1 :dataset/name "foo"}`**:

**Local** (`./data`):

```clojure
(d/q '[:find ?e :in $ :where [?e :dataset/name _]] @conn)
;; => #{[1]}

(d/q '[:find ?e :in $ :where [?e :dataset/name "foo"]] @conn)
;; => #{[1]}
```

**Remote** (`dtlv://…`):

```clojure
(d/q '[:find ?e :in $ :where [?e :dataset/name _]] @conn)
;; => #{}

(d/q '[:find ?e :in $ :where [?e :dataset/name "foo"]] @conn)
;; => #{[1]}
```

So **pattern / don’t-care value** **`_`** in the datom clause appears **broken over the wire**, while a **ground literal** still matches.

## Maintainer response

**Huahai** ([@huahaiy](https://github.com/huahaiy)) thanked the reporter and asked for a **more complete repro** (their quick attempt did **not** reproduce). Also requested **version**, **platform**, and related context.

## Reporter constraints

**Ryan Schmukler** ([@rschmukler](https://github.com/rschmukler)): **v0.10.7** on **macOS** locally and **v0.10.7** on **Linux** remotely; **no capacity** to produce a minimal repro — acknowledged that would make diagnosis harder.

## Suggested next step

**Huahai**: try **`master`** — a **stale metadata** bug on the **server** may already be fixed upstream relative to **v0.10.7**.

---

## Links

- [juji-io/datalevin](https://github.com/juji-io/datalevin)  

Thanks to **Ryan Schmukler**, **Huahai**, and thread participants.

*Treat as **open** until confirmed fixed in a release you run; re-test **remote** vs **embedded** after upgrading.*
