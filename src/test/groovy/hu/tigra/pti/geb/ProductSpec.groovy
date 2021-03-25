package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.AddressPage
import hu.tigra.pti.geb.page.LoginPage
import hu.tigra.pti.geb.page.MainPage
import hu.tigra.pti.geb.page.OrderPage
import hu.tigra.pti.geb.page.ProductPage
import hu.tigra.pti.geb.page.SearchPage

class ProductSpec extends BaseSpec {

    def 'Termék keresése és legdrágább kosárba tétele, majd megrendelése'() {

        given: 'Megérkezünk a főoldalra.'
        def mainPage = waitFor { to MainPage }

        when: 'Beírom a keresőbe: "printed" és a keresésre kattintok'
        mainPage.searchInput = 'printed'
        mainPage.searchButton.click()

        then: 'Megjelenik a találati oldal és a találatok száma 5'
        def searchPage = waitFor { at SearchPage }
        searchPage.counterEquals(5)

        when: 'Átváltok listanézetre és rendezem a termékeket ár szerint csökkenő sorrendbe'
        searchPage.listViewButton.click() // lista nézetre váltás
        searchPage.sortInput = 'Price: Highest first'

        then: 'Megvárom míg az első találat ára $50.99'
        // hint: waitFor-nak bármilyen logikai kifejezést megadhatunk
        waitFor { searchPage.prices.first() == '$' + highestPrice }

        when: 'Rákattintok az első találaton lévő "More" gombra'
        searchPage.moreButtons.first().click()

        then: 'Megjelenik a termék oldala'
        def productPage = waitFor { at ProductPage }

        when: 'Mennyiségnek beállítom a 3, a méretnek pedig az M értékeket, majd az "Add to cart" gombra kattintok'
        productPage.quantity = quantity
        productPage.size = 'M'
        productPage.addToCartButton.click()

        then: 'Megjelenik a "Product successfully added to your shopping cart" felirat'
        productPage.addToCartIsSuccessful()

        when: 'Rákattintok a "Proceed to checkout" gombra'
        productPage.checkoutButton.click()

        then: 'Megérkezek a rendelés oldalra, aminek a címe: "SHOPPING-CART SUMMARY"' +
              'és a "Total products" résznél szereplő összeg megegyezik az 50.99 háromszorosával'
        // Itt használható az OrderPage
        def orderPage = waitFor { at OrderPage }
        // hint: A cím több mindent is tartalmazhat, egyenlőséggel nem jó vizsgálni
        orderPage.header.text().startsWith('SHOPPING-CART SUMMARY')
        orderPage.totalProductPrice == quantity * highestPrice

        when: 'Rákattintok a "Proceed to checkout" gombra'
        // hint: A továbblépésekhez használható az orderPage.nextButton változó
        orderPage.nextButton.click()

        then: 'Megjelenik a bejelentkezés oldal'
        // Itt használható a korábban létrehozott LoginPage
        def loginPage = waitFor { at LoginPage }

        when: 'Elvégzem a bejelentkezést'
        loginPage.login(Constant.USERS.HELYES_FELHASZNALO)

        then: 'Visszakerülök a rendelés oldalra, aminek a címe: "ADDRESSES"'
        waitFor { at OrderPage }
        orderPage.header.text() == 'ADDRESSES'

        when: 'A "YOUR DELIVERY ADDRESS" részben lévő címen módosítani akarok, ezért az "Update" gombra kattintok'
        orderPage.updateDeliveryAddressButton.click()

        then: 'A cím szerkesztő oldalára jutok'
        // Itt használható az AddressPage
        def addressPage = waitFor { at AddressPage }

        when: 'Egy mező értékét átírom és a "Save" gombra kattintok'
        addressPage.postalCode = '98765'
        addressPage.saveButton.click()

        then: 'Visszakerülök a rendelés oldalra, aminek a címe: "ADDRESSES"'
        waitFor { at OrderPage }
        orderPage.header.text() == 'ADDRESSES'

        when: 'Rákattintok a "Proceed to checkout" gombra'
        orderPage.nextButton.click()

        then: 'Megjelenik a rendelés oldal "SHIPPING" című része'
        orderPage.header.text() == 'SHIPPING'

        when: 'Bepipálom az "I agree to the terms..." dobozt és rákattintok a "Proceed to checkout" gombra'
        orderPage.shippingAgreeTerms.check()
        orderPage.nextButton.click()

        then: 'Megjelenik a rendelés oldal "PLEASE CHOOSE YOUR PAYMENT METHOD" című része'
        orderPage.header.text() == 'PLEASE CHOOSE YOUR PAYMENT METHOD'

        when: 'Kiválasztom a "Pay by check" opciót'
        orderPage.payByCheckButton.click()

        then: 'Megjelenik a rendelés oldal "ORDER SUMMARY" című része'
        waitFor { orderPage.header.text() == 'ORDER SUMMARY' }

        when: 'Rákattintok az "I confirm my order" gombra'
        orderPage.nextButton.click()

        then: 'Megjelenik a sikeres rendelés üzenete: "Your order on My Store is complete."'
        waitFor { orderPage.message.text() == 'Your order on My Store is complete.' }

        where:
        quantity = 3
        highestPrice = 50.99
    }
}
