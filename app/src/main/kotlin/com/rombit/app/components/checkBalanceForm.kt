package com.rombit.app.components

import com.rombit.app.style.approve
import com.rombit.app.style.fomantic
import kweb.*
import kweb.state.KVar
import kweb.state.render

fun ElementCreator<DivElement>.checkBalanceForm(handleCheckBalance: (KVar<Boolean>, InputElement) -> Unit) {
    val loading = KVar(false)
    var input: InputElement? = null
    h4(fomantic.header).text("Check balance of:")
    form(fomantic.ui.form) {
        div(fomantic.field) {
            input = input(type = InputType.text, placeholder = "Address")
            input!!.on.keypress { ke ->
                if(ke.code == "Enter")
                    handleCheckBalance(loading, input!!)
            }
        }
        render(loading) {
            val button = button(fomantic.ui.button).text("Check")
                .apply {
                    on.click { handleCheckBalance(loading, input!!) }
                    if(it) addClasses("loading") else removeClasses("loading")
                }
        }
    }

}
