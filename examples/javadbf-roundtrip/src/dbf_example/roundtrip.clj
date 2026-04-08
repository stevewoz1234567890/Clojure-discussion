(ns dbf-example.roundtrip
  "Write a tiny DBF with DBFWriter, read it back with DBFReader.
  Java package is com.linuxense.javadbf (not the Maven group id)."
  (:import
   (com.linuxense.javadbf DBFDataType DBFField DBFReader DBFUtils DBFWriter)
   (java.io File FileInputStream))

(defn- char-field [^String name len]
  (doto (DBFField.)
    (.setName name)
    (.setType DBFDataType/CHARACTER)
    (.setLength len)))

(defn -main [& _]
  (let [f (doto (File/createTempFile "dbf-demo" ".dbf")
            (.deleteOnExit))]
    (let [w (DBFWriter. f)
          fields (into-array DBFField [(char-field "NAME" 20)])]
      (.setFields w fields)
      (.addRecord w (object-array ["Clojure"]))
      (.close w))
    (let [r (DBFReader. (FileInputStream. f))]
      (try
        (println "Read row:" (vec (.nextRecord r)))
        (finally
          (DBFUtils/close r))))))
