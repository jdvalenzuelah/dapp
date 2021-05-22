package com.rombit.app.components

import com.rombit.app.style.fomantic
import com.rombit.token.TransactionLog
import kweb.*


fun  ElementCreator<*>.transactionList(transactions: Collection<TransactionLog>) {

    if(transactions.isNotEmpty()) {
        table(fomantic.ui.celled.table).new {
            thead().new {
                tr().new {
                    th().text("Details")
                }
            }
            tbody().new {
                transactions.forEach { transaction ->
                    tr().new {
                        td(mapOf("data-label" to "from")).new {
                            div {
                                p().text("From: ${transaction.from}")
                                p().text("To: ${transaction.to}")
                                p().text("Amount: ${transaction.amount}")
                                p().text("Date: ${transaction.date.toString()}")
                            }
                        }
                    }
                }
            }
        }
    } else {
        p().text("No history yet. Make a transaction to see it reflected here!")
    }

}
