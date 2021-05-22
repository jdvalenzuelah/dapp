package com.rombit.app.components

import com.rombit.app.style.fomantic
import com.rombit.token.TransactionLog
import kweb.*


fun  ElementCreator<*>.transactionList(transactions: Collection<TransactionLog>) {
    table(fomantic.ui.celled.table).new {
        thead().new {
            tr().new {
                th().text("From Address")
                th().text("To Address")
                th().text("Amount Transferred")
                th().text("Date")
            }
        }
        tbody().new {
            transactions.forEach { transaction ->
                tr().new {
                    td(mapOf("data-label" to "from")).text(transaction.from)
                    td(mapOf("data-label" to "to")).text(transaction.to)
                    td(mapOf("data-label" to "amount")).text(transaction.amount.toString())
                    td(mapOf("data-label" to "date")).text(transaction.date.toString())
                }
            }
        }
    }
}
