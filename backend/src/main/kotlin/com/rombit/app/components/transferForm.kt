package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*

fun ElementCreator<*>.transferForm(): DivElement = div(fomantic.ui.modal) {
    div(fomantic.header) {
        p().text("Transfer funds:")
    }

    div(fomantic.content) {
        form(fomantic.ui.form) {
            div(fomantic.field) {
                input(type = InputType.text, placeholder = "to")
            }
            div(fomantic.field) {
                input(type = InputType.text, placeholder = "amount")
            }
        }
    }

    div(fomantic.actions) {
        div(fomantic.ui.approve.button)
            .text("Transfer")
    }
}
