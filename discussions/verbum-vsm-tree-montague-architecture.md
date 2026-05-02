# Verbum — VSM tree architecture, φ‑like compressor, Montague-shaped trio (community note)

Informal recap of a thread about **[Verbum](https://github.com/michaelwhitford/verbum)** ([@michaelwhitford](https://github.com/michaelwhitford)): a **small Python** research codebase (“explore **LLM attention** and **model architecture**”) where **scripts are public** for reproducibility; **checkpoints** and **large artifacts** are omitted upstream.

## Author update (paraphrase)

- **Compressor** behavior was located and showed **self-similarity** very close to **φ** (golden ratio).
- **Strided attention** is working and can run in a **VSM-shaped** tensor layout.
- Active work: a **Montague-flavored** **type → parse → apply** trio; overall design shifted to a **tree of VSMs** as the **LLM** backbone.
- A **~23 M** parameter experiment reached **~88 %** on the author’s metric; scaling to **~80 M** targets the **~12 %** of tokens failing from **missing context**, after an earlier **too-narrow chokepoint** limited learning of **complex sentence structure**.
- Repo holds training/setup **scripts** (minus weights/large blobs) with the hope others can **reproduce** runs.

## Follow-ups from the same voice

- **Ternary weights** used broadly where possible — if the approach pans out, inference could achieve strong **CPU throughput**.
- **Sample ~80 M config** (as reported): **`d_model=512`**, **`d_basin=512`**, **`n_heads=16`**, **`n_levels=8`** — **~80.8 M** logical parameters (**~80.67 M** ternary, **~165 k** continuous), **~20.8 MB** packed size.

## Wu David — related reading

Link dropped in-thread to **[The Platonic Representation Hypothesis](https://arxiv.org/abs/2405.07987)** (arXiv:2405.07987): convergence of **representations** across **modalities** and scale toward a shared statistical model of reality — offered as **“for ALL AI”** flavor context, not as a formal claim about Verbum.

## Links

- [michaelwhitford/verbum](https://github.com/michaelwhitford/verbum)  
- [arXiv:2405.07987 — Platonic Representation Hypothesis](https://arxiv.org/abs/2405.07987)  

Thanks to **Michael Whitford**, **Wu David**, and thread readers.

*Benchmarks and percentages are author-reported in chat — confirm against the repo and your own runs.*
