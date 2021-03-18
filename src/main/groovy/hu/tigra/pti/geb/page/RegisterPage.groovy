package hu.tigra.pti.geb.page

import geb.Page
import geb.module.RadioButtons

class RegisterPage extends Page {

    static url = 'index.php?controller=authentication&back=my-account#account-creation'

    static at = { pageTitle.displayed }

    static content = {
        pageTitle { $('h1', text: 'CREATE AN ACCOUNT') }
        gender { $('input[type="radio"][name="id_gender"]').module(RadioButtons) }
    }

}
