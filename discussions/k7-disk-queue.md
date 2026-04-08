# k7 — high-performance disk-backed queue (initial release)

**[k7](https://github.com/mpenet/k7)** ([`/kəˈsɛt/`](https://github.com/mpenet/k7), Clojars: [`com.s-exp/k7`](https://clojars.org/com.s-exp/k7)) is a **high-performance append-only queue** for Clojure, backed by **mmap’d segment files** on disk. The author positions it as work-in-progress: stable in practice but **no warranty on your data**—see the [upstream README](https://github.com/mpenet/k7/blob/main/README.md).

## Headline numbers

On **Apple M1**, **JVM 20**, using **`:flush`** fsync strategy (see below), the project reports roughly **~20M messages/s** for **`enqueue!`** and **~6.5M msg/s** for **poll + ack** (with details and tables in [bench-output.txt](https://github.com/mpenet/k7/blob/main/bench-output.txt)). Treat benchmarks as indicative; your hardware, payload size, and durability settings will differ.

## Design highlights

- **Append-only log** — preallocated mmap’d segment files.
- **Zero-copy reads** — payloads are read-only **`ByteBuffer`** slices into the mmap.
- **Single writer** — multiple **independent consumer groups**, each with **crash-safe cursors** in their own mmap’d files.
- **Ack / nack / seek** — **at-least-once** delivery, **redelivery on nack**, **arbitrary seek**; **adaptive batching** on **`poll!`**.
- **Crash recovery** — segments are scanned on open; the last valid **CRC32C**-verified frame is found automatically.
- **Fsync strategy** — configurable per queue (`:async`, `:flush`, `:sync`) and per consumer group (`:cursor-fsync-strategy`).
- **Low allocation** — **`enqueue!`** is zero-alloc; **`poll!`** allocates only the read-only **`ByteBuffer`** slice per message.

## Quick start

```clojure
(require '[s-exp.k7 :as k7])

(with-open [q  (k7/queue "/var/data/my-queue" {:fsync-strategy :flush})
            cg (k7/consumer-group q "workers")]
  (k7/enqueue! q (.getBytes "hello"))
  (let [batch (k7/poll! cg {:max-batch 10 :timeout-ms 50})]
    (doseq [msg batch]
      (k7/ack! cg msg))))
```

**Threading:** **`enqueue!`** is single-writer; **`poll!` / `ack!` / `nack!` / `seek!`** are single-thread per **`ConsumerGroup`**. Multiple groups on one queue are independent and can run concurrently. The README compares this model to **LMAX Disruptor** and **Chronicle Queue**.

Optional **`s-exp.k7.async`** integrates with **core.async** (`sink!`, `producer-chan`, `consumer-group-chan`).

## Credits

Library by **[Max Penet](https://github.com/mpenet)** ([@mpenet](https://github.com/mpenet)).

## Runnable example in this repo

**[examples/k7-hello](../examples/k7-hello/)** — enqueue one message, poll, ack, print (uses a temp directory for queue files).

## Links

- [GitHub — mpenet/k7](https://github.com/mpenet/k7)
- [Clojars — com.s-exp/k7](https://clojars.org/com.s-exp/k7)
- [bench-output.txt](https://github.com/mpenet/k7/blob/main/bench-output.txt)

## Community note

**Noah Bogart** on the initial announcement: “wow, this is sick.”
