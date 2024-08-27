(ns hello
  (:require [clojure.pprint :refer [pprint]]))

(println "Hello there!...")
(pprint {:a 10, :b true, :c [1]})

(comment

  (require '[emmy.env :as e])
  (e/->infix (e// (e/sin 'a) (e/cos 'a)))

  (require '[emmy.mafs :as mafs])
  (mafs/cartesian)
  (mafs/of-x {:x e/sin})

  (meta (mafs/of-x {:x e/sin}))
  ;; =>
  {:portal.viewer/reagent? true,
   :portal.viewer/default :emmy.portal/mafs,
   :nextjournal.clerk/viewer '#_object[emmy$mafs$default_viewer],
   :portal.viewer/mafs? true}

  (require '[emmy.viewer :as ev])
  (pprint (mafs/of-x {:x e/sin :color :blue}))
  (pprint (ev/expand (mafs/of-x {:x e/sin :color :blue})))
  (pprint (eval (ev/expand (mafs/of-x {:x e/sin :color :blue}))))

  (require '[reagent.core :as r])
  (r/with-let [a 10]
    [:div
     ["A" a]])

  )
