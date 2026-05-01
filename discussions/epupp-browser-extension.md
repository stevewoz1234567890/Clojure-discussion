# Epupp — Clojure (Scittle) REPL in the browser for live tampering & userscripts

**[Epupp](https://github.com/PEZ/epupp)** is a **browser extension** that starts a **Clojure REPL** in the page using **[Scittle](https://github.com/babashka/scittle)** (ClojureScript in the browser). You can **inspect, collect, and modify** any site from your **editor** or **AI harness**, and pair that with **userscripts** in a **Tampermonkey**-style workflow (target URLs, `document-start` / `document-end` / `document-idle`, etc.).

- **[Chrome Web Store](https://chromewebstore.google.com/detail/bfcbpnmgefiblppimmoncoflmcejdbei)**  
- **[Firefox Browser Add-ons](https://addons.mozilla.org/firefox/addon/epupp/)**  

**Safari** support is **not on par** with Chromium and Firefox yet, but the extension is still usable there in a limited way.

A **[starter template](https://github.com/PEZ/my-epupp-hq)** exists for humans and agents getting oriented.

## v0.0.15 — Epupp dependencies & fixes

**Epupp dependencies** — treat **Epupp `.cljs` code as libraries**:

- Builds on **`:epupp/inject`** manifest behavior.  
- **Local libraries** via the **`epupp://SCRIPT-NAME`** scheme inside the Epupp filesystem.  
- **Transitive dependencies** supported.  
- **External dependencies** — inject ClojureScript from **GitHub raw** hosts over **HTTPS**, including **gists**.  
- **Supported hosts:** `raw.githubusercontent.com`, `gist.githubusercontent.com`.  
- **SHA-pinned** resolution for **immutable, reproducible** deps.  
- Code is **fetched and cached when the script is saved**, then **injected from cache at page load**.

**Bugfix:** **`document-start`** userscripts were **run twice**; that is fixed.

Discussion and feedback: **`#epupp`** on the Clojureverse Slack (and issues on the repo).

## Community note — **sharing between userscripts**

**Peter Strömberg** ([@PEZ](https://github.com/PEZ) / **pez**) noted that dependency sharing should address **sharing code between userscripts** — with a ping to **@neumann** to report how it goes in practice.

## Userscripts vs **SPA navigation** (needs **refresh**) — Brave / GitHub Actions

[**sheluchin**](https://github.com/sheluchin) (Slack **`#epupp`**): a **userscript** that enhances **GitHub Actions** workflow pages (surfacing exact **workflow run timestamps** instead of hovering **“X minutes ago”**) often **does not run visibly** after **in-app navigation** (clicking links inside GitHub’s **SPA** shell), yet **shows up after F5**.

**Peter Strömberg** ([@PEZ](https://github.com/PEZ)) guessed **lazy-loaded** fragments plus **warm-cache** semantics on reload—your inject may fire **before** the subtree you scrape exists.

### Practical mitigation

1. **Describe repro as steps** (“open X, click Y, observe missing UI until refresh”) even without a repo—helps distinguish **routing** vs extension bugs (**PEZ**, in-thread).
2. **Poll** (or **`MutationObserver`**) until the DOM node(s) your script anchors on exist, then detach—pattern lives in Epupp’s **bundled web-installer helpers** (**PEZ**: “prior art … not easy to spot” amidst a large installer script).

### Environment

Report used **Brave** (Chromium). Same class of pitfalls applies to Chrome-likes whenever **turbo-/client-side** transitions skip a full **`load`** cycle your `@run-at` keyed on mentally.

## Links

- [GitHub — PEZ/epupp](https://github.com/PEZ/epupp)  
- [Chrome — Epupp: Live Tamper your Web](https://chromewebstore.google.com/detail/bfcbpnmgefiblppimmoncoflmcejdbei)  
- [Firefox — Epupp: Live Tamper your Web](https://addons.mozilla.org/firefox/addon/epupp/)  
- [Scittle](https://github.com/babashka/scittle)  
- [my-epupp-hq template](https://github.com/PEZ/my-epupp-hq)  
