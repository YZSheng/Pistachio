(ns pistachio.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [pistachio.core-test]))

(doo-tests 'pistachio.core-test)
