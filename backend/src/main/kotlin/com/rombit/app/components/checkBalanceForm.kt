package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*

fun ElementCreator<DivElement>.checkBalanceForm(handleCheckBalance: (InputElement) -> Unit) {
    h4(fomantic.header).text("Check balance of:")
    form(fomantic.ui.form) {
        div(fomantic.field) {
            input(type = InputType.text, placeholder = "Address")
        }
        button(fomantic.ui.button, type = ButtonType.submit).text("Check")
    }

}
