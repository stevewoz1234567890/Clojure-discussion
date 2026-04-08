# Eido — declarative, EDN-based images (and scenes)

**[Eido](https://github.com/leifericf/eido)** (“describe what you see”) is a **declarative, data-driven image language** for Clojure: you **describe the image as data**, not as a sequence of drawing instructions. The author frames it as a long-running idea, now prototyped and evolving in public.

## What it does

- **2D** — shapes, colors, gradients, transforms, typography compiled to paths.  
- **3D** — basic **3D scenes** (see the **[gallery](https://eido.leifericf.com/gallery/)**).  
- **Particles** — physics-style effects (emitters, forces, lifetimes) configured as data.  
- **Animations** — sequences of scene maps (e.g. 60 frames = 60 maps); no timeline DSL in the core story.  

**Rendering** is **CPU-based**, **100% Clojure** with **no external dependencies** (Clojure + standard library only). One main entry: **`eido.core/render`** takes a scene (or sequence of scenes) and produces output.

Design inspiration is cited from **[Replicant](https://github.com/cjohansen/replicant)** (Christian Johansen): a minimal, data-first approach to rendering, applied here to image generation.

## Quick start

Requires **Clojure 1.12+** and **Java 11+**. Add a **git dependency** (check the repo for the current tag — below is an example from upstream docs):

```clojure
io.github.leifericf/eido {:git/tag "v1.0.0-alpha5" :git/sha "03b38ab"}
```

```clojure
(require '[eido.core :as eido])

(eido/render
  {:image/size [400 400]
   :image/background [:color/rgb 245 243 238]
   :image/nodes
   [{:node/type :shape/circle
     :circle/center [200 200]
     :circle/radius 120
     :style/fill [:color/rgb 200 50 50]}]}
  {:output "circle.png"})
```

Full topics: **[Documentation](https://eido.leifericf.com/docs/)**, **[API reference](https://eido.leifericf.com/api/)**. The **[gallery](https://eido.leifericf.com/gallery/)** lists dozens of examples (2D, 3D, mixed, particles, typography, etc.) with source in `examples/gallery/`.

## Status

**v1.0.0-alpha5** (as of the README snapshot above): API **still evolving** and may change without notice.

## Credits

**[Leif Eric Fredheim](https://github.com/leifericf)** ([@leifericf](https://github.com/leifericf)).

## Runnable example in this repo

**[examples/eido-circle](../examples/eido-circle/)** — renders a circle PNG under `target/eido-circle.png`.

## Community note (Clojureverse Slack)

After the initial announcement, follow-up posts (3D, particles, etc.) drew a gentle reminder: **`#announcements`** is for **occasional or major** posts; **incremental updates** fit **`#releases`** better. The author acknowledged and moved follow-ups accordingly—useful context if you announce similar projects on that Slack.

## Links

- [GitHub — leifericf/eido](https://github.com/leifericf/eido)
- [Gallery](https://eido.leifericf.com/gallery/)
- [Docs](https://eido.leifericf.com/docs/)
- [API](https://eido.leifericf.com/api/)
