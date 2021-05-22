package com.rombit.app.components

import com.rombit.app.style.fomantic
import kweb.*

fun ElementCreator<*>.mainView(pageTile: String, description: String = "", content: ElementCreator<DivElement>.() -> Unit) {
    div(fomantic.ui.main.container) {
        div(fomantic.column) {

            if(description.isNotEmpty()) {
                div(fomantic.ui.vertical.segment) {
                    div(fomantic.ui.message) {
                        p().text(description)
                    }
                }
            }

            div(fomantic.ui.vertical.segment) {
                h1(fomantic.ui.dividing.header).text(pageTile)
                content(this)
            }
        }
    }
}
