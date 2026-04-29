# ClojureScript — testing macros without Clojure-style eval and thrown?

People sometimes write Clojure tests that combine `clojure.test` / `thrown?` / `eval` to probe macros:

```clojure
(is (thrown? Exception (eval `(my-macro 1 2 3))))
```

A thread asked for the same pattern on plain ClojureScript (not focusing on self-hosted builds).

Informal Clojurians summary—defer to ClojureScript compiler docs and project docs for authority.

---

## Themes

### Where macros run (plain ClojureScript)

The usual `cljs` compiler executes macro bodies on the JVM inside Clojure during compilation and emits JavaScript afterward. Generated JS does not ship alongside `clojure.core/eval` and `macroexpand` the way Clojure test namespaces do on the JVM runtime.

Noah Bogart ([@NoahTheDuke](https://github.com/NoahTheDuke)) agreed: ordinary `cljs.test` namespaces in the target environment do not provide `eval` or `macroexpand` for exercising macros like that.

Sean Corfield ([@seancorfield](https://github.com/seancorfield)) floated `macroexpand` instead of `eval` when testing on Clojure, then remarked that macros are already expanded before the emitted JavaScript runs.

**p-himik** ([@p-himik](https://github.com/p-himik); GitHub displays **Eugene Pakhomov**) contrasted JVM compilation work with executing JavaScript afterward; **self-hosted** ClojureScript is a distinct stack that the thread parked.

### Why `eval` on Clojure does not isolate expand-time faults

Even on the JVM, `thrown?` wrapping `eval` can fail because the expanded code is malformed—macros may legally expand to gibberish like `(1)` without raising during macro expansion; failure appears only once evaluation proceeds.

Sean Corfield noted phrasing ambiguity: saying "macro expansion failed" may mean compile-time analyzer errors versus emitting bad downstream forms.

### Practical workflows mentioned

- Exercise `defmacro` definitions from `.clj` / `.cljc` Clojure test paths on the JVM (`macroexpand`, `eval`), where those APIs exist.

- Exercise `cljs` sources by asserting compile errors from watchers or CI (Shadow CLJS, Kaocha CLJS helpers, analyzer messages, etc.).

---

## Credits

Thread participants: [p-himik](https://github.com/p-himik) (Eugene Pakhomov), [Noah Bogart](https://github.com/NoahTheDuke), [Sean Corfield](https://github.com/seancorfield).

---

## See also

- [ClojureScript compilation](https://clojurescript.org/guides/cljs-compilation)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
