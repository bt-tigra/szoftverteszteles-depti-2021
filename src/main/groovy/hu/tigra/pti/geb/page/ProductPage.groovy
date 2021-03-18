package hu.tigra.pti.geb.page

import geb.Page

class ProductPage extends Page {

    static url = "/index.php?controller=product"

    static at = { body.displayed }

    static content = {
        body { $('body[id="product"]') }

    }

    def addToCartIsSuccessful() {
        waitFor { $('div[id="layer_cart"] h2', text: 'Product successfully added to your shopping cart').displayed }
    }
}
