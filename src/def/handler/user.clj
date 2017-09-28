(ns user)
(use 'selmer.parser)

(selmer.parser/cache-off!)

(selmer.parser/set-resource-path! (clojure.java.io/resource "def/handler/example"))


(render-file "userInfo.html" {:title "Selmer Template",}
                          :name "Michael",
                          :password "123")
