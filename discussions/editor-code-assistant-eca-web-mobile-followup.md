# Editor Code Assistant (`eca-web`) — mobile QA after [issue **#1**](https://github.com/editor-code-assistant/eca-web/issues/1)

This note summarizes follow-up chatter after [**eca-web** `#1`](https://github.com/editor-code-assistant/eca-web/issues/1) was addressed: **iPhone-hotspot LAN** testing against a local **`eca`** stack, **`eca-webview` + `eca-web`** **deploy semantics**, flaky **“connection lost”** banners vs live streaming, **iOS** (**Chrome**/WebKit) **layout overflow** on a huge (~2.3 MB) chat transcript, inability to clear history on mobile, handset warmth/battery anecdotes, **where** to drop informal bug chatter.

Informal recap—defer to the **[editor-code-assistant](https://github.com/editor-code-assistant)** org on GitHub and maintainers for authoritative status.

---

## Browser reload vs VS Code extension

**Eric Dallo ([@ericdallo](https://github.com/ericdallo))**: relevant fixes touched **`eca-web`** and **`eca-webview`** server-side—they were already **live**, so testers only needed to **reload** the web UI (no standalone **ECA VS Code extension** rebuild for **that slice**).

---

## Network path (“last mile” flake)

Reporter **chromalchemy** ran the laptop across **Wi-Fi** tethered through an **iPhone hotspot** on the LAN. Drops were intermittent; **Tailscale** was floated as a possibly steadier tunnel.

---

## “Connection lost” UI vs concurrent streaming

Observers described **“connection lost / reconnecting” chrome** lingering while **streaming** still appended content. Occasionally a **successful reconnect** refreshed the SPA and snapped **viewport width** back sane. Persistent banner + ineffective **retry** controls appeared together with flaky transport and **long sessions**.

**Eric** asked to reduce variables (**one** concise chat **/ session**) while investigating; **additional improvements** landed later in-thread.

---

## Mobile layout regressions (**iOS / WebKit**) 

After reloading on handset:

- The **chat** column exhibited **horizontal overflow / scroll**, failing to constrain to **viewport**.
- The **jump-to-bottom** control partly reset left clipping, but width stayed exaggerated.
- A **massive** backlog (**~**2.3 **MB markdown**) was suspected of amplifying stress.

Reminder (thread): **all** **`iOS`** browsers—even **Chrome skins**—sit on **Apple’s WebKit mandate** (distinct from desktop Blink).

Eric **could not** reliably reproduce lacking Apple hardware yet agreed **mobile Safari-class** fidelity matters.

---

## Trash / clearing chat (**mobile**) 

Clearing chats via **`trash`** in **desktop VS Code** worked, yet **reload** on phone fetched the oversized prior transcript anyhow. **`trash`** in the **`mobile`** web UI appeared ineffective. Maintainer queued further investigation (**“cant clear chat on mobile” quote**).

---

## Thermal / workload hypothesis

Huge transcript held in-browser memory correlated anecdotally with **rising handset temperature** vs idle; maintainer conceded difficulty measuring but conceded plausibility when holding **megabyte-scale** conversational history resident.

---

## Where to paste informal bugs (**Slack**) 

Eric encouraged informal Slack notes alongside tighter GitHub issues when reproduction steps stabilize.

---

## Credits

**chromalchemy** (field QA) • **Eric Dallo** ([@ericdallo](https://github.com/ericdallo)) (maintainer triage.)

---

## Links

- [eca-web issue **#1**](https://github.com/editor-code-assistant/eca-web/issues/1)
- [`editor-code-assistant` org](https://github.com/editor-code-assistant)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
