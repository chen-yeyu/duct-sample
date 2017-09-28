(ns def.handler.mypage
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [def.boundary.tables :as tables]
            [integrant.core :as ig]
            [clojure.data.json :as json]))

(use 'selmer.parser)
(selmer.parser/cache-off!)

(defmethod ig/init-key :def.handler/mypage [_ {:keys [db]}]
  (context "/mypage" []
    (ANY "/page" [username password]
          (def user (first (tables/get-users db username password)))
          (tables/close-db db)
          (render-file "def/handler/example/page.html"
                       {:username (:username user) :password (:password user)
                        :first_name (:first_name user) :last_name (:last_name user)
                        :id (:id user)}))
    (ANY "/" [username password]
          (def user (first (tables/get-users db username password)))
          (tables/close-db db)
         (def request {
                       :status 200
                       :headers {
                                 "Access-Control-Allow-Headers" "Origin, X-Requested-With, Content-Type, Accept",
                                 "Content-Type" "application/json;charset=UTF-8",
                                 "Access-Control-Allow-Origin" "*"}
                       :body (render-file "def/handler/example/page.html"
                                          {:username (:username user) :password (:password user)
                                           :first_name (:first_name user) :last_name (:last_name user)
                                           :id (:id user)})}) request)
   (ANY "/react" [username password]
        (if (nil? username) (println "nil") (println username))
        (def user (first (tables/get-users db username password)))
        (println (json/write-str user))
        (tables/close-db db)
        (def request {
                      :status 200
                      :headers {
                                "Access-Control-Allow-Headers" "Origin, X-Requested-With, Content-Type, Accept",
                                "Content-Type" "application/json;charset=UTF-8",
                                "Access-Control-Allow-Origin" "*"}
                      :body (json/write-str user)}))))
