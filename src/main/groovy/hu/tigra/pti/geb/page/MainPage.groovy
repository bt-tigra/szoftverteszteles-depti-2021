package hu.tigra.pti.geb.page

import geb.Page

class MainPage extends Page {

    static url = "/index.php"

    static at = { title == 'My Store' }

    static content = {
        loginButton { $('a[class="login"]') }
        searchInput { $('input[id="search_query_top"]') }
        searchButton { $('button[name="submit_search"]') }
    }
}
