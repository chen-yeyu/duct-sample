(ns def.handler.login
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(use 'selmer.parser)
(selmer.parser/cache-off!)


(defmethod ig/init-key :def.handler/login [_ options]
  ;
  ; (context "/login" []
  ;          (GET "/" [] (io/resource "def/handler/example/login.html"))
  ;          (POST "/post-login" [username password]
  ;                (str "user: " username " password: " password))
  ;          (GET "/selmer-test1" []
  ;               (render "Hello {{name}}!" {:name "test-selmer1"}))
  ;          (GET "/selmer-test2" []
  ;               (render-file "def/handler/example/userInfo.html"
  ;                            {:title "test-selmer2" :name "CYY"})))

  (context "/account" []
           (GET "/" [] (io/resource "def/handler/example/account.html"))
           (POST "/post-login-selmer" [username password]
                 (render-file "def/handler/example/myinfo.html"
                              {:username username, :password password}))))
