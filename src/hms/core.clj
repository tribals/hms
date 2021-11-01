(ns hms.core
  (:require (clojure [edn :as edn]
                     [string :as str]))
  (:gen-class))

(def full first)
(def part second)

(def quot&rem (juxt quot rem))

(defn days [n]
  (quot&rem n 24))

(defn sexagesimal [n]
  (quot&rem n 60))

(def hours sexagesimal)
(def minutes sexagesimal)

(defn hms-up-to-days [h m s]
  (let [d (days h)
        h (hours m)
        m (minutes s)]
    {:days (full d)
     :hours (+ (part d) (full h))
     :minutes (+ (part h) (full m))
     :seconds (part m)}))
  ; (let [d (quot h 24)
  ;       h (+ (rem h 24) (quot m))
  ;       m (+ (rem m) (quot s))
  ;       s (rem s)]
  ;   {:days d :hours h :minutes m :seconds s}))

(defn str->hms [s]
  (map edn/read-string (str/split s #":")))

(defn -main [& args]
  (let [hms (apply hms-up-to-days (str->hms (first args)))]
    (println (str (:days hms) " days, "
                  (:hours hms) ":" (:minutes hms) ":" (:seconds hms) " hms"))))
