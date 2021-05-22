package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*


fun ElementCreator<DivElement>.loginForm(handleContinue: (InputElement) -> Unit): DivElement = div(fomantic.ui.basic.modal) {
    var input: InputElement? = null

    div(fomantic.header) {
        p().text("Please enter account private key:")
    }

    div(fomantic.content) {
        form(fomantic.ui.form) {
            input = input(type = InputType.text)
            input!!.on.keypress { ke ->
                if(ke.code == "Enter")
                    handleContinue(input!!)
            }
        }
    }

    div(fomantic.actions) {
        div(fomantic.ui.approve.button)
            .text("Continue")
            .apply {
                on.click { handleContinue(input!!) }
            }
    }

}
