# Practicalli nvim-astro — Clojure on Neovim 0.12 (AstroNvim)

**[practicalli/nvim-astro](https://github.com/practicalli/nvim-astro)** is a **Practicalli** Neovim configuration: a rich setup for **Clojure** development on **Neovim 0.12**, built on the **[AstroNvim](https://github.com/AstroNvim/AstroNvim)** distribution.

## What it bundles (overview)

- **Clojure REPL–driven development** via **[Olical/Conjure](https://github.com/Olical/conjure)** — connect to nREPL (and friends) from the editor.
- **Structural editing** — **[nvim-paredit](https://github.com/julienvincent/nvim-paredit)** and **[nvim-parinfer](https://github.com/gpanders/nvim-parinfer)**, coordinated via **[parpar.nvim](https://github.com/dundalek/parpar.nvim)** (the **par-par** plugin) so Paredit-style edits and Parinfer can coexist (from the AstroCommunity **Clojure** pack).
- **Tree-sitter (Treesitter)** — syntax highlighting and incremental language parsing ("faster plugin magic") for smarter editor features.
- **Clojure LSP** — diagnostics and language features via **`clojure-lsp`**, installed automatically through **[Mason](https://github.com/williamboman/mason.nvim)** (no manual binary hunting).
- **Lazy loading** — packages loaded on demand to keep startup and idle resource use low.
- **Git UIs** — **[LazyGit](https://github.com/jesseduffield/lazygit)** and **[Neogit](https://github.com/Neogit/neogit)** (Magit-like workflows, in the spirit of Emacs Magit or VS Code **Edamagit**).

## Release [2026-04-08](https://github.com/practicalli/nvim-astro/releases/tag/2026-04-08)

- **astronvim:** switch to the released **v6** line.
- **practicalli:** remove **neo-tree**; replace with **`snacks.picker.explorer`** (via [snacks.nvim](https://github.com/folke/snacks.nvim) on AstroNvim v6).
- **community:** add a **markdown** pack including the **marksman** tool (language server).
- **community:** add a **bash** pack for **zsh** and **bash** scripts.
- **readme:** update repo name and overview.

## Links

- [GitHub — practicalli/nvim-astro](https://github.com/practicalli/nvim-astro)  
- [Release 2026-04-08](https://github.com/practicalli/nvim-astro/releases/tag/2026-04-08)  
- [Practicalli](https://practical.li/) — learning resources and tooling around Clojure and editors.

## Credits

Configuration and release notes from **[Practicalli](https://github.com/practicalli)**; stack credits belong to **AstroNvim**, **Conjure**, **Mason**, **LazyGit**, **Neogit**, and respective plugin authors.
