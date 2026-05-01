# Fulcro Statecharts — long-running flows (Datastar), timers, **`send`/`invoke`**, and custom protocols

[fulcrologic/statecharts](https://github.com/fulcrologic/statecharts): **Fulcro Statecharts** on the JVM with a **simple** interpreter **env**, **`core.async`**, and **[Datastar](https://data-star.dev)**-backed SSE pages.

Informal recap of a Clojurians thread (reporter unnamed in paste; **`tomc`** follow-up):

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

## **`tomc` follow-up**

Proposed adapting **`WorkingMemoryStore`**: **`save-working-memory!`** `(swap! storage assoc …)` plus **`notify session-id working-memory`** to drive side effects (**SSE** patches whenever persisted working memory changes).

**Upstream reply to that snippet was not captured in the thread paste.**

Alignment with **`awkay`’s guidance**: a **store**/protocol boundary that executes on **`save`** is consistent with composing **implementations rather than internals listeners** until/unless upstream adds instrumentation.

---

## Links

- [fulcrologic/statecharts](https://github.com/fulcrologic/statecharts)  
- Datastar homepage: **[data-star.dev](https://data-star.dev)**  

Thanks to **[Tony Kay](https://github.com/awkay)**, **`tomc`**, and **`#fulcro`** / thread participants.
