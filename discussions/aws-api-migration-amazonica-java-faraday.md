# aws-api — migrations from **amazonica**, **AWS SDK for Java**, and **Faraday**

Teams still on older Clojure AWS stacks sometimes move to **[cognitect-labs/aws-api](https://github.com/cognitect-labs/aws-api)** — **`cognitect.aws.client.api`** with **`com.cognitect.aws/api`**, **`com.cognitect.aws/endpoints`**, and per-service artifacts (see **[docs](https://cognitect-labs.github.io/aws-api/)** and the repo **README** for current coordinates).

This note captures a short **Clojurians** thread: why people switch, and a naming reminder from the **Clojure** team.

---

## Why leave **amazonica**?

One maintainer described **migrating a large codebase** from **[amazonica](https://github.com/mcohen01/amazonica)** to **aws-api** because **amazonica** was **not keeping pace** with **AWS API deprecations** and **unsupported / legacy surfaces** — operational risk when AWS turns endpoints off or changes signing and models.

**aws-api** tracks **current service descriptors** and ships **data-driven** **`invoke`** over explicit ops—closer to how AWS documents APIs today than older generated-Java-style mirrors.

---

## Other paths onto **aws-api**

Another report: moving from the **AWS SDK for Java** plus **[`com.taoensso/faraday`](https://github.com/ptaoussanis/faraday)** (DynamoDB-oriented) to **aws-api** shrank an **uberjar by about 30%** (author-measured; your mileage depends on which services and transitives you pull).

That lines up with the general story: **fewer** fat Java client blobs when you take only the **Clojure + HTTP** path **aws-api** uses, instead of dragging broad **v1/v2** AWS Java clients for the same surface area.

---

## “Cognitect” on the tin — **Nubank** maintains it

**[Alex Miller](https://github.com/puredanger)** (**Clojure** / **Cognitect** alum) noted in-channel that **Cognitect as a company is gone**, but **the `com.cognitect.aws` coordinates and `cognitect.aws.*` namespaces stay** for **compatibility** — **maintenance is at [Nubank](https://nubank.com.br/)**. The **[aws-api README](https://github.com/cognitect-labs/aws-api)** already states it is developed internally at **Nubank**.

So thank-yous belong to **current maintainers** and **Nubank OSS**, even when the Maven coordinates still say **cognitect**.

---

## Thanks

Thanks to **Nubank** and **aws-api** contributors, thread voices (**salam**, **dharrigan**), and everyone publishing migration war stories—those concrete before/after signals help teams plan.
