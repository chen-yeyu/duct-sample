(defproject def "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [duct/server.http.jetty "0.1.5"]
                 [duct/core "0.5.1"]
                 [duct/module.logging "0.2.0"]
                 [hikari-cp "1.7.6"]
                 [org.postgresql/postgresql "42.1.1"]
                 [org.clojure/java.jdbc      "0.4.2"]
                 [duct/database.sql.hikaricp "0.1.2"]
                 [duct/module.sql "0.2.0"]
                 [duct/migrator.ragtime "0.1.2"]
                 [com.gearswithingears/shrubbery "0.4.1"]
                 [org.clojure/data.json "0.2.6"]
                 [duct/module.web "0.5.5"]]
  :plugins [[duct/lein-duct "0.9.2"]]
  :main ^:skip-aot def.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.2.0"]
                                   [eftest "0.3.1"]
                                   [selmer "0.7.2"]
                                   [kerodon "0.8.0"]]}})
