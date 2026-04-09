# Practicalli nvim-astro — Clojure on Neovim 0.12 (AstroNvim)

**[practicalli/nvim-astro](https://github.com/practicalli/nvim-astro)** is a **Practicalli** Neovim configuration aimed at **Clojure** and related workflows on **Neovim 0.12**, built on the **[AstroNvim](https://github.com/AstroNvim/AstroNvim)** distribution.

## What it bundles (overview)

- **REPL-driven development** via **[Olical/Conjure](https://github.com/Olical/conjure)** — connect to nREPL (and friends) from the editor.
- **Structural editing** — **[nvim-paredit](https://github.com/julienvincent/nvim-paredit)** and **[nvim-parinfer](https://github.com/gpanders/nvim-parinfer)**, coordinated via **[parpar.nvim](https://github.com/dundalek/parpar.nvim)** so Paredit-style edits and Parinfer can coexist (from the AstroCommunity **Clojure** pack).
- **Tree-sitter** — syntax highlighting and incremental parsing for faster, smarter language-aware features.
- **Clojure LSP** — **`clojure-lsp`** installed automatically via **[Mason](https://github.com/williamboman/mason.nvim)** (diagnostics and LSP features without manual binary hunting).
- **Lazy loading** — packages loaded on demand to keep startup and idle resource use lower.
- **Git UIs** — **[LazyGit](https://github.com/jesseduffield/lazygit)** and **[Neogit](https://github.com/Neogit/neogit)** (Magit-like workflows compared to Emacs Magit or VS Code Edamagit).

## Release [2026-04-08](https://github.com/practicalli/nvim-astro/releases/tag/2026-04-08)

- **AstroNvim:** switch to the released **v6** line.
- **Practicalli layer:** remove **neo-tree**; use **`snacks.picker.explorer`** for file exploration (via [snacks.nvim](https://github.com/folke/snacks.nvim) picker integration on AstroNvim v6).
- **Community packs:** add a **markdown** pack including the **marksman** language server; add a **bash** pack for **zsh** and **bash** scripts.
- **Documentation:** README updated (repo name and overview).

## Links

- [GitHub — practicalli/nvim-astro](https://github.com/practicalli/nvim-astro)  
- [Release 2026-04-08](https://github.com/practicalli/nvim-astro/releases/tag/2026-04-08)  
- [Practicalli](https://practical.li/) — learning resources and tooling around Clojure and editors.

## Credits

Configuration and release notes from **[Practicalli](https://github.com/practicalli)**; stack credits belong to **AstroNvim**, **Conjure**, **Mason**, **LazyGit**, **Neogit**, and respective plugin authors.
