(defproject tetris-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [org.clojure/tools.logging "0.4.1"]]
  :plugins [[lein-codox "0.10.7"]
            [lein-cljfmt "0.7.0"]]
  :main ^:skip-aot tetris-app.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :codox {:source-paths ["./src/tetris_app"]}
  )
