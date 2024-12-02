(ns day2.one
  (:require [clojure.string :as str]))

(comment
  "Please, place the `input` file in the same directory as this file."
  :rcf)

(defn read-input [file]
  (str/trim (slurp file)))

(defn to-lines
  "Turn a blob or block into a seq of lines"
  [input]
  (str/split-lines input))

(defn parse-line
  "Parse a line into a seq of strings"
  [line]
  (map #(Integer/parseInt %) (str/split line #"\s+")))

(def data (map parse-line (to-lines (read-input "src/day2/input"))))

(defn is-increasing [nums]
  (apply < nums))

(defn is-decreasing [nums]
  (apply > nums))

(defn make-pairs [nums]
  (partition 2 1 nums))

(defn get-pairs-change-abs [nums]
  (map (fn [[a b]] (abs (- b a))) (make-pairs nums)))

(defn all-numbers-in-safe-range? [nums]
  (every? #(and (<= % 3) (>= % 1)) nums))

(defn is-safe-diff [nums]
  (all-numbers-in-safe-range? (get-pairs-change-abs nums)))

(defn is-level-safe [nums]
  (and (or (is-increasing nums) (is-decreasing nums)) (is-safe-diff nums)))

(comment
  (is-level-safe [5 2 -1])
  :rcf)

(defn score-level [nums]
  (if (is-level-safe nums)
    1
    0))

(defn score-all-levels [data]
  (apply + (map score-level data)))

(println (score-all-levels data))
