(ns pistachio.views
  (:require
    [re-frame.core :as re-frame]
    [pistachio.events :as events]
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

(defn nav-bar []
  (let [toggled (re-frame/subscribe [::subs/menu-burger-toggled])]
    [:nav.navbar {:role "navigation"}
     [:div.navbar-brand
      [:a.navbar-item {:href "#/"} "PistachioHR"]
      [:a.navbar-burger.burger
       {:class (str "navbar-burger burger" (when @toggled " is-active"))
        :role "button"
        :data-target "navbar-content"
        :on-click #(re-frame/dispatch [::events/toggle-menu-burger])}
       (->> (range 3)
            (map #(vector :span {:key %1})))]]
     [:div#navbar-content.navbar-menu
      [:div.navbar-start
       [:a.navbar-item {:href "#/"} "Home"]
       [:a.navbar-item {:href "#/about"} "About"]]]]))

;; users

(defn user-section []
  (let [users (re-frame/subscribe [::subs/users])]
    [:div.columns (map (fn [{name :name department :department avatar-url :avatar-url}]
                         (user-tile name department avatar-url))
                       @users)]))

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1.title
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
    [:div#main
     [nav-bar]
     [show-panel @active-panel]]))
