package hu.tigra.pti.geb

import geb.spock.GebReportingSpec
import hu.tigra.pti.geb.domain.User
import hu.tigra.pti.geb.page.LoginPage

abstract class BaseSpec extends GebReportingSpec {

    def login(User user) {
        if ($('a[class="logout"]').displayed) {
            return
        }
        def loginPage = to LoginPage
        loginPage.login(user.emailAddress, user.password)

        return loginPage
    }
}
