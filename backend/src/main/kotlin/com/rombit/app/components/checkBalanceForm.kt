package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*

fun ElementCreator<DivElement>.checkBalanceForm(handleCheckBalance: (InputElement) -> Unit) {
    var input: InputElement? = null
    h4(fomantic.header).text("Check balance of:")
    form(fomantic.ui.form) {
        div(fomantic.field) {
            input = input(type = InputType.text, placeholder = "Address")
            input!!.on.keypress { ke ->
                if(ke.code == "Enter")
                    handleCheckBalance(input!!)
            }
        }
        button(fomantic.ui.button).text("Check")
            .apply {
                on.click { handleCheckBalance(input!!) }
            }
    }

}
