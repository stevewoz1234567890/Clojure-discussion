# jank — C API: no `char*` from strings (`jank_object_ref`); use C++

**[jank](https://github.com/jank-lang/jank)** exposes a **C ABI** (**`jank_object_ref`**, **`jank_call0`**, etc.) for embedding and native glue. A thread asked whether a native **C** function could obtain **`const char*`** (**UTF‑8**) for a **jank string** passed as **`jank_object_ref`**, or whether callers could rely on **jank-side** conversion **before** the call—the **C API** did not show an obvious string-extraction hook.

## Maintainer answer (summary)

**Jeaye** ([@jeaye](https://github.com/jeaye)):

- Getting string bytes that way **is not supported through the C API** as of that discussion—**use the C++ API** for access to **`jank::runtime`** string/value APIs.
- The **surface for embedding jank from native code was still intentionally limited**—“not really built out,” with focus on **Clojure developers** rather than full **native-developer** ergonomics first.

**dsouth**:

- Comfortable working without **it** for now; treating **C++** as something to pick up later.

## Takeaway

For **readable string contents** tied to **`jank_object_ref`**, plan on **C++** (wrappers bridging to **C**) **until/unless** the **C ABI** gains explicit helpers.

---

*Community note—verify against current jank repo and headers.*
