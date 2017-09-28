(ns def.handler.example-test
  (:require [clojure.test :refer :all]
            [kerodon.core :refer :all]
            [kerodon.test :refer :all]
            [integrant.core :as ig]
            [def.handler.example :as example]))

(def handler
  (ig/init-key :def.handler/example {}))

(deftest smoke-test
  (testing "example page exists"
    (-> (session handler)
        (visit "/example")
        (has (status? 200) "page exists"))))
