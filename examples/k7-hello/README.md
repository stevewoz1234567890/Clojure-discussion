# k7 — one enqueue / poll / ack

Uses [`com.s-exp/k7`](https://clojars.org/com.s-exp/k7) ([discussion](../../discussions/k7-disk-queue.md)). Creates a temporary directory for the queue files.

## Run

```bash
cd examples/k7-hello
clojure -M -m k7-example.hello
```

Expected:

```text
hello, k7
```
