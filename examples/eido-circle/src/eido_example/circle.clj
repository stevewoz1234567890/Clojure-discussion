(ns eido-example.circle
  "Render a simple circle PNG with Eido (declarative image data)."
  (:require [eido.core :as eido]))

(defn -main [& _]
  (let [out (java.io.File. "target/eido-circle.png")]
    (.mkdirs (.getParentFile out))
    (eido/render
      {:image/size [200 200]
       :image/background [:color/rgb 245 243 238]
       :image/nodes
       [{:node/type :shape/circle
         :circle/center [100 100]
         :circle/radius 70
         :style/fill [:color/rgb 200 50 50]}]}
      {:output (.getPath out)})
    (println "Wrote" (.getAbsolutePath out))))
