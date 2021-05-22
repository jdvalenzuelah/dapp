package com.rombit.app.components

import com.rombit.app.style.fomantic
import com.rombit.token.Account
import kweb.*

fun ElementCreator<*>.accountList(accounts: Collection<Account>) {
    table(fomantic.ui.celled.table).new {
        thead().new {
            tr().new {
                th().text("Address")
                th().text("Balance")
            }
        }
        tbody().new {
            accounts.forEach { account ->
                tr().new {
                    td(mapOf("data-label" to "address")).text(account.address)
                    td(mapOf("data-label" to "balance")).text(account.amount.toString())
                }
            }
        }
    }
}
