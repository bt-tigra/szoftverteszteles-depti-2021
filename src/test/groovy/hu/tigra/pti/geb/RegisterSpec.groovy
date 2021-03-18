package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.LoginPage
import hu.tigra.pti.geb.page.MainPage
import hu.tigra.pti.geb.page.RegisterPage

class RegisterSpec extends BaseSpec {

    def 'Felhasználó regisztrálása'() {
        given: 'Megérkezünk a főoldalra.'
        def mainPage = waitFor { to MainPage }

        when: 'Rákattintok a Sign in gombra.'
        mainPage.loginButton.click()

        then: 'Megérkezek a bejelentkező felületre.'
        def loginPage = waitFor { at LoginPage }

        when: 'Kitöltöm a regisztrációs emailt és rákattintok a "Create an account" gombra'
        loginPage.registerEmail = "teszt${Util.getRandomNumber(100, 999)}@tigra.hu"
        loginPage.registerButton.click()

        then: 'Megjelenik a regisztrációs képernyő "CREATE AN ACCOUNT" címmel'
        def registerPage = waitFor { at RegisterPage }

        when: 'Kitöltöm a "Your personal information" blokkban az összes mezőt és a "Register" gombra kattintok.'
        registerPage.gender.checked = 'Mr.'

        then: '5 hibaüzenet jelenik meg'

        /*when: 'Kitöltöm a jelszót újra és a "Your address" blokkban a mezőket, majd a "Register" gombra kattintok.'

        then: 'Megjelenik a felhasználó adatai felület: "My account".'*/
    }
}
