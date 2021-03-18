package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.LoginPage
import hu.tigra.pti.geb.page.MainPage
import hu.tigra.pti.geb.page.MyAccountPage
import hu.tigra.pti.geb.page.RegisterPage

import java.time.LocalDate

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
        registerPage.firstName = 'Elek'
        registerPage.lastName = 'Teszt'
        registerPage.password = 'teszt123'
        registerPage.dateOfBirth.valueFromLocalDate(LocalDate.of(1990, 5, 12))
        registerPage.newsletter.check()
        registerPage.offers.check()
        registerPage.registerButton.click()

        then: '5 hibaüzenet jelenik meg'
        registerPage.errorMessages.values.size() == 5

        when: 'Kitöltöm a jelszót újra és a "Your address" blokkban a mezőket, majd a "Register" gombra kattintok.'
        registerPage.password = 'teszt123'
        registerPage.company = 'Az én cégem kft'
        registerPage.address = 'Zöld utca 42.'
        registerPage.city = 'Pirosváros'
        registerPage.state = 'Florida'
        registerPage.postalCode = '12345'
        registerPage.other = 'Valami nagyon fontos információ...'
        registerPage.phone = '06123456789'
        registerPage.mobilePhone = '06987654321'
        registerPage.alias = 'Az én kicsi címem'
        registerPage.registerButton.click()

        then: 'Megjelenik a felhasználó adatai felület: "My account".'
        def myAccountPage = waitFor { at MyAccountPage }
        myAccountPage.header.text() == "MY ACCOUNT"
    }
}
