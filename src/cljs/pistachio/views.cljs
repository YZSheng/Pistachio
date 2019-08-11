(ns pistachio.views
  (:require
    [re-frame.core :as re-frame]
    [pistachio.subs :as subs]))

;; users

(defn user-section []
  (let [users (re-frame/subscribe [::subs/users])]
       [:div (map (fn [{name :name department :department}]
                    [:div {:key (str name department)} "Name: " name])
                  @users)]))

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name)]
     [user-section]
     [:div
      [:a {:href "#/about"}
       "go to About Page"]]]))

;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])

;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
