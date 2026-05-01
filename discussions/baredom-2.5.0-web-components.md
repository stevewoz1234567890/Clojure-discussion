# BareDOM v2.5.0 — Custom Elements UI kit (Shadow DOM + ESM)

**[BareDOM](https://github.com/avanelsas/baredom)** ([**Alexander van Elsas**](https://github.com/avanelsas) / **\@vanelsas**) is described as **90+** native **`<x-*>` UI components** authored in **ClojureScript** and shipped as framework-free **[Custom Elements v1](https://developer.mozilla.org/en-US/docs/Web/API/Web_components)**, **[Shadow DOM](https://developer.mozilla.org/en-US/docs/Web/API/Web_components/Using_shadow_DOM)**, **tree‑shakeable ES modules**, and **`custom-elements.json`**. Consume via **[`\@vanelsas/baredom` npm](https://www.npmjs.com/package/@vanelsas/baredom)**, **`dist/` ESM bundles**, or **[`com.github.avanelsas/baredom` on Clojars](https://clojars.org/com.github.avanelsas/baredom)** (**`deps.edn`**: see **[README](https://github.com/avanelsas/baredom)** for current coordinate).

Announcements summarize **what changed since earlier posts** rather than exhaustive changelogs; **verify** **`package.json` / Clojars** for the semver you resolve (repo **history** evolves quickly).

---

## Highlights in the **v2.5.0**‑era announcement

- **TypeScript support** (`*.d.ts` alongside **`dist`** entry points — see npm **`exports`** in upstream **`package.json`**).
- **Debug mode** — in‑page overlay (outline components, hover tag names, inspect / tweak live internal state — **no** browser extension).
- **`integrity.json`** — CI/build emits **Subresource Integrity** hashes for hashed assets (**SRI**‑friendly CDN use).
- **`<x-welcome-tour>`** — guided onboarding (spotlight, connectors, focus trap, keyboard navigation).
- **`<x-file-upload>`** — drag‑and‑drop uploads.
- **`<x-popover>`** — new **`portal`** attribute to dodge **CSS stacking‑context traps**.
- **`baredom.utils.overlay`** — shared overlay anchor logic (refactors **`<x-context-menu>`** internals without behavior churn).
- **`<x-tooltip>`** — delay, four placements, rich default slot content, **`WAI-ARIA`** pattern.
- **`<x-combobox>`** — type‑ahead, keyboard nav, **ARIA**.
- **`<x-skeleton-group>`** — synchronized skeleton pulse + presets.
- **`<x-image>`** — optional **aspect-ratio** cage, shimmer while loading, fade‑in success, configurable error fallback.
- **`<x-icon>`** — themed wrapper around a **slotted `<svg>`**.

---

## Demos / companion tooling

- **Component demos** — **`https://avanelsas.github.io/baredom/`**.
- **UI → designer / inspector** — **[Bareforge](https://avanelsas.github.io/bareforge/)** (**`https://avanelsas.github.io/bareforge/`**).

Community support: **`#baredom`** on **Clojurians Slack**.

---

## Thread notes (Safari **`x-button` demo** event log)

**Joe Lane** reported an empty **event log** on **Mobile Safari** tapping buttons on [`x-button` sunset demo](https://avanelsas.github.io/baredom/demo/x-button.html?theme=sunset).

**Alexander** clarified the live sample **only attaches logging to the Primary button**, so other buttons deliberately stay quiet—not necessarily a WebKit bug. A follow‑up tweak was floated for better mobile coverage (author lacked an iOS device at the instant reply).

Friendly adoption signals from **Asier** and encouragement from Joe on leaning into **actual web‑platform primitives**.

Thanks **Alexander van Elsas**, early adopters, and thread participants.
