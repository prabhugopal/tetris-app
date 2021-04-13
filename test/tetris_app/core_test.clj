(ns tetris-app.core-test
  (:require [clojure.test :refer :all]
            [tetris-app.game.engine :refer :all]
            [tetris-app.core :as core]))


;;test for checking the get-shape-orientation
(deftest test-get-shape-orientation
  (testing "get-shape-orientation.."
    (is
     (=
      (get-shape-orientation "Q")
      ['(1 0) '(1 1)
       '(0 0) '(0 1)]))))

;;test for checking the valid row for the given shape
(deftest test-get-valid-row
  (testing "get-valid-row.."
    (let [state [#{0 1 2 3}]
          shape "Q"
          col 0
          size 10]
      (is
       1
       (=
        (get-valid-row size shape col state))))))

;;test for checking the can-shape-fit? method.


(deftest test-can-shape-fit?
  (testing "can-shape-fit?.."
    (is
     false?
     (can-shape-fit? 10 "I" 8 0 [#{0 1 2 3}]))))

;;Testing the game engine with the provided example to check the correctness. 
(deftest test-example-1
  (testing "Example1 : I0,I4,Q8"
    (is 1 (= (-> (play-game "I0,I4,Q8" 10)
                 (core/count-rows<-game-sate))))))

(deftest test-example-2
  (testing "Example2 : T1,Z3,I4"
    (is 4 (= (-> (play-game "T1,Z3,I4" 10)
                 (core/count-rows<-game-sate))))))

(deftest test-example-3
  (testing "Example3 : Q0,I2,I6,I0,I6,I6,Q2,Q4"
    (is 3 (= (-> (play-game "Q0,I2,I6,I0,I6,I6,Q2,Q4" 10)
                 (core/count-rows<-game-sate))))))