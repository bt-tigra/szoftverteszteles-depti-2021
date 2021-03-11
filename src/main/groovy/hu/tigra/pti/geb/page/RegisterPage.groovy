package hu.tigra.pti.geb.page

import geb.Page

class RegisterPage extends Page {

    static url = 'index.php?controller=authentication&back=my-account#account-creation'

    static at = { pageTitle.displayed }

    static content = {
        pageTitle { $('h1', text: 'CREATE AN ACCOUNT') }
    }

}
