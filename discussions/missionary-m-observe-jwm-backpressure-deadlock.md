# Missionary — **`m/observe`** backpressure deadlock when emitting **burst** synchronous events (**JWM**)

[**Missionary**](https://github.com/leonoel/missionary) (**`missionary`** on Clojars) exposes reactive flows **`m/observe`** with **backpressure-aware** attaches.

[**Estevo U.C. Castro**](https://github.com/euccastro) (**`euccastro`**) asked in-thread: if **`m/observe`** wraps **single-threaded** producer that emits **more than once** **before **`m/observe`** returns** (example: a **[JetBrains/jwm](https://github.com/JetBrains/jwm)** window firing both **creation** and **immediate resize** callbacks while the synchronous boot path still stacks), callers can **deadlock**—nothing downstream has attached yet so the pull-based consumer never drains the **first** event.

Desired longer term: once running, producers should stay **pressure-driven** (**no buffering** between window and sinks).

---

## Maintainer hint — **defer bootstrap emissions**

**[`leonoel`](https://github.com/leonoel)** (Missionary author) floated pushing the **opening burst** onto the **next animation frame** (or similar **async** poke) so the subscriber exists before values leave the toolkit.

[**`euccastro`**](https://github.com/euccastro) clarified that with **JWM** the culprit was stacking **creation** payloads on the same synchronous edge as **`setVisible`/show** callbacks; delaying the **`show`** call cleanly separated timelines so **Leonardo’s** sequencing trick became viable.

**Leonardo** expressed interest in a concise **Missionary × JWM** sample in public.

---

## Takeaway

- Treat **`m/observe`** as coupling **lifecycle + pull** semantics: **avoid multi-emit** before the attaching fiber can **park**/`step`.  
- For GUI SDKs emitting **paired** synchronous notifications, **`show`**/visibility edges are often simpler to reschedule than rewriting the toolkit.

## Links

- [leonoel/missionary](https://github.com/leonoel/missionary)  
- [JetBrains/jwm](https://github.com/JetBrains/jwm)  

Thanks to [**@leonoel**](https://github.com/leonoel), [**@euccastro**](https://github.com/euccastro).
