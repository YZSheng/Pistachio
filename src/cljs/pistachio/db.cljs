(ns pistachio.db)

(def john {:name "John" :department "IT"})
(def mary {:name "Mary" :department "HR"})

(def users [john mary])

(def default-db
  {:name "Pistachio HR System"
   :users users})
