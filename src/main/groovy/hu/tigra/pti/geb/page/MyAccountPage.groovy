package hu.tigra.pti.geb.page

import geb.Page

class MyAccountPage extends Page {

    static url = "/index.php?controller=my-account"

    static at = { title == 'My account - My Store' }

    static content = {
        header { $('h1[class="page-heading"]') }
    }
}
