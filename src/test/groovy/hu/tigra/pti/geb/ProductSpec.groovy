package hu.tigra.pti.geb

import hu.tigra.pti.geb.page.MainPage
import hu.tigra.pti.geb.page.ProductPage
import hu.tigra.pti.geb.page.SearchPage

class ProductSpec extends BaseSpec {

    def 'Termék keresése és legdrágább kosárba tétele'() {

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
        waitFor { searchPage.prices.first() == '$50.99' }

        when: 'Rákattintok az első találaton lévő "More" gombra'
        searchPage.moreButtons.first().click()

        then: 'Megjelenik a termék oldala'
        def productPage = waitFor { at ProductPage }

        when: 'Mennyiségnek beállítom a 3, a méretnek pedig az M értékeket, majd az "Add to cart" gombra kattintok'
        productPage.quantity = '3'
        productPage.size = 'M'
        productPage.addToCartButton.click()

        then: 'Megjelenik a "Product successfully added to your shopping cart" felirat'
        productPage.addToCartIsSuccessful()
    }
}
