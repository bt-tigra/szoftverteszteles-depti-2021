package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.LoginPage
import hu.tigra.pti.geb.page.MainPage
import hu.tigra.pti.geb.page.MyAccountPage

class LoginSpec extends BaseSpec {

    def 'Helyes bejelentkezés'() {
        given: 'Megérkezünk a főoldalra.'
        def mainPage = waitFor { to MainPage }

        when: 'Rákattintok a Sign in gombra.'
        mainPage.loginButton.click()

        then: 'Megérkezek a bejelentkező felületre.'
        def loginPage = waitFor { at LoginPage }
        loginPage.loginForm.displayed

        when: 'Kitöltöm a mezőket helyes adatokkal és rányomok a belépés gombra.'
        loginPage.emailAddress = 'gebpti2021@tigra.hu'
        loginPage.password = 'teszt123'
        loginPage.loginButton.click()

        then: 'Megjelenik a felhasználó adatai felület: "My account".'
        def myAccountPage = waitFor { at MyAccountPage }
        myAccountPage.header.text() == "MY ACCOUNT"
    }

    def 'Helyes bejelentkezés 2'() {
        given: 'Megérkezünk a főoldalra.'
        waitFor { to MainPage }

        when: 'Bejelentkezek egy helyes felhasználóval'
        login(Constant.USERS.HELYES_FELHASZNALO)

        then: 'Megjelenik a felhasználó adatai felület: "My account".'
        def myAccountPage = waitFor { at MyAccountPage }
        myAccountPage.header.text() == "MY ACCOUNT"
    }

    def 'Helytelen bejelentkezés'() {
        given: 'Megérkezünk a főoldalra.'
        MainPage mainPage = waitFor { to MainPage }

        when: 'Rákattintok a Sign in gombra.'
        mainPage.loginButton.click()

        then: 'Megérkezek a bejelentkező felületre.'
        def loginPage = waitFor { at LoginPage }
        loginPage.loginForm.displayed

        when: 'Kitöltöm a mezőket helytelen adatokkal és rányomok a belépés gombra.'
        loginPage.emailAddress = 'gebpti2021@tigra.hu'
        loginPage.password = 'rosszjelszo'
        loginPage.loginButton.click()

        then: 'Megjelenik a hibaüzenet: "Authentication failed".'
        loginPage.errorMessages.values.any{errorMessage -> errorMessage == "Authentication failed."}
    }

    def 'Helytelen bejelentkezés 2'() {
        given: 'Megérkezünk a főoldalra.'
        waitFor { to MainPage }

        when: 'Bejelentkezek egy helyes felhasználóval'
        def loginPage = login(Constant.USERS.HELYTELEN_FELHASZNALO)

        then: 'Megjelenik a hibaüzenet: "Authentication failed".'
        loginPage.errorMessages.values.any{errorMessage -> errorMessage == "Authentication failed."}
    }
}