(ns k7-example.hello
  "Minimal enqueue → poll → ack using k7 (disk-backed queue)."
  (:require [s-exp.k7 :as k7])
  (:import (java.nio.file Files)))

(defn -main [& _]
  (let [dir (Files/createTempDirectory "k7-demo" (make-array java.nio.file.attribute.FileAttribute 0))]
    (with-open [q (k7/queue (str dir) {:fsync-strategy :flush})
                cg (k7/consumer-group q "demo")]
      (k7/enqueue! q (.getBytes "hello, k7"))
      (doseq [msg (k7/poll! cg {:max-batch 10 :timeout-ms 200})]
        (println (String. (k7/payload->bytes (k7/msg->payload msg))))
        (k7/ack! cg msg)))))
