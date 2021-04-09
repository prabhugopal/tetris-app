(ns tetris-app.game.reducers
  (:gen-class))

;; plan was to organize all the reducers in one place.

;;(def cell-reducers {:D (fn [col dirty-cells] 
;;                         (let [])
;;                         (when (some dirty-cells col)
;;                           ))
;;                    })

;; dirty pick of the available row without validating the size
(defn pick-available-row
  "takes the shape, column and game-state as the input. and produces the immediate available row to draw the shape in a dirty manner. output type - number"
  [shape col state]
  (if (some #(shape) ["Q" "S" "I" "L" "J"])
    (loop [row (count state)
           [dirty-cells & remaining] (reverse state)]
      (if (or (some #(= col %) dirty-cells) (= 0 row))
        row
        (recur (dec row) remaining)))
    ()))
