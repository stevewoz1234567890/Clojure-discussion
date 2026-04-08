# javadbf from Clojure — use `:import`, not `:require`

When you add **[javadbf](https://github.com/albfernandez/javadbf)** to **`deps.edn`**, the dependency resolves correctly, but **`require` will not work**: this artifact is a **plain Java library**. There is **no Clojure namespace** whose name matches the Maven coordinates.

## `deps.edn`

Example (version as needed):

```clojure
com.github.albfernandez/javadbf {:mvn/version "1.14.1"}
```

[Maven Repository entry](https://mvnrepository.com/artifact/com.github.albfernandez/javadbf).

## Why `require` fails

A line like this cannot work, because **`com.github.albfernandez.javadbf` is not a Clojure (or Java) package** used by the library:

```clojure
(ns invoice-parser.get-dbf-data
  (:require [com.github.albfernandez/javadbf :refer DBFReader :as jdbf]))  ;; wrong
```

**`require`** loads **namespaces** (Clojure or `gen-class` / AOT Java with a matching layout). **Java classes** are brought in with **`import`**.

Also, **Maven coordinates are not the same thing as the Java package name**. This project’s classes live under **`com.linuxense.javadbf`** (historical package from the original javadbf lineage), while the published artifact id is **`com.github.albfernandez/javadbf`**.

## Working namespace form

**Erik Colson** found the correct approach:

```clojure
(ns invoice-parser.get-dbf-data
  (:import (com.linuxense.javadbf DBFReader DBFUtils)))
```

From there you use **`DBFReader.`**, **`DBFUtils.`**, etc., like any other Java interop (see the [library README](https://github.com/albfernandez/javadbf) and Javadoc for constructors and methods).

## Runnable example in this repo

**[examples/javadbf-roundtrip](../examples/javadbf-roundtrip/)** — writes a one-row `.dbf` with `DBFWriter`, reads it with `DBFReader`.

## Links

- [GitHub — albfernandez/javadbf](https://github.com/albfernandez/javadbf)  
- [Maven — com.github.albfernandez:javadbf](https://mvnrepository.com/artifact/com.github.albfernandez/javadbf)  
