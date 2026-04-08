# Eido — circle PNG

Renders a circle with **[Eido](https://github.com/leifericf/eido)** ([discussion](../../discussions/eido-declarative-images.md)). Requires **Clojure 1.12+** and **Java 11+**.

## Run

```bash
cd examples/eido-circle
clojure -M -m eido-example.circle
```

Writes **`target/eido-circle.png`** next to the project.

## Dependency

Git coordinates (see [eido README](https://github.com/leifericf/eido)):

```clojure
io.github.leifericf/eido {:git/tag "v1.0.0-alpha5" :git/sha "03b38ab"}
```
