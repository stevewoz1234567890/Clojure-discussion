# Grid Coordination — `clj-oa3-vtn` & public California price server

**[Grid Coordination](https://grid-coordination.energy/)** publishes **OpenADR 3**–oriented **Clojure** libraries for grid signaling and dynamic pricing. This note follows up on that stack with two concrete pieces: a **production VTN** implementation and a **live price service** you can call without credentials.

## `clj-oa3-vtn` — OpenADR 3.1.0 Virtual Top Node (VTN)

**[clj-oa3-vtn](https://github.com/grid-coordination/clj-oa3-vtn)** ([`energy.grid-coordination/clj-oa3-vtn`](https://clojars.org/energy.grid-coordination/clj-oa3-vtn)) is a **production** **OpenADR 3.1.0** **VTN** server in Clojure.

- **Two-port HTTP architecture** — **BL** (business logic) and **VEN** (virtual end node) roles on separate ports (defaults **8081** / **8080**): trusted BL clients get full CRUD; the VEN port is public read-oriented by default.
- **MQTT** — create/update/delete notifications via **Mosquitto** (e.g. **Eclipse Mosquitto**); topic discovery matches the **OpenADR 3.1.0** notifier model.
- **Storage** — **in-memory** (optional **duratom** file persistence) or **DynamoDB** via **Cognitect [aws-api](https://github.com/cognitect-labs/aws-api)**.
- **Routes from the spec** — HTTP surface is driven from the **OpenADR 3.1.0** OpenAPI spec with **[Legba](https://github.com/mpenet/legba)** (OpenAPI → Ring routes), not hand-written endpoint stubs; entities and coercion align with **[clj-oa3](https://github.com/grid-coordination/clj-oa3)**.
- **Runtime** — **[Component](https://github.com/stuartsierra/component)** lifecycle, **[mulog](https://github.com/BrunoBonacci/mulog)** structured logging, **Jetty** + Ring.

Upstream **README** documents phase-1 scope (programs, events, subscriptions, MQTT, pagination, auth stubs) and not-yet-implemented items (OAuth2, webhook notifications, etc.).

## Public price server

**Base URL:** [https://price.grid-coordination.energy/openadr3/3.1.0](https://price.grid-coordination.energy/openadr3/3.1.0)

A **live** electricity **price** VTN composed from the same Clojure stack: **`clj-oa3-vtn`**, **[clj-gridx](https://github.com/grid-coordination/clj-gridx)** (GridX API client), **[clj-oa3](https://github.com/grid-coordination/clj-oa3)** entities, plus **Component**, **mulog**, **duratom**, and **aws-api** (DynamoDB + SSM). It serves **hourly marginal prices** from the **CAISO Day-Ahead Market** for **PG&E** and **SCE** rate schedules — **492 programs** across **9 tariffs**, with substantial **history** and a **7-day forward** window (details in the user guide). **No authentication** is required for read access.

**MQTT push:** broker at **`mqtt.grid-coordination.energy`** (see [notifiers](https://price.grid-coordination.energy/openadr3/3.1.0/notifiers) and the [MQTT guide](https://github.com/grid-coordination/price-server-user-guide/blob/main/mqtt-notifications.md)).

Deployment (per announcement): **AWS ECS Fargate** with a **self-hosted Mosquitto** broker for notifications.

## Clojure tutorial (client + charting)

The **[price-server-user-guide](https://github.com/grid-coordination/price-server-user-guide)** includes **[tutorial-clojure.md](https://github.com/grid-coordination/price-server-user-guide/blob/main/tutorial-clojure.md)** — connect with **[clj-oa3-client](https://github.com/grid-coordination/clj-oa3-client)**, list programs, pull hourly prices, and chart with **[oz](https://github.com/metasoarous/oz)** / **Vega-Lite**.

## Related repositories

| Repo | Role |
|------|------|
| [clj-oa3-vtn](https://github.com/grid-coordination/clj-oa3-vtn) | VTN server |
| [clj-oa3](https://github.com/grid-coordination/clj-oa3) | OpenADR 3 entities / API |
| [clj-oa3-client](https://github.com/grid-coordination/clj-oa3-client) | VEN/BL Component clients |
| [clj-gridx](https://github.com/grid-coordination/clj-gridx) | GridX client |
| [clj-oa3-test](https://github.com/grid-coordination/clj-oa3-test) | Integration tests |
| [price-server-user-guide](https://github.com/grid-coordination/price-server-user-guide) | Docs, curl walkthrough, language tutorials |

## Coordinates (check Clojars for current versions)

```clojure
energy.grid-coordination/clj-oa3-vtn {:mvn/version "0.7.0"}
;; clj-oa3, clj-oa3-client, clj-gridx — see https://clojars.org/groups/energy.grid-coordination
```

## Credits

**[grid-coordination](https://github.com/grid-coordination)** (Clark Communications Corporation) and contributors; standards context at [grid-coordination.energy](https://grid-coordination.energy/).
