(ns day3.two
  (:require [clojure.string :as str]))

(comment
  "Please, place the `input` file in the same directory as this file."
  :rcf)

(defn read-input [file]
  (str/trim (slurp file)))

(def input (read-input "src/day3/input"))

(defn find-matches [pattern input]
  (re-seq pattern input))

(def matches (find-matches #"((mul)\((\d+),(\d+)\))|(do)\(\)|(don\'t)\(\)" input))

(comment
  matches
  :rcf)

(defn parse-mul-arguments [matches]
  (map (fn [[_ _ mul a b do dont]]
         [(or mul do dont) (and mul (Integer/parseInt a)) (and mul (Integer/parseInt b))])
         matches))

(comment
  (parse-mul-arguments matches)
  :rcf)

;; (apply + (map (fn [[a b]] (* a b)) (parse-mul-arguments matches)))

(defn remove-between-dont-and-do [parsed-list]
  (let [state (atom {:in-range false :result []})]
    (doseq [item parsed-list]
      (let [[type _ _] item]
        (cond
          (= type "don't") (swap! state assoc :in-range true)
          (= type "do") (swap! state assoc :in-range false)
          (not (:in-range @state)) (swap! state update :result conj item))))
    (:result @state)))

;; Example usage
(def parsed-list [["mul" 2 4] ["don't" nil nil] ["mul" 5 5] ["mul" 11 8] ["do" nil nil] ["mul" 8 5]])
(def result (remove-between-dont-and-do parsed-list))

(println result) ;; => [["mul" 2 4] ["mul" 8 5]]

(comment
  matches
  :rcf)

(defn parse-mul-arguments [matches]
  (map (fn [[_ _ mul a b do dont]]
         [(or mul do dont) (and mul (Integer/parseInt a)) (and mul (Integer/parseInt b))])
         matches))

(comment
  (parse-mul-arguments matches)
  :rcf)


(comment
  crying here :( without for cycle and state machine)
  :rcf)
(defn remove-between-dont-and-do [parsed-list]
  (let [state (atom {:in-range false :result []})]
    (doseq [item parsed-list]
      (let [[type _ _] item]
        (cond
          (= type "don't") (swap! state assoc :in-range true)
          (= type "do") (swap! state assoc :in-range false)
          (not (:in-range @state)) (swap! state update :result conj item))))
    (:result @state)))

(def result (remove-between-dont-and-do (parse-mul-arguments matches)))

(println result) ;; => [["mul" 2 4] ["mul" 8 5]]§

(apply + (map (fn [[_ a b]] (* a b)) result))