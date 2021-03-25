package hu.tigra.pti.geb.page

import geb.Page
import geb.module.Checkbox

class OrderPage extends Page {

    static url = "/index.php?controller=order"

    static at = { title == 'Order - My Store' && header.displayed }

    static content = {
        header { $('h1[class="page-heading"]') }

        nextButton(required: false) { $('p.cart_navigation').$('.button.btn.btn-default.button-medium') }

        totalProductPrice { $('td[id="total_product"]').text().substring(1).toDouble() }

        updateDeliveryAddressButton { $('ul[id="address_delivery"]').$('a[title="Update"]') }

        shippingAgreeTerms(required: false) { $('input[id="cgv"]').module(Checkbox) }

        payByCheckButton(required: false) { $('a[class="cheque"]') }

        message(required: false) { $('p.alert.alert-success') }
    }
}
