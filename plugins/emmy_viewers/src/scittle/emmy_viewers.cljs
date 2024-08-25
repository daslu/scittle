(ns scittle.emmy-viewers
  {:nod-doc true}
  (:require
   [emmy.viewer.sci]
   [scittle.core :as scittle]))

(defn init []
  (emmy.viewer.sci/install!)
  (scittle/register-plugin! ::emmy-viewers {}))
