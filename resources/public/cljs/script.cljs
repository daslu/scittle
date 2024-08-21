(ns user
  (:require
   [reagent.core :as r]
   [reagent.dom :as rdom]))

(defn my-alert2 []
  (js/alert "My alert 2!"))

(defn just-saying []
  (js/console.log "Hi there, just saying we are connected now!"))

(comment
  (my-alert2)
  (just-saying)
  )

(def state (r/atom {:clicks 0}))
(defn my-component []
  [:div
   [:p "Hello dude " @state]
   [:button {:onClick #(swap! state update :clicks inc)} "Click Me"]])


(def loc (js/document.getElementById "APP2"))
(rdom/render [my-component] loc)
