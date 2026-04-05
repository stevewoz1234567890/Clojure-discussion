(ns clojisr-example.docstrings-demo
  "Minimal demo of clojisr 1.1.0: R function docstrings on required vars.
  Requires R with ggplot2 installed and a working clojisr session (see example README)."
  (:require [clojisr.v1.r :refer [require-r]]
            [clojure.string :as str]))

(defn ggplot-first-doc-line
  "Returns the first line of R help text for ggplot2::ggplot, via var metadata."
  []
  (require-r '[ggplot2 :as gg :docstrings? true])
  (-> #'gg/ggplot meta :doc str/split-lines first))

(defn -main [& _]
  (println (ggplot-first-doc-line)))
