(ns pistachio.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
    [secretary.core :as secretary]
    [goog.events :as gevents]
    [goog.history.EventType :as EventType]
    [re-frame.core :as re-frame]
    [pistachio.events :as events]))


(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
            (re-frame/dispatch [::events/set-active-panel :home-panel]))


  (defroute "/about" []
            (re-frame/dispatch [::events/set-active-panel :about-panel]))

  (defroute "/users/:id" [id]
            (re-frame/dispatch [::events/set-selected-user id])
            (re-frame/dispatch [::events/set-active-panel :user-details]))

  ;; --------------------
  (hook-browser-navigation!))
