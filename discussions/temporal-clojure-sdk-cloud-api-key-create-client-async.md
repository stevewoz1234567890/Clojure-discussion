# Temporal Clojure SDK — Temporal Cloud API keys and `create-client` vs `create-client-async`

**[manetu/temporal-clojure-sdk](https://github.com/manetu/temporal-clojure-sdk)** (**[`io.github.manetu/temporal-sdk`](https://clojars.org/io.github.manetu/temporal-sdk)** on Clojars) exposes **[`temporal.client.core`](https://cljdoc.org/d/io.github.manetu/temporal-sdk/latest/api/temporal.client.core)** for workers and callers. Teams moving from self-hosted Temporal to **[Temporal Cloud](https://docs.temporal.io/cloud)** (including **GCP** regional endpoints such as **`europe-west3.gcp.api.temporal.io:7233`**) sometimes hit **`PERMISSION_DENIED: Request unauthorized`** from Clojure even when **the same API key configuration works with the Java SDK**.

This note summarizes a short community thread—the failure mode is subtle and the workaround is **`create-client-async`**, not a typo in `:api-key-fn`.

---

## Symptomatic code (fails against Temporal Cloud with API keys)

```clojure
(ns hello-temporal.main
  (:require [temporal.client.core :as c]))

(def client
  (c/create-client {:target "europe-west3.gcp.api.temporal.io:7233"
                    :namespace "namespace_name.account_id"
                    :enable-https true
                    :api-key-fn (fn [] "<api-key>")}))
```

The key, namespace string, `:enable-https`, and target can all be correct—as confirmed against working **Java** examples—and the Clojure call still refuses with **`PERMISSION_DENIED`**.

---

## Fix: use `create-client-async`

Maintainer **[Gregory Haskins](https://github.com/ghaskins)** ([@ghaskins](https://github.com/ghaskins)) points to unresolved behavior between **Temporal Cloud** and **`api-key`** auth when creating a **connected** client through the synchronous path ([**`create-client-async` on cljdoc**](https://cljdoc.org/d/io.github.manetu/temporal-sdk/1.6.0/api/temporal.client.core#create-client-async)).

Switch to **`create-client-async`** with the same map:

```clojure
(ns hello-temporal.main
  (:require [temporal.client.core :as c]))

(def client
  (c/create-client-async {:target "europe-west3.gcp.api.temporal.io:7233"
                          :namespace "namespace_name.account_id"
                          :enable-https true
                          :api-key-fn (fn [] "<api-key>")}))
```

Reporters confirm this resolves **`PERMISSION_DENIED`** for Cloud API keys.

Upstream context links to **`WorkflowServiceStub` / connectivity** tuning in SDK history: **[commit `e8d22bc`](https://github.com/manetu/temporal-clojure-sdk/commit/e8d22bccf3d149bc62209fb2a7cef58c95ac38eb)**.

---

## Why the synchronous client hurts (conceptual)

As **Juan José Vázquez Delgado** explained after it worked:

- **Temporal Cloud** API keys apparently **do not grant** whatever the **health check path** expects when stubs are built with **`WorkflowServiceStubs`** / **`newConnectedServiceStubs`**-style **eager connectivity**.
- The client **dies there** and **does not recover**—so you see **`Request unauthorized`** even though routing and secrets are otherwise fine.
- **`create-client-async`** avoids that failure mode for Cloud + API-key setups.

Naming gotcha raised in-thread: **`create-client-async`** suggests “only for async Temporal,” but here it signals **different stub construction**, not **`async`/promises vs blocking** semantics for your workflows. Maintainer noted intent to clarify docs.

---

## Thanks

Thanks to **Juan José Vázquez Delgado** for the reproducible Clojure example and persistence checking Java parity, **[Gregory Haskins](https://github.com/ghaskins)** for the production **`tcloud`** + API-key hint and **`create-client-async`** recommendation, and the **Manetu / temporal-clojure-sdk** contributors.
