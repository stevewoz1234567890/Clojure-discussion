# jank — `xkb_state_key_get_syms` / `analyze/invalid-cpp-call` (`wonk/input.jank`) — community thread

This note summarizes a short debugging exchange about **[jank](https://github.com/jank-lang/jank)** **`cpp/`** calls to **libxkbcommon** from **`src/wonk/input.jank`** (Wayland / **wlroots** keyboard path in the **wonk** project). The **`analyze/invalid-cpp-call`** diagnostic reported **no overload** for **`xkb_state_key_get_syms`** even after argument types **appeared** to line up with the C header—**no** definitive fix was settled in-thread. Informal recap for searchability.

---

## C declaration (reference)

From **xkbcommon**, the relevant API is along the lines of:

```c
XKB_EXPORT int
xkb_state_key_get_syms(struct xkb_state *state,
                       xkb_keycode_t key,
                       const xkb_keysym_t **syms_out);
```

with **`xkb_keycode_t`** typically **`uint32_t`**.

---

## What showed up in jank

**dsouth** called (sketch):

```clojure
(let [wlr-keyboard (cpp/unbox (:* wlr_keyboard) (:wlr-reference keyboard-state))
      sym-size (cpp/xkb_state_key_get_syms
                 (.-xkb_state wlr-keyboard)
                 keycode
                 (cpp/& syms))]
  …)
```

with **`struct wlr_keyboard`** exposing **`struct xkb_state *xkb_state`**.

The compiler error evolved across edits:

1. **Argument 0** as **`struct xkb_state *&`** (reference-to-pointer), which looked **surprising** for a plain pointer parameter—possibly from **macro expansion** / how the **member access** or **`let`** binding fed the analyzer.
2. After changes, **argument 1** as **`jank::i64`** vs **`xkb_keycode_t`** (**`uint32_t`**), which suggested a **numeric width / signedness** mismatch from the **Jank** side.
3. After more tweaks, the error listed **`xkb_state*`**, **`xkb_keycode_t`**, **`xkb_keysym_t**`**—textually **matching** the C prototype—yet **still** “no matching call,” leaving **dsouth** stuck.

---

## Ideas raised (not confirmed)

- **Shantanu** wondered about **`XKB_EXPORT`** / **symbol visibility**; they expected a **clearer** error if the symbol were **not** visible, and noted it **should** be exported.
- **Third parameter `const`:** the C API takes **`const xkb_keysym_t **syms_out`**; whether **jank**’s **`cpp/&`** path preserves **`const`**-qualified pointer types **could** matter for **overload resolution** in the generated C++, even when “it looks fine” in the header snippet (thread **did not** verify this).

---

## Who said what (names as in the thread)

| Name | Contribution (summary) |
|------|------------------------|
| **dsouth** | **`input.jank`** call to **`xkb_state_key_get_syms`**; traced evolving **`analyze/invalid-cpp-call`** diagnostics; **member access** and **`keycode`** typing ruled out **obvious** mistakes to them. |
| **Shantanu** | **Visibility** / **`XKB_EXPORT`** hypothesis; **`const`** on **`syms_out`** as a possible subtle mismatch; asked for the **latest** **`let`** form for context. |

---

## See also

- [jank — throw-site traces, `cpptrace`, JIT symbols](jank-cpptrace-throw-site-jit-symbols.md) — other **`cpp/`** / codegen foot-guns
- [libxkbcommon — `xkbcommon.h` (upstream)](https://github.com/xkbcommon/libxkbcommon/blob/master/include/xkbcommon/xkbcommon.h) — **`xkb_state_key_get_syms`** declaration; match installed **version**

---

*Community note—jank’s C interop and diagnostics evolve; reproduce against current `main` and upstream headers.*
