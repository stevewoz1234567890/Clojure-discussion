# Babashka / CircleCI — Intel **macOS** (`amd64`) natives without Intel Mac runners

**[Crispin Wellington](https://github.com/epiccastle)** (**[bbssh](https://github.com/epiccastle/bbssh)**) asked how to keep shipping **Intel (x86_64) Darwin** artefacts when **[CircleCI](https://circleci.com/) no longer exposes the old **Intel Mac build images** — and how **[Babashka](https://github.com/babashka/babashka)** itself still publishes those binaries (**[@borkdude](https://github.com/borkdude)** / **Michiel Borkent**).

Informal recap of the thread answers; CircleCI SKUs churn — **verify** resource classes against current docs before copying YAML verbatim.

---

## Is losing a dedicated Intel Mac executor a blocker?

Often **no**. **[borkdude](https://github.com/borkdude)** noted he has **released some pods without Intel macOS support** and **heard no complaints** — one pragmatic default is **ship ARM-only** until someone needs **x86_64** binaries, then revisit.

---

## How **Babashka** still builds **Intel macOS** today

On CircleCI, **`babashka`’s `:mac`** job targets **`amd64`** but runs on **Apple Silicon** executors (**`m4pro.large`** in **[`gen_ci.clj`](https://github.com/babashka/babashka/blob/master/.circleci/script/gen_ci.clj)**), not a physical Intel Mac:

1. **Install [Rosetta 2](https://support.apple.com/en-us/102527)** on the runner — the generated config includes a step  
   `sudo /usr/sbin/softwareupdate --install-rosetta --agree-to-license`  
   when the platform is **`mac`** (see the same **`gen_ci.clj`** file around the **`Install Rosetta`** step).
2. **Download and use the Intel (x86_64) GraalVM** via the existing **`script/install-graalvm`** wiring for that job’s **`BABASHKA_ARCH` / platform** (still **`amd64`** for **`:mac`** in **`make-config`**).

So the answer “how does bb do it?” is literally: **ARM Mac CI + Rosetta + Intel GraalVM toolchain**, not a separate Intel-only Mac pool.

---

## Takeaway for other **GraalVM-native** pods (e.g. **bbssh**)

- **Mirror** Babashka’s pattern in your own Circle config, **or**
- **Drop Intel mac** until a user asks, **or**
- **Self-host** / use another provider if you need a first-class **x86_64-mac** VM.

**Crispin** planned to read **`gen_ci.clj`** or otherwise ship without **amd64** if that was simpler.

---

## Thanks

Thanks to **Crispin Wellington**, **Michiel Borkent**, and thread participants sharing operational detail for **GraalVM**-shaped pipelines.
