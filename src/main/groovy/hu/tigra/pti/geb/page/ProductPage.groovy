package hu.tigra.pti.geb.page

import geb.Page

class ProductPage extends Page {

    static url = "/index.php?controller=product"

    static at = { body.displayed }

    static content = {
        body { $('body[id="product"]') }
        quantity { $('input[id="quantity_wanted"]') }
        size { $('select[id="group_1"]') }
        addToCartButton { $('button[name="Submit"]') }
        checkoutButton { $('div[id="layer_cart"]').$('a[title="Proceed to checkout"]') }
    }

    def addToCartIsSuccessful() {
        waitFor { $('div[id="layer_cart"] h2', text: 'Product successfully added to your shopping cart').displayed }
    }
}
