# Squint — `core` data-structure helpers as a ~1–3 kb ESM bundle for plain JS

This note summarizes a **Clojurians Slack** thread about **[Squint](https://github.com/squint-cljs/squint)**’s compiled **persistent-style** collection helpers: rough sizes (**~1 kb** for data-structure primitives alone, **~3 kb** with **`atom`** and related **`!`** functions), and whether a **prebuilt** **`squint-ds.min.js`** (npm / CDN) would be useful for **vanilla** / **van.js** front ends that want a **single mutable root** plus **Clojure-like** **`assoc-in`** / **`update-in`** on **native JS objects**.

Thread opener (unnamed in paste): motivation included **very small JS UIs** and dissatisfaction with **default LLM-shaped** client state patterns — preference for one **top-level** source of truth and **cljs-flavored** updates.

## **`chr15m` / Chris McCormick** — trivial wrapper + **esbuild**

Re-export selected symbols from **`squint`’s** emitted **`core`** (names as compiled to JS, e.g. **`contains_QMARK_`**, **`assoc_BANG_`**), then bundle:

```javascript
export {
  get,
  get_in,
  nth,
  peek,
  contains_QMARK_,
  assoc,
  assoc_BANG_,
  assoc_in,
  update,
  update_in,
  conj,
  conj_BANG_,
  merge,
  merge_with,
  into,
  dissoc,
  dissoc_BANG_,
  pop,
  select_keys,
  empty,
  atom,
  deref,
  reset_BANG_,
  swap_BANG_,
  add_watch,
  remove_watch
} from './src/squint/core.js';
```

```bash
npx esbuild squint-ds.js --bundle --minify --format=esm --outfile=squint-ds.min.js
```

They reported **personal need** in **plain JS** codebases and floated a **PR** to **`squint`** to ship such an artifact on **npm** / CDNs if there was interest.

## **Michiel Borkent (`borkdude`)** — “Why not use squint core directly?”

Short maintainer counterpoint: consumers might already get what they need by depending on **Squint `core`** / the normal compile path rather than a separate curated bundle — worth validating before adding release surface area.

*(No conclusion captured in-thread on npm artifact vs docs-only; check **[squint-cljs/squint](https://github.com/squint-cljs/squint)** issues/PRs.)*

## Links

- [Squint — squint-cljs/squint](https://github.com/squint-cljs/squint)  
- [van.js](https://vanjs.org/) — ultra-light reactive DOM helper cited in-thread  

## Credits

**Chris McCormick** ([@chr15m](https://github.com/chr15m)), **Michiel Borkent** ([@borkdude](https://github.com/borkdude)), thread opener, and participants.
