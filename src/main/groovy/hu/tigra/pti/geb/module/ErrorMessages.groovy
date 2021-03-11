package hu.tigra.pti.geb.module

import geb.Module

class ErrorMessages extends Module {

    static content = {
        values { navigator.find('li').collect{ li -> li.text() } }
    }
}
