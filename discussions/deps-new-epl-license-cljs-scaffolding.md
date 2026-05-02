# deps-new — EPL license default vs readme, SPDX, cljs testing scaffolding

This note summarizes a short community exchange about **[deps-new](https://github.com/seancorfield/deps-new)** (`io.github.seancorfield/deps-new`): the **default license** shown in generated **`lib`** projects, **what shipped vs what the readme promises**, and appetite for **ClojureScript test scaffolding** (e.g. **shadow-cljs**). Informal chat; verify behavior against the **release you install** and upstream docs.

---

## What happened

Someone installed **deps-new** as documented (`clojure -Ttools install-latest … :as new`), ran **`clojure -Tnew lib :name myusername/mycoollib`**, and saw **`EPL-1.0`** in the generated project while the **upstream readme** suggested **`EPL-2.0`**.

Maintainer **[Sean Corfield](https://github.com/seancorfield)** clarified that the **EPL-2.0 readme alignment had not been released yet** at that moment (changes lived on **`develop`**). He pointed to the **`develop`** commit history and **`CHANGELOG.md`** on that branch for what was coming, mentioned planning a **v0.12.0** release, and noted limited feedback on the licensing tweak beforehand.

**Workaround (already available on the then-current line, cited as v0.11.x):** pass **`:license/id EPL-2.0`** when generating if you want **EPL-2.0** scaffolding regardless of the default template string.

The reporter thanked Sean, called out learning about **[SPDX](https://spdx.org/licenses/)** as a useful reference for license identifiers, and floated a **feature request**: optional scaffolding for **ClojureScript tests** (Node and/or browser via **shadow-cljs**) for **`cljc`** libraries.

Sean responded that he **does not do ClojureScript development** day to day, so **meaningful cljs additions would need one or more PRs** from people comfortable with that stack—while indicating openness in principle if contributors drive the work.

---

## Who said what (handles as in the thread)

| Handle | Contribution (summary) |
|--------|-------------------------|
| **jcoyle** | Observed **EPL-1.0** vs readme **EPL-2.0** after **`install-latest`** + **`lib`**; thanked maintainer; noted **SPDX**; suggested future **cljs / shadow-cljs** test scaffolding for **`cljc`** libs (non-urgent; possible future PR). |
| **seancorfield** | **Unreleased** readme vs behavior—check **`develop`** commits / **`CHANGELOG.md`**; **v0.12.0** timing; **`:license/id EPL-2.0`** override on **v0.11.x**; cljs scaffolding needs **community PRs**. |

---

## Links

- [GitHub — seancorfield/deps-new](https://github.com/seancorfield/deps-new)  
- [Commits on `develop`](https://github.com/seancorfield/deps-new/commits/develop/)  
- [CHANGELOG.md on `develop`](https://github.com/seancorfield/deps-new/blob/develop/CHANGELOG.md)  
- [SPDX License List](https://spdx.org/licenses/)  

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
