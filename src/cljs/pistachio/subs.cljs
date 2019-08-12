(ns pistachio.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
  ::users
  (fn [db]
    (:users db)))

(defn menu-burger-toggled-sub
  [db]
  (:menu-burger-toggled db))


(re-frame/reg-sub
  ::menu-burger-toggled
  menu-burger-toggled-sub)
