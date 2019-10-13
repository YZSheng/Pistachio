(ns pistachio.events
  (:require
   [re-frame.core :as re-frame]
   [pistachio.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 ::set-selected-user
 (fn-traced [db [_ id]]
   (assoc db :selected-user (some #(if (= id (:id %)) %) (:users db)))))

(re-frame/reg-event-db
  ::toggle-menu-burger
  (fn-traced [db _]
             (update db :menu-burger-toggled not)))
