(ns def.main
  (:gen-class)
  (:require [clojure.java.io :as io]
            [duct.core :as duct]))
;hel
(defn -main [& args]
  (duct/exec (duct/read-config (io/resource "def/config.edn"))
             (duct/parse-keys args)))
