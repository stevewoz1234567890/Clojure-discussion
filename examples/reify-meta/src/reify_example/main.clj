(ns reify-example.main
  "Demonstrates Clojure 1.12.5-alpha1 (CLJ-2945): reify no longer carries reader :line/:column metadata."
(defn -main [& _]
  (println "meta (reify Serializable) =>"
           (meta (reify java.io.Serializable)))
  (println "meta ^{:a 1} (reify Serializable) =>"
           (meta ^{:a 1} (reify java.io.Serializable))))
