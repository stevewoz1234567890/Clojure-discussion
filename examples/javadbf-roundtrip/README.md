# javadbf — write and read a `.dbf`

Uses **`:import`** for `com.linuxense.javadbf.*` (see [discussion](../../discussions/javadbf-clojure-import.md)).

## Run

```bash
cd examples/javadbf-roundtrip
clojure -M -m dbf-example.roundtrip
```

Expected:

```text
Read row: [Clojure]
```

## Coordinates

```clojure
com.github.albfernandez/javadbf {:mvn/version "1.14.1"}
```
