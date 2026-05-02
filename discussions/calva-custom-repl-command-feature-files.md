# Calva — **Custom REPL** commands from **`.feature`** (Gherkin) files

This note summarizes a **Clojurians Slack** (**`#calva`**) thread about porting a **Cursive** testing workflow to **[Calva](https://github.com/BetterThanTomorrow/calva)** for **VS Code** users: drive **Cucumber-style** **`.feature`** files and evaluate a **snippet** that passes **file path** and **line number** into a project **REPL** helper.

## Goal (Cursive-shaped)

In **Cursive**, something equivalent to:

```clojure
(require '[blahblah.repl :as repl])
(repl/run-scenario-test! "~file-path" ~line-number)
```

bound to the **current** feature file and **cursor line**.

## Problem

**Calva** **`runCustomREPLCommand`** behaves when the editor thinks the buffer is **Clojure**, but **does not** run the same way when the active document is a **`.feature`** file — the extension effectively **refuses to treat** that context as valid for **Custom REPL** execution (while normal **`.clj` / `.cljc` / …** buffers work).

## Workaround — force Clojure mode + explicit **`user`** ns

**Adrian Smith** suggested a **`keybindings.json`** entry that:

- Calls **`calva.runCustomREPLCommand`** with a fixed **`ns`** (**`user`**) so evaluation does not depend on Calva inferring a namespace from the buffer.
- Uses **`$file`** and **`$line`** placeholders inside the **snippet**.

Example:

```json
{
  "key": "shift+alt+s",
  "command": "calva.runCustomREPLCommand",
  "args": {
    "ns": "user",
    "snippet": "(do (require '[blahblah.repl :as repl]) (repl/run-scenario-test! \"$file\" $line))"
  }
}
```

Adjust **`blahblah.repl`** / **`run-scenario-test!`** to match your project.

## Maintainer direction

**Peter Strömberg** ([@PEZ](https://github.com/PEZ)): Calva **should** be fixed so **Custom REPL** works without hacks for non-Clojure extensions. Until then, try **`files.associations`** so VS Code (and language features that key off it) **treat `*.feature` as Clojure**:

```json
"files.associations": {
  "*.feature": "clojure"
}
```

*(Trade-off: Gherkin highlighting/editing may degrade if the buffer is forced to **Clojure** language ID.)*

## Links

- [Calva — GitHub](https://github.com/BetterThanTomorrow/calva)  
- VS Code — [Language identifiers](https://code.visualstudio.com/docs/languages/overview) · **`files.associations`** in [User/workspace settings](https://code.visualstudio.com/docs/getstarted/settings)  

Thanks to thread opener, **Adrian Smith**, and **[@PEZ](https://github.com/PEZ)**.

*Search Calva issues for “custom repl” + “feature” if you want to track or contribute an upstream fix.*
