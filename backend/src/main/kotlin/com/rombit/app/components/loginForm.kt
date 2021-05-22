package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*
import kweb.plugins.jqueryCore.executeOnSelf


fun ElementCreator<DivElement>.loginForm(handleContinue: (InputElement) -> Unit): DivElement = div(fomantic.ui.basic.modal) {
    var input: InputElement? = null

    div(fomantic.header) {
        p().text("Please enter account private key:")
    }

    div(fomantic.content) {
        form(fomantic.ui.form) {
            input = input(type = InputType.text)
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

            input!!.on.keypress { ke ->
                if(ke.code == "Enter")
                    handleContinue(input!!)
            }
        }
    }

    div(fomantic.actions) {
        div(fomantic.ui.disabled.approve.button.id("pk-submit"))
            .text("Continue")
            .apply {
                on.click { handleContinue(input!!) }
            }
    }

}
