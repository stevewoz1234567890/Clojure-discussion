# Jetty (**Ring**) — **`SO_REUSEADDR`** and other tweaks via **`:configurator`**

Teams using **`[ring.adapter.jetty](https://github.com/ring-clojure/ring/tree/master/ring-jetty-adapter)`** (**`run-jetty`**) sometimes need low-level **`Server` / connector** knobs that **`run-jetty`’s** keyword options do not expose—including **`SO_REUSEADDR`** (via **`ServerConnector#setReuseAddress`**) on **[Eclipse Jetty](https://jetty.org/)**.

This note summarizes a Clojurians thread: **`:configurator`** is the supported escape hatch—you receive the fully built **`org.eclipse.jetty.server.Server`** before listen and mutate it with plain **Java APIs**.

Upstream entry points move with **Jetty** major lines; use **`import`** / **`:refer`** for **`Server`**, **`ServerConnector`**, **`Connector`**, **`ConnectionFactory`**, **`HttpConfiguration`**, **`UriCompliance`** (**`org.eclipse.jetty.*`** packages) aligned with your **`ring-jetty-adapter`** (**[Ring changelog](https://github.com/ring-clojure/ring/blob/master/CHANGELOG.md)**).

---

## Pattern: **`SO_REUSEADDR`**

**István Karaszi** iterated **`ServerConnector`** instances on the server:

```clojure
(defn configurator [^org.eclipse.jetty.server.Server server]
  (doseq [^org.eclipse.jetty.server.ServerConnector connector (.getConnectors server)]
    (.setReuseAddress connector true)))
```

Pass **`(jetty/run-jetty handler {:configurator configurator …})`** (alongside **`:port`** et al.). If you terminate **HTTPS** connectors too, **`doseq` over connectors** catches each listener you care about—verify each type you run locally supports **`setReuseAddress`** (typical **`ServerConnector`** implementations do).

---

## Same hook, different Jetty surface: **`HttpConfiguration` / Uri compliance**

**[Sean Corfield](https://github.com/seancorfield)** reported an analogous **`:configurator`** at work—not reuse, but **`UriCompliance`** for **backward-compatible URL parsing** after a **Jetty** upgrade (`JETTY_11`, **`ILLEGAL_PATH_CHARACTERS`** regime). **`ConnectionFactory`** instances vary by protocol; **`getHttpConfiguration`** is **not** on a tidy common superclass, so callers **`try`/catch`** per factory:

```clojure
(defn- configure-compliance [^org.eclipse.jetty.server.Server server]
  (doseq [^org.eclipse.jetty.server.Connector conn (.getConnectors server)]
    (doseq [^org.eclipse.jetty.server.ConnectionFactory factory (.getConnectionFactories conn)]
      (try
        (let [^org.eclipse.jetty.server.HttpConfiguration config (.getHttpConfiguration factory)]
          (.setUriCompliance config (org.eclipse.jetty.http.UriCompliance/from "JETTY_11,ILLEGAL_PATH_CHARACTERS")))
        (catch Exception e
          (logger/error "Error setting URI compliance" (ex-message e)))))))
```

Chat aside: **“Java APIs can be so awful”**—the connector/factory graph is the reason **`:configurator`** exists instead of fifty Ring keywords.

---

## Links

- **[ring-clojure/ring — `ring-jetty-adapter`](https://github.com/ring-clojure/ring/tree/master/ring-jetty-adapter)**  
- **[Related community note — Ring 1.15.4 (Jetty bumps)](ring-1.15.4.md)**

Thanks **István Karaszi** and **Sean Corfield**.
