package hu.tigra.pti.geb.module

import geb.Module

import java.time.LocalDate

class Date extends Module {

    static content = {
        days { $('select[id="days"]') }
        months { $('select[id="months"]') }
        years { $('select[id="years"]') }
    }

    def value(String years, String months, String days) {
        this.years = years
        this.months = "${months} "
        this.days = days
    }

    def valueFromLocalDate(LocalDate localDate) {
        years = localDate.getYear()
        months = localDate.getMonthValue()
        days = localDate.getDayOfMonth()
    }
}
