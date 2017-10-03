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
         (let [[user db][(first (tables/get-users db username password)) db]] (tables/close-db db) (render-file "def/handler/example/page.html"
                                                                                                               {:username (:username user) :password (:password user)
                                                                                                                :first_name (:first_name user) :last_name (:last_name user)
                                                                                                                :id (:id user)}))

          )
    (ANY "/" [username password]
         (let [[user db][(first (tables/get-users db username password)) db]] (tables/close-db db) {
                                                                                                   :status 200
                                                                                                   :headers {
                                                                                                             "Access-Control-Allow-Headers" "Origin, X-Requested-With, Content-Type, Accept",
                                                                                                             "Content-Type" "application/json;charset=UTF-8",
                                                                                                             "Access-Control-Allow-Origin" "*"}
                                                                                                   :body (render-file "def/handler/example/page.html"
                                                                                                                      {:username (:username user) :password (:password user)
                                                                                                                       :first_name (:first_name user) :last_name (:last_name user)
                                                                                                                       :id (:id user)})})
         )

   (ANY "/react" [username password]
        (if (nil? username) (println "nil") (println username))
        (let [[user db] [(first (tables/get-users db username password)) db]] (tables/close-db db){
                                                                                                   :status 200
                                                                                                   :headers {
                                                                                                             "Access-Control-Allow-Headers" "Origin, X-Requested-With, Content-Type, Accept",
                                                                                                             "Content-Type" "application/json;charset=UTF-8",
                                                                                                             "Access-Control-Allow-Origin" "*"}
                                                                                                   :body (json/write-str user)})
        )))
