(ns tetris-app.game.models
  (:gen-class))

;;shapes and dimensions
(def shapes {:Q ['(1 0) '(1 1)
                 '(0 0) '(0 1)]

             :Z ['(1 0) '(1 1)
                        '(0 1) '(0 2)]

             :S [       '(1 1) '(1 2)
                 '(0 0) '(0 1)]

             :T ['(1 0) '(1 1) '(1 2)
                        '(0 1)       ]

             :I ['(0 0) '(0 1) '(0 2) '(0 3)]

             :L ['(2 0)
                 '(1 0)
                 '(0 0) '(0 1)]

             :J [       '(2 1)
                        '(1 1)
                 '(0 0) '(0 1)]
             })

;;not used


(def dirty-cells [])

;;not used
(def game-states {})