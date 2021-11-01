(ns hms.core-test
  (:require [clojure.test :refer :all]
            [hms.core :as core]))

(deftest days-test
  (testing "days"
    (is (= [0 0] (core/days 0)))
    (is (= [0 13] (core/days 13)))
    (is (= [1 5] (core/days (+ 24 5))))
    (is (= [2 0] (core/days (* 24 2))))))

(deftest hours-test
  (testing "hours"
    (is (= [0 0] (core/hours 0)))
    (is (= [0 42] (core/hours 42)))
    (is (= [1 42] (core/hours (+ 60 42))))
    (is (= [2 0] (core/hours (* 60 2))))))

(deftest hms-up-to-days-test
  (testing "hms"
    (is (= {:days 0
            :hours 0
            :minutes 0
            :seconds 42}
           (core/hms-up-to-days 0 0 42)))
    (is (= {:days 1
            :hours 0
            :minutes 42
            :seconds 0}
           (core/hms-up-to-days 24 42 0)))
    (is (= {:days 3
            :hours 7
            :minutes 46
            :seconds 12}
           (core/hms-up-to-days (+ (* 3 24) 7) 45 72)))))

(deftest str->hms-test
  (testing "parsing H:M:S string"
    (is (= [3 45 123] (core/str->hms "3:45:123")))))
