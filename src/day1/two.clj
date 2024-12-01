(ns day1.two)

(comment
  "Please, place the `input` file in the same directory as this file."
  :rcf)

(comment "Copilot magic. Difficult readable :(" :rcf)
(defn parse-file [file-path]
      (with-open [rdr (clojure.java.io/reader file-path)]
            (doall
                  (map #(mapv read-string (clojure.string/split % #"\s+"))
                               (line-seq rdr)))))

(def data (parse-file "src/day1/input"))

(def first-list (map first data))
(def second-list (map second data))

(def counts (frequencies second-list))

(defn check-value [value counts]
  (get counts value 0))

(def frequencies-entries (map #(check-value % counts) first-list))
(def frequencies-entries-sum (apply + (map * first-list frequencies-entries)))

(println frequencies-entries-sum)