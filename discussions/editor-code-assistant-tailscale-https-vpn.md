# ECA + Tailscale — desktop vs mobile installs, HTTPS, and `tailscale cert`

Short **Clojurians** thread on **Tailscale** for **[Editor Code Assistant (ECA)](https://eca.dev/)**: install **Tailscale** on **which** devices, what **HTTPS** setup requires, and when **`tailscale cert`** applies **vs** **`tailscale serve`**.

Informal recap—**[ECA Remote / VPN](https://eca.dev/config/remote/#vpn--tailscale)** is the authoritative starting point (**Eric Dallo** routed testers there).

---

## Install on desktop, phone, or both?

**Eric Dallo ([@ericdallo](https://github.com/ericdallo))**: **both** — run **Tailscale** on the **machine hosting ECA** **and** **on the mobile client** that opens the web UI.

---

## HTTPS: admin console, Serve, and `tailscale cert`

The thread cited **[Tailscale — set up HTTPS certificates](https://tailscale.com/docs/how-to/set-up-https-certificates)**. Docs describe running **`tailscale cert`** on machines that **materialize TLS certificate files** on disk.

**chromalchemy** asked whether that **`tailscale cert`** step applies on a **phone** (ambiguous on iOS).

**Paraphrasing ChatGPT-assisted notes shared in-thread:**

- Stacks such as **`tailscale serve`** in **HTTPS** mode act as Tailscale’s **HTTPS** front—enable **HTTPS**/**MagicDNS** pieces in the **admin console**; **`tailscale cert`** is **often unnecessary** purely to get **Serve** working.
- **`tailscale cert`** targets **manual PEM files**—you inherit **renewal** obligations unless something else rotates them.
- **ECA** remote access expects **`web.eca.dev`** to reach your server via **browser-valid HTTPS**—the **documented Tailscale recipe** favors **HTTPS Serve** rather than **raw TCP tunnels alone**.

**Eric** prompted validation against **[eca.dev — VPN / Tailscale](https://eca.dev/config/remote/#vpn--tailscale)** and invited gaps to be reported.

---

## Credits

**chromalchemy** · **Eric Dallo ([@ericdallo](https://github.com/ericdallo))**

---

## Links

- [ECA — Remote config · VPN / Tailscale](https://eca.dev/config/remote/#vpn--tailscale)
- [Tailscale — HTTPS certificates](https://tailscale.com/docs/how-to/set-up-https-certificates)

---

*If you spot a factual error or want attribution adjusted, open a PR or issue on this repository.*
