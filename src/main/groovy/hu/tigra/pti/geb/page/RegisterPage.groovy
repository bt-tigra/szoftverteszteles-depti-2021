package hu.tigra.pti.geb.page

import geb.Page
import geb.module.Checkbox
import geb.module.RadioButtons
import hu.tigra.pti.geb.module.Date
import hu.tigra.pti.geb.module.ErrorMessages

class RegisterPage extends Page {

    static url = 'index.php?controller=authentication&back=my-account#account-creation'

    static at = { pageTitle.displayed }

    static content = {
        pageTitle { $('h1', text: 'CREATE AN ACCOUNT') }

        gender { $('input[type="radio"][name="id_gender"]').module(RadioButtons) }
        firstName { $('input[id="customer_firstname"]') }
        lastName { $('input[id="customer_lastname"]') }
        password { $('input[id="passwd"]') }
        dateOfBirth { module(Date) }
        newsletter { $('input[id="newsletter"]').module(Checkbox) }
        offers { $('input[id="optin"]').module(Checkbox) }

        registerButton { $('button[id="submitAccount"]') }
        errorMessages(required: false) { $('div[class="alert alert-danger"]:first-of-type').module ErrorMessages }

        company { $('input[id="company"]') }
        address { $('input[id="address1"]') }
        city { $('input[id="city"]') }
        state { $('select[id="id_state"]') }
        postalCode { $('input[id="postcode"]') }
        other { $('textarea[id="other"]') }
        phone { $('input[id="phone"]') }
        mobilePhone { $('input[id="phone_mobile"]') }
        alias { $('input[id="alias"]') }
    }

}
