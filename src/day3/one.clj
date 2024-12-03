(ns day3.one
  (:require [clojure.string :as str]))

(comment
  "Please, place the `input` file in the same directory as this file."
  :rcf)

(defn read-input [file]
  (str/trim (slurp file)))

(def input (read-input "src/day3/input"))

(defn find-matches [pattern input]
  (re-seq pattern input))

(def matches (find-matches #"mul\((\d+),(\d+)\)" input))

(defn parse-mul-arguments [matches]
  (map (fn [[_ a b]]
         [(Integer/parseInt a) (Integer/parseInt b)])
       matches))

(apply + (map (fn [[a b]] (* a b)) (parse-mul-arguments matches)))
