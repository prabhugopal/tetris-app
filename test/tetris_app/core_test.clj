(ns tetris-app.core-test
  (:require [clojure.test :refer :all]
            [tetris-app.core :refer :all]))

(deftest test-get-shape-orientation
  (testing "get-shape-orientation.."
    (is 
     (= 
      (get-shape-orientation "Q") 
      ['(1 0) '(1 1)
       '(0 0) '(0 1)]))))

(deftest test-get-valid-row
  (testing "get-valid-row.."
    (let [state [#{0 1 2 3}]
          shape "Q"
          col 0
          size 10]
      (is 
       (= 
        (get-valid-row shape col state size) 
        1)))))


(deftest test-can-shape-fit?
  (testing "can-shape-fit?.."
    (is 
     (false? 
      (can-shape-fit? "I" 8 0 10)))))

(deftest test-example-1
  (testing "Example1 : I0,I4,Q8"
    (is (= (-> (play-game "I0,I4,Q8" 10)
               (parse-the-game-state-to-rows))
           1))))

(deftest test-example-2
  (testing "Example2 : T1,Z3,I4"
    (is (= (-> (play-game "T1,Z3,I4" 10)
               (parse-the-game-state-to-rows))
           4))))

(deftest test-example-3
  (testing "Example3 : Q0,I2,I6,I0,I6,I6,Q2,Q4"
    (is (= (-> (play-game "Q0,I2,I6,I0,I6,I6,Q2,Q4" 10)
               (parse-the-game-state-to-rows))
           3))))