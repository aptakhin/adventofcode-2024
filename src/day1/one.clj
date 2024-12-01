(ns day1.one)

(comment
  "Please, place the `input` file in the same directory as this file."
  :rcf)

(comment "Copilot magic. Difficult readable :(" :rcf)
(defn parse-file [file-path]
      (with-open [rdr (clojure.java.io/reader file-path)]
            (doall
                  (map #(mapv read-string (clojure.string/split % #"\s+"))
                               (line-seq rdr)))))

(def data (parse-file "src/1/input"))
(comment
  (second (first data))
  :rcf)

(def first-list (map first data))
(def second-list (map second data))

(def sorted-first-list (sort first-list))
(def sorted-second-list (sort second-list))

(def differences (map abs(map - sorted-first-list sorted-second-list)))
(apply + differences)
(def sum (apply + differences))
(println sum)
