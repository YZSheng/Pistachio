(ns pistachio.db)

(def fake-avatar "https://i.pravatar.cc/300?u=")

(def john {:name "John" :department "IT" :avatar-url (str fake-avatar "john")})
(def mary {:name "Mary" :department "HR" :avatar-url (str fake-avatar "mary")})
(def adam {:name "Adam" :department "Sales" :avatar-url (str fake-avatar "adam")})
(def jack {:name "Jack" :department "Support" :avatar-url (str fake-avatar "jack")})

(def users [john mary adam jack])

(def default-db
  {:name "Pistachio HR System"
   :users users})
