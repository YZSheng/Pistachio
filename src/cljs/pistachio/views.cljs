(ns pistachio.views
  (:require
    [re-frame.core :as re-frame]
    [pistachio.events :as events]
    [pistachio.subs :as subs]
    [secretary.core :as secretary]))

(defn user-tile
  [id name department avatar-url]
  [:div {:class "user-tile column" :key name}
   [:div
    [:a {:href (str "#/users/" id)}
     [:figure {:class "image is-128x128"}
      [:img {:src avatar-url}]]
     [:dl
      [:dt name]
      [:dd department]]]]])


;; nav bars

(defn nav-bar []
  (let [toggled (re-frame/subscribe [::subs/menu-burger-toggled])]
    [:nav.navbar {:role "navigation"}
     [:div.navbar-brand
      [:a.navbar-item {:href "#/"} "PistachioHR"]
      [:a.navbar-burger.burger
       {:class       (str "navbar-burger burger" (when @toggled " is-active"))
        :role        "button"
        :data-target "navbar-content"
        :on-click    #(re-frame/dispatch [::events/toggle-menu-burger])}
       (->> (range 3)
            (map #(vector :span {:key %1})))]]
     [:div#navbar-content {:class (str "navbar-menu" (when @toggled " is-active"))}
      [:div.navbar-start
       [:a.navbar-item {:href "#/"} "Home"]
       [:a.navbar-item {:href "#/about"} "About"]]]]))

;; users

(defn user-section []
  (let [users (re-frame/subscribe [::subs/users])]
    [:section.section
     [:div.columns (map (fn [{:keys [id name department avatar-url]}]
                          (user-tile id name department avatar-url))
                        @users)]]))

(defn user-details []
  (let [user (re-frame/subscribe [::subs/selected-user])]
    [:section.section
     [:div.container
      [:h1 (str "User details for " (:name @user))]
      [:div.card
       [:div.card-content
        [:div.media
         [:div.media-left
          [:figure.image.is-128x128
           [:img {:src (:avatar-url @user)}]]]
         [:div.media-content
          [:p.title.is-4 (str "Department " (:department @user))]]]]]]]))





(defn welcome-message [name]
  [:section.hero.is-medium.is-primary.is-bold
   [:div.hero-body
    [:div.container
     [:h1.title "Hello there!"]
     [:h2.subtitle (str "Welcome to " name)]]]])

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [welcome-message @name]
     [user-section]]))

;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]])

;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :user-details [user-details]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div#main
     [nav-bar]
     [show-panel @active-panel]]))
