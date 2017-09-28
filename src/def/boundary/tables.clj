(ns def.boundary.tables
  (:require [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as hikari-cp]
            [duct.database.sql]))

(defprotocol Tables
  (get-tables [db username]))

(defprotocol Users
  (get-users [db username password]))

(defprotocol closeDb
  (close-db [db]))

(extend-protocol Tables
  duct.database.sql.Boundary
  (get-tables [{:keys [spec]} username]
              (jdbc/query spec [(str "SELECT * from user_master where username = '" username "';")])))

(extend-protocol Users
  duct.database.sql.Boundary
  (get-users [{:keys [spec]} username password]
             (jdbc/query spec [(str "select * from user_master where username = '" username "'and password ='" password "';")])))

(extend-protocol closeDb
  duct.database.sql.Boundary
  (close-db [{:keys [spec]}]
            (if-not (nil? (:datasource spec)) (hikari-cp/close-datasource (:datasource spec)) (println (:datasource spec)))))
