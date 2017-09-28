(ns def.handler.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [hikari-cp.core :refer :all]
            [clojure.java.jdbc :as jdbc]
            [integrant.core :as ig]))


(def datasource-options {:auto-commit        true
                         :read-only          false
                         :connection-timeout 30000
                         :validation-timeout 5000
                         :idle-timeout       600000
                         :max-lifetime       1800000
                         :minimum-idle       10
                         :maximum-pool-size  10
                         :pool-name          "db-pool"
                         :adapter            "postgresql"
                         :username           "chenyeyu"
                         :password           "A123"
                         :database-name      "test"
                         :server-name        "localhost"
                         :port-number        5432
                         :register-mbeans    false})

(def datasource
  (make-datasource datasource-options))


(defmethod ig/init-key :def.handler/example [_ options]
  (context "/" []
    (GET "/" []

      (jdbc/with-db-connection [conn {:datasource datasource}]
       (let [rows (jdbc/query conn "select * from user_master")]
         (println rows)) (close-datasource datasource))
      (io/resource "def/handler/example/example.html"))))
