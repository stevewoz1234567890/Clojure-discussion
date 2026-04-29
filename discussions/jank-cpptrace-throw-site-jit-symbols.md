# jank — throw-site traces, `cpptrace`, `CPPTRACE_TRY`, and JIT symbols

**[jank](https://github.com/jank-lang/jank)** is a native Clojure dialect (C++ / LLVM). A community thread asked how to **recover a throw-site stack trace as a value** when handling **C++ exceptions** that surface through **`cpp/`** interop—especially after **stack unwinding**, which **does not** preserve the frames between **throw** and **catch** the way a capture-at-throw mechanism would.

**[cpptrace](https://github.com/jeremy-rifkin/cpptrace)** can record traces at throw time and read them back (for example via **`from_current_exception`**-style APIs), but **jank’s Clojure `try` / `catch` lowering is not the same** as cpptrace’s **`CPPTRACE_TRY` / `CPPTRACE_CATCH`** macros. See **[Traces from all exceptions (`CPPTRACE_TRY` / `CPPTRACE_CATCH`)](https://github.com/jeremy-rifkin/cpptrace#traces-from-all-exceptions-cpptrace_try-and-cpptrace_catch)**.

## What maintainers said (summary)

**Jeaye** ([@jeaye](https://github.com/jeaye)):

- **C++** does not generally attach rich stack metadata to arbitrary **`std::exception`** objects; jank uses cpptrace internally, and **you can also use cpptrace** while linking against the same binary’s dependencies—**but** the practical pattern discussed was a **small C/C++ wrapper** that runs a jank thunk with **`CPPTRACE_TRY` / `CPPTRACE_CATCH`** and **`jank_call0`**, instead of relying on jank’s built-in **`try`/`catch`** alone.
- There is **no portable C++ guarantee** that “every exception carries a full throw-site backtrace” without explicit cooperation (macros, TLS, or similar).
- **Future polish:** jank **might** default generated exception handling to **`CPPTRACE_TRY` / `CPPTRACE_CATCH`** for better diagnostics, at a **performance** cost—worth investigation, not a promise.

**Richie** (thread repro):

- **Visibility / linking:** symbols for cpptrace inside the jank binary can be **non-exported** (`nm` **lowercase `t`** etc.), so **JIT’d `cpp/raw` alone** may not be a good place for **`CPPTRACE_TRY`**—a **normally linked** shared library that resolves against the running **`jank`** binary (e.g. **`-Wl,-undefined,dynamic_lookup`** on macOS) can work where inline JIT snippets do not.
- **Don’t link a second static `libcpptrace` expecting shared state:** a separate copy of cpptrace **will not** see the same internal storage jank uses.
- **Repro scenario:** **`nil`** passed into **`cpp/`** **`int`**-typed native code yields a **`what()`** string in **`catch`**, but **no jank frames** naming **`inner`/`outer`** in the vanilla handler—a wrapper using **`CPPTRACE_TRY`** yielded **native frames** (**`dynamic_call`**, **`apply_to`**, **`eval`**, etc.) but **JIT’d jank closures often appeared as bare addresses**.
- **`JANK_EXTRA_FLAGS=-g`** (and related debug/JIT-registration paths) **did not**, in Richie’s run, produce **readable symbols for those JIT frames**—**printf-style** logging remained the practical fallback.

## Links

- [jank-lang/jank](https://github.com/jank-lang/jank)  
- [cpptrace — exception traces (`CPPTRACE_TRY` / `CPPTRACE_CATCH`)](https://github.com/jeremy-rifkin/cpptrace#traces-from-all-exceptions-cpptrace_try-and-cpptrace_catch)

---

*Community note; behavior and codegen change over time—verify against current jank and cpptrace.*
