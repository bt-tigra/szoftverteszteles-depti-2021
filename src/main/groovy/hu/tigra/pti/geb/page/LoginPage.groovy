package hu.tigra.pti.geb.page

import geb.Page
import hu.tigra.pti.geb.module.ErrorMessages

class LoginPage extends Page {

    static url = 'index.php?controller=authentication'

    static at = { loginForm.displayed }

    static content = {
        loginForm { $('form[id="login_form"]') }
        emailAddress { $('form[id="login_form"]').$('input[id="email"]') }
        password { $('form[id="login_form"]').$('input[id="passwd"]') }
        loginButton { $('form[id="login_form"]').$('button[id="SubmitLogin"]') }
        errorMessages(required: false) { $('div[class="alert alert-danger"]:first-of-type').module ErrorMessages }
    }

    def login(emailAddress, password) {
        this.emailAddress = emailAddress
        this.password = password
        loginButton.click()
    }
}
