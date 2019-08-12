(ns pistachio.views
  (:require
    [re-frame.core :as re-frame]
    [pistachio.subs :as subs]))

(defn user-tile
  [name department avatar-url]
  [:div {:class "user-tile column" :key name}
   [:div
    [:figure {:class "image is-128x128"}
     [:img {:src avatar-url}]]
    [:dl
     [:dt name]
     [:dd department]]]])

;; nav bars

(defn nav-bar
  "navigation bar"
  []
  [:nav {:class "navbar" :role "navigation"}
   [:div {:class "navbar-brand"}
    [:a {:class "navbar-item" :href "#/"} "PistachioHR"]
    [:a {:class "navbar-burger burger" :role "button" :data-target "navbar-content"}
     (->> (range 3)
          (map #(vector :span {:key %1})))]]
   [:div {:class "navbar-menu" :id "navbar-content"}
    [:div {:class "navbar-start"}
     [:a {:class "navbar-item" :href "#/"} "Home"]
     [:a {:class "navbar-item" :href "#/about"} "About"]]]])

;; users

(defn user-section []
  (let [users (re-frame/subscribe [::subs/users])]
    [:div {:class "columns"} (map (fn [{name :name department :department avatar-url :avatar-url}]
                                    (user-tile name department avatar-url))
                                  @users)]))

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 {:class "title"}
      (str "Hello from " @name)]
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
    [:div {:id "main"}
     [nav-bar]
     [show-panel @active-panel]]))
