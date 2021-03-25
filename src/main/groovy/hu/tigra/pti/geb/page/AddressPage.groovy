package hu.tigra.pti.geb.page

import geb.Page

class AddressPage extends Page {

    static url = "/index.php?controller=address"

    static at = { title == 'Address - My Store' }

    static content = {

    }
}
