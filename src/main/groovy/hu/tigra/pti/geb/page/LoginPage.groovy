package hu.tigra.pti.geb.page

import geb.Page
import hu.tigra.pti.geb.domain.User
import hu.tigra.pti.geb.module.ErrorMessages

class LoginPage extends Page {

    static url = 'index.php?controller=authentication'

    static at = { loginForm.displayed }

    static content = {
        loginForm { $('form[id="login_form"]') }
        emailAddress { loginForm.$('input[id="email"]') }
        password { loginForm.$('input[id="passwd"]') }
        loginButton { loginForm.$('button[id="SubmitLogin"]') }
        errorMessages(required: false) { $('div[class="alert alert-danger"]:first-of-type').module ErrorMessages }
        registerEmail { $('input[id="email_create"]') }
        registerButton { $('button[id="SubmitCreate"]') }
    }

    def login(String emailAddress, String password) {
        this.emailAddress = emailAddress
        this.password = password
        loginButton.click()
    }

    def login(User user) {
        emailAddress = user.emailAddress
        password = user.password
        loginButton.click()
    }
}
