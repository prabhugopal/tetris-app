(ns tetris-app.core
  (:require
   [tetris-app.game.engine :as tetris-engine]
   [clojure.tools.logging :as log])
  (:gen-class)
  (:import (java.io BufferedReader StringWriter)))

(declare
 get-input
 count-rows<-game-sate
 draw-state->terminal
 print-state->str
 get-printable-state)

(def grid-size
  10)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (log/info "****** Welcome to SiTE a Simplified Tetris Engine  ******")
  (doseq [input-shapes (get-input "./input.txt")]
    (let [rendering-fn (partial draw-state->terminal grid-size)]
      (println
       (-> (tetris-engine/play-game input-shapes grid-size rendering-fn)
           (count-rows<-game-sate))))))

;; reads the input sequence from the console/file
(defn get-input
  "reads the user input from console."
  ([]
   (line-seq (BufferedReader. *in*)))
  ([file]
   (with-open [rdr (clojure.java.io/reader file)]
     (into [] (line-seq rdr)))))

;; parses the game state to produce the resultant height
(defn count-rows<-game-sate
  "takes the game state as input, the game state should be represented in vector of hash-sets,
  each sets contains the occupied columns/cells of that partifular row.
  produces the number of rows or height of the resultant game state."
  [game-state]
  (count game-state))

(defn print-state->str
  "function to print the state"
  [grid-size state]
  (let [reversed-state (rseq state)
        fn-get-printable-state (partial get-printable-state grid-size)
        printable-state (doall (map fn-get-printable-state reversed-state))
        init-row-string ""
        newlined-state  (reduce #(str %1 (with-out-str (println %2))) init-row-string printable-state)]
    (str "\n" newlined-state)))

(defn get-printable-state
  ""
  [grid-size state-row]
  (let [init-row (into []  (take grid-size (repeat 0)))
        filled-row (reduce #(assoc %1 %2 1) init-row state-row)]
    (doall filled-row)))

(defn draw-state->terminal
  [grid-size state]
  (print "\033[2J")
  (print (print-state->str grid-size state))
  (flush))