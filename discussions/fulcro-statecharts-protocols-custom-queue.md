# Fulcro Statecharts — long-running flows (Datastar), timers, **`send`/`invoke`**, and custom protocols

[fulcrologic/statecharts](https://github.com/fulcrologic/statecharts): **Fulcro Statecharts** on the JVM with a **simple** interpreter **env**, **`core.async`**, and **[Datastar](https://data-star.dev)**-backed SSE pages.

Informal recap of Clojurians **`#fulcro`** threads (reporter unnamed in first paste; **`tomc`** follow-ups):

## Reporter questions (paraphrase)

1. **`event-queue`** — Do completed sessions (**`:com.fulcrologic.statecharts/running?`** **false**) keep rows in **`event-queue`** and get cleaned eventually? (**Reporter resolved abandon cleanup independently** in-thread; see below.)

2. **Abandoning idle sessions** — Three approaches debated; **blocking issue**: parallel **timer** region with **`sce/invoke`** (`:future` + `Thread/sleep` + `simple/send!`) vs **`sce/send`** `:delay`; **exiting/re-entering the timer state did not cancel** the scheduled **`send`/future**, so timeouts still fired.

3. **Observing sessions** — **SSE patch** pushes when **configuration** or **data model** changes: is there **`session changed`** instrumentation?

---

## [**Tony Kay**](https://github.com/awkay) ([@awkay](https://github.com/awkay)) — design framing

Intent of heavy **protocol wiring** everywhere: callers can tailor behavior while staying on a semantically solid SCXML-style interpreter — e.g. user-editable charts loaded from UI/DB using **Sci** or string snippets.

- **`send` does not auto-cancel.** If targeting a stopped/missing session, the **external** **event queue is expected to drop** the delivery quietly.
- Want cancel-on-reset? **`on-exit`** (or comparable exit hook your model supports) calling an explicit **`cancel`** (or cancelling your own **`invoke`**) is the place to tie cleanup to transitions.
- The chart is fundamentally **driven off the external queue** (`core.async`-backed **simple queue** bundled as a sketch, not elevated to “only/production” ergonomics).

Practical deployments often implement **their own**:

- **`ExternalEventQueue`** (minimum), so enrichment / fan-out happens where events enqueue or dequeue.
- **Working storage**, **SQL-backed** queues, clustered execution — **months/years-later** timeouts (subscription workflows), survives restarts.

Parallelism: spin **workers** freely as long as **two threads never process the same `session-id` concurrently.**

Internal micro-step stabilization of the interpreter has **no first-class observers** documented in-thread — but the **micro-step algorithm protocol is plug-in replaceable**, so copying the default interpreter and inserting hooks is mechanically possible (**Tony Kay** hadn’t personally needed finer than **external-event** reactions).

---

### Working memory store vs internal queue (later clarification)

The **working memory store** is typically used by the **external** event queue to **load and persist** session memory **between external events**. The **internal** event queue **does not** cause storage events. Instrumenting **save/load** at that boundary therefore reflects **external-driven** evolution of configuration and data — **not** fine-grained visibility into **internal** movements (e.g. **`raise`**-driven micro-steps).

Per the **W3C** state-machine algorithm, processing an **external** event continues until transitions **stop causing movement**; the **internal** queue is a **simple FIFO** meant only to **stabilize** the chart — **no** timing, **no** “capabilities” toward the outside world. Unless the chart loops infinitely, that stabilization typically finishes in **microseconds**.

An external-event evaluation **blocks** only if **application code** blocks the thread; until the step completes, you are **not** in a settled configuration yet.

---

## **`tomc` — observing settled configuration / data model only

**Goal:** React when **configuration** or **data model** reaches a **new settled state** after **external** events — **without** subscribing to every internal transition.

**Approach discussed:** Hook **`WorkingMemoryStore`** (e.g. **`save-working-memory!`** `swap!` plus **`notify session-id working-memory`**) to drive **SSE** (or similar) when persisted working memory changes.

### Maintainer guidance on *where* to instrument

**Tony Kay** confirmed that implementing hooks **either**:

- in the **external event queue** implementation (pattern: **pull** working memory → **run** a step → **save**), **or**
- in the **working memory store** (triggered by that save path),

will achieve **settled-state** visibility for that use case.

**Semantic preference:** **`ExternalEventQueue`** is the cleaner seam because **only external events** (including **timed** work routed through that queue) change configuration in the intended model; the **`WorkingMemoryStore`** protocol might legitimately be touched **for other reasons** later, which could make store-level hooks **surprising** if they fan out as “session changed” notifications. A store-based solution **works**; queue-based instrumentation avoids coupling notifications to unrelated **`save`** callers.

---

## **Bugs reported — `:future` invoke, `:invoke-id`, and `finalize` ordering**

**`tomc`** reported (separate follow-ups in thread):

1. **`com.fulcrologic.statecharts.invocation.future`** — when the **`src`** completes, **`sp/send!`** omits **`:invoke-id`**, so **`finalize`** elements are **not** notified and **`finalize`** scripts may **not** run.

2. **Even after a local fix for (1)** — **data model** updates from **`finalize`** **`script`** elements appear to apply **after** transitions that should already see them, which **conflicts** with the spec (example: **`assign`** in **`finalize`** sets **`:foo`** from invoke result; a **`transition`** on **`:done.invoke.…`** with **`:cond`** reading **`:foo`** still sees **`nil`**).

**Tony Kay** (upstream): **finalize** is not heavily used in his own work; **`:future`** invoke was largely **demo** material — bugs there are plausible. **`finalize`** **should** match the spec; the implementation followed the standard algorithm — he would **analyze** the reports.

*(Treat resolution status as **upstream investigation** — verify against current **`fulcrologic/statecharts`** releases and issues/PRs.)*

---

## Links

- [fulcrologic/statecharts](https://github.com/fulcrologic/statecharts)  
- [W3C State Chart XML (SCXML)](https://www.w3.org/TR/scxml/) — normative processing model  
- Datastar homepage: **[data-star.dev](https://data-star.dev)**  

Thanks to **[Tony Kay](https://github.com/awkay)**, **`tomc`**, and **`#fulcro`** / thread participants.
