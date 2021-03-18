package hu.tigra.pti.geb.page

import geb.Page

class SearchPage extends Page {

    static url = "/index.php?controller=search"

    static at = { title == 'Search - My Store' }

    static content = {
        listViewButton { $('li[id="list"] > a') }

        // A termékek árait tartalmazza szöveges formában
        prices { $('div.product-container div.right-block-content span[itemprop="price"]').collect{it.text()} }

        counterText { $('span.heading-counter') }
        sortInput { $('select[id="selectProductSort"]') }
        moreButtons { $('a[title="View"]') }
    }

    def counterEquals(int number) {
        counterText.text() == "${number} results have been found."
    }
}
