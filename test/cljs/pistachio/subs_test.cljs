(ns pistachio.subs-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [pistachio.subs :as subs]))

(deftest menu-burger-toggle-test
  (testing "menu burger toggle on"
    (let [toggled (subs/menu-burger-toggled-sub {:menu-burger-toggled true})]
      (is (true? toggled))))
  (testing "menu burger toggle off"
    (let [toggled (subs/menu-burger-toggled-sub {:menu-burger-toggled false})]
      (is (false? toggled)))))