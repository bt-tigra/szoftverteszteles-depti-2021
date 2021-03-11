package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.LoginPage
import hu.tigra.pti.geb.page.MainPage
import hu.tigra.pti.geb.page.RegisterPage

class RegisterSpec extends BaseSpec {

    def 'A regisztrációs képernyőre navigálok'() {
        given: 'Megérkezünk a főoldalra.'
        def mainPage = waitFor { to MainPage }

        when: 'Rákattintok a Sign in gombra.'
        mainPage.loginButton.click()

        then: 'Megérkezek a bejelentkező felületre.'
        def loginPage = waitFor { at LoginPage }

        when: 'Kitöltöm a regisztrációs emailt és rákattintok a "Create an account" gombra'
        loginPage.registerEmail = 'teszt123@tigra.hu'
        loginPage.registerButton.click()

        then: 'Megjelenik a felhasználó adatai felület: "My account".'
        waitFor { at RegisterPage }
    }
}
