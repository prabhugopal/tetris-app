(ns tetris-app.game.engine
  (:require [tetris-app.game.models :as models]
            [clojure.tools.logging :as log])
  (:gen-class))

(declare
 get-input
 play-game
 count-rows<-game-sate
 get-valid-row
 can-shape-fit?
 pick-next-available-row
 draw-shape
 get-shape-orientation
 drop-rows-with-all-dirtycells
 new-state-with-dirty-cells
 group-cells->rows-cols)



;; main entry to the game engine and plays the game with input
(defn play-game
  "takes the  sequence of shapes and the grid-size as input. sequence of shapes should be comma separated string
  e.g \"Q0,I4,T3\" and the number for grid size. outputs the game state."
  ([input grid-size render-fn]
   (log/info
    "*** Initiating a Game: input[ " input " ], grid-size:" grid-size "\n")
   (if-let [shapes (clojure.string/split input #",")]
     (loop [[head & tail] shapes
            state []]
       (if (empty? head)
         (do (log/info "Final Game State:" grid-size state)
             state)
         (do
           (let [shape (str (first head))
                 col (Integer. (re-find #"\d+" head))]
             (log/info "Processing the shape:" shape " @ column:" col "\n")
             (let [new-state (->> (get-valid-row grid-size shape col state)
                                  (draw-shape shape col)
                                  (group-cells->rows-cols)
                                  (new-state-with-dirty-cells state))
                   _state (drop-rows-with-all-dirtycells  grid-size new-state)]
               (when (not (nil? render-fn))
                 (render-fn new-state))
               (Thread/sleep 1000)
               (recur tail _state))))))))
  ([input grid-size]
   (play-game input grid-size nil)))

;; gets a valid row,
;; find the available row for the shape and check if the shape fits using the grid size
;; if fits return the row, otherwise increment the row
(defn get-valid-row
  "takes the shape, column, game-sate and grid-size as inputs and produces the valid row (number) for
  the given shape to draw in the grid."
  [grid-size shape col state]
  (let [new-row (pick-next-available-row col state)]
    (log/debug "new-row " new-row ", state:" (count state))
    (if (> (count state) 0)
      (let [fit? (can-shape-fit? grid-size shape col new-row state)]
        (log/debug "fit? :: " fit?)
        (when (not fit?)
          (inc new-row))))
    new-row))

;; kind of dirty check for the grid-size.
;; checks the fitment of any shape in the provided row, col
(defn can-shape-fit?
  "takes the shape, column, row and size as the inoput. checks can the shape fit in the provided row
  and produces the boolean value (true/false) output."
  [grid-size shape col row state]
  (do
    (log/debug "state :: " state)
    (not-any?
     (fn [dirty-cells]
       (do
         (log/debug "dirty-cells :: " dirty-cells "state :: " state)
         (let [is-zero? (zero?
                         (compare
                          (vec (sort
                                (second dirty-cells)))
                          (vec (sort
                                (nth state (first dirty-cells) #{})))))
               is-exceed-size? (not-any?
                                #(>= % grid-size)
                                (second dirty-cells))
               res (and is-zero? is-exceed-size?)]
           (log/debug "is-zero? ::  " is-zero? " is-exceed-size? :: " is-exceed-size?)
           res)))
     (group-cells->rows-cols (draw-shape shape col row)))))


;; dirty pick of the available row without validating the size
(defn pick-next-available-row
  "takes the shape, column and game-state as the input. and produces the immediate available row
  to draw the shape in a dirty manner. output type - number"
  [col state]
  (log/debug "pick-available-row col:: " col " state:: " state)
  (loop [row (count state)
         [dirty-cells & remaining] (reverse state)]
    (if (or (some #(= col %) dirty-cells) (= 0 row))
      row
      (recur (dec row) remaining))))


;; draws the shape in the grid with the provided row and col
(defn draw-shape
  "takes the shape, column, and row as input and draws the shape in the grid. produces the points/cells of the shape.
  output type - vector of (x,y) cells/points"
  [shape col row]
  (map
   (fn [cells] (map + (list row col) cells))
   (get-shape-orientation shape)))

;;gets the given shape orientation
(defn get-shape-orientation
  "takes the shape as input and results the shape orientation "
  [shape]
  ((keyword shape) models/shapes))

;; drops the rows with no empty cells
(defn drop-rows-with-all-dirtycells
  "takes the grid-size and game-state as input and drops all the completely occupied rows or
  the rows which do not have any empty cells/columns."
  [grid-size state]
  (log/debug "Clearing Occupied Row From Game State:" state)
  (reduce
   (fn [new-state row]
     (log/debug "Checking for :" row grid-size (count row))
     (if (not= (count row) grid-size)
       (assoc new-state (count new-state) row)
       new-state))
   [] state))

;;returns the new state with dirty cells
(defn new-state-with-dirty-cells
  "takes the current-state and occupied/dirty cells as input. produces the new state with merging the dirty cells."
  [current-state rows-cols]
  (log/debug "current-state :: " current-state " rows-cols :: " rows-cols)
  (reduce
   (fn [state cells]
     (log/debug state "--" cells "--" (first cells))
     (assoc state (first cells)
            (set (concat (nth state (first cells) #{})
                         (last cells)))))
   current-state (sort rows-cols)))

;;groups the cells into rows and columns
(defn group-cells->rows-cols
  "this is a utility function which takes the grid-cells input and produces the grid-cells
  in vector rows-#{columns} format."
  [grid-cells]
  (log/debug "grid-cells: " grid-cells)
  (sort (map
         (fn [point]
           (do
             [(first point) (set (map last (last point)))]))
         (group-by first grid-cells))))




