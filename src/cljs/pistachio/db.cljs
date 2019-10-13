(ns pistachio.db)

(def fake-avatar "https://i.pravatar.cc/300?u=")

(def john {:id "0" :name "John" :department "IT" :avatar-url (str fake-avatar "john")})
(def mary {:id "1" :name "Mary" :department "HR" :avatar-url (str fake-avatar "mary")})
(def adam {:id "2" :name "Adam" :department "Sales" :avatar-url (str fake-avatar "adam")})
(def jack {:id "3" :name "Jack" :department "Support" :avatar-url (str fake-avatar "jack")})

(def users [john mary adam jack])

(def default-db
  {:name "Pistachio HR System"
   :users users
   :menu-burger-toggled false
   :selected-user nil})
