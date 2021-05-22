package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*
import kweb.plugins.jqueryCore.executeOnSelf

fun ElementCreator<*>.transferForm(handleOnTransfer: (to: InputElement, amount: InputElement) -> Unit): DivElement = div(fomantic.ui.modal) {
    var toInput: InputElement? = null
    var amount: InputElement? = null

    div(fomantic.header) {
        p().text("Transfer funds:")
    }

    div(fomantic.content) {
        form(fomantic.ui.form) {
            div(fomantic.field) {
                toInput = input(type = InputType.text, placeholder = "Transfer funds to", attributes = fomantic.id("to"))
                    .apply {
                        executeOnSelf("""
                        .keyup(function(e){
                            let text = $(this).val();
                            
                            if(text === undefined || text.length === 0) {
                                $('.ui.button#pk-submit').addClass('disabled');
                            } else {
                                $('.ui.button#pk-submit').removeClass('disabled');
                            }
                        })
                    """.trimIndent())
                    }

            }
            div(fomantic.field) {
                amount = input(type = InputType.number, placeholder = "Amount to transfer", attributes = fomantic.id("amount"))
                    .apply {
                        executeOnSelf("""
                        .keyup(function(e){
                            let text = $(this).val();
                            
                            if(text === undefined || text.length === 0) {
                                $('#transfer-sub').addClass('disabled');
                            } else {
                                $('#transfer-sub').removeClass('disabled');
                            }
                        })
                    """.trimIndent())
                    }

            }
        }
    }

    div(fomantic.actions) {
        div(fomantic.ui.approve.disabled.button.id("transfer-sub"))
            .text("Transfer")
            .apply {
                on.click { handleOnTransfer(toInput!!, amount!!) }
            }
    }
}
