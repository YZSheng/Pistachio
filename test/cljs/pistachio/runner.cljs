(ns pistachio.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [pistachio.core-test]
              [pistachio.subs-test]))

(doo-tests 'pistachio.core-test
           'pistachio.subs-test)
