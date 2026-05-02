# `clojure-mcp-light` — `clj-paren-repair` needs a **file path**, not **stdin**

This note summarizes a **Clojurians Slack** thread about **`clj-paren-repair`** from **[bhauman/clojure-mcp-light](https://github.com/bhauman/clojure-mcp-light)** when installed via **`bbin`** (example: **`bbin install https://github.com/bhauman/clojure-mcp-light.git --tag v0.2.2 --as clj-paren-repair --main-opts '["-m" "clojure-mcp-light.paren-repair"]'`**).

## The confusing example

Docs such as **[iwillig/clojure-system-prompt — `SYSTEM.md`](https://github.com/iwillig/clojure-system-prompt/blob/main/SYSTEM.md)** illustrate piping Clojure snippets into **`clj-paren-repair`**:

```bash
echo '(defn hello [x] (+ x 1)' | clj-paren-repair
```

Several participants reported **`Could not fix delimiter errors`** (or similar) for that pattern — reinstalling the **`bbin`** binary did not change behavior.

## What actually works

**`clj-paren-repair`** expects a **path to a file**, not **stdin**. Writing the broken form to disk and passing the path succeeds:

```bash
echo "(defn hello [x] (+ x 1)" > broken.clj
clj-paren-repair broken.clj
```

**Akiz** noted the tool works reliably when invoked from automation as **`clj-paren-repair "$file_path"`** (with stderr discarded / messaging around success vs failure).

## Maintainer note

**[Bruce Hauman](https://github.com/bhauman)** ([@bhauman](https://github.com/bhauman)): **`clj-paren-repair` does not take stdin**; he was **unsure** whether it **ever** did or whether that changed (**possible regression** vs **docs drift**). Flagged for follow-up investigation.

---

## Side thread (prompting)

**Akiz** linked **[The Register — “Telling an AI model that it's an expert makes it worse…”](https://www.theregister.com/2026/03/24/ai_models_persona_prompting/)** (persona-style **“you are an expert”** vs **exact instructions**) in passing.

**Tomas Brejla** ([@brdloush](https://github.com/brdloush)) clarified their immediate goal: **local Qwen 3.6 MoE** on a **24 GB** GPU with a small **“Clojure survival guide”** so **`clj-nrepl-eval`** fails less often — **`clj-nrepl-eval`** worked; **`clj-paren-repair`** via **`echo`** did not; they recalled **`clojure-mcp`** (“full” MCP) behaving differently in the past.

---

## Links

- [clojure-mcp-light](https://github.com/bhauman/clojure-mcp-light)  
- [clojure-system-prompt (`SYSTEM.md`) — iwillig](https://github.com/iwillig/clojure-system-prompt/blob/main/SYSTEM.md) *(example may assume stdin — verify against maintainer intent)*  
- [The Register — persona prompting study (2026-03-24)](https://www.theregister.com/2026/03/24/ai_models_persona_prompting/)  

Thanks to **Tomas Brejla**, **Akiz**, **Bruce Hauman**, and thread participants.
