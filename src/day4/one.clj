(ns day4.one
  (:require [clojure.string :as str]))

(comment
  "Please, place the `input` file in the same directory as this file."
  :rcf)

(defn read-input [file]
    (str/trim (slurp file)))

(def input (read-input "src/day4/input"))

(defn split-by-lines [s]
  (str/split-lines s))

(def size (count (split-by-lines input)))

(def mp (split-by-lines input))

(nth (nth mp 0) 3)

(def direction [0 1])

(defn get-char [x y]
  (if (and (<= 0 x) (< x size) (< y size) (<= 0 y)) (nth (nth mp y) x) nil))


(defn safe-compare [x y char]
  (= char (get-char x y)))

(defn search-for-xmas-in-direction [x y xdir ydir]
  (and (safe-compare x y \X) (safe-compare (+ x xdir) (+ y ydir) \M) (safe-compare (+ x xdir xdir) (+ y ydir ydir) \A) (safe-compare (+ x xdir xdir xdir) (+ y ydir ydir ydir) \S)))



(
;;  (assert (= (get-char 0 3) \S) "Expected character at (0, 3) to be 'S'")
 (get-char 6 0)
 (safe-compare 0 -1 nil)
 (safe-compare -1 0 nil)
 (safe-compare 0 0 \M)
 (safe-compare 5 0 \X)
 (safe-compare 6 0 \M)
;;  (assert (= (search-for-xmas-in-direction 5 0 1 0) true) "hello")
)

(defn search-for-xmas-in [x y]
  (list
   (search-for-xmas-in-direction x y 1 0)
   (search-for-xmas-in-direction x y -1 0)
   (search-for-xmas-in-direction x y 0 1)
   (search-for-xmas-in-direction x y 0 -1)
   (search-for-xmas-in-direction x y 1 1)
   (search-for-xmas-in-direction x y -1 -1)
   (search-for-xmas-in-direction x y 1 -1)
   (search-for-xmas-in-direction x y -1 1)
   ))

(defn count-search-for-xmas-in [x y]
  (count (filter true? (search-for-xmas-in x y))))


(count-search-for-xmas-in 5 0)

(defn iterate-map []
  (reduce + (for [x (range size)
                  y (range size)]
              (count-search-for-xmas-in x y))))

  (iterate-map)

;;  (defn compare-xmas [start direction]
;;    (str/split-lines s)))
