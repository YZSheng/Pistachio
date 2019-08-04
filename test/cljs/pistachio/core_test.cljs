(ns pistachio.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [pistachio.core :as core]))

(deftest fake-test
  (testing "sanity"
    (is (= 1 1))))
