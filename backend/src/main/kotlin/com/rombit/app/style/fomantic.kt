package com.rombit.app.style

import kweb.classes
import kweb.id
import kweb.plugins.fomanticUI.FomanticUIClasses
import kweb.plugins.fomanticUI.FomanticUIPlugin

val fomanticPlugin get() = FomanticUIPlugin()
val fomantic : FomanticUIClasses get() = FomanticUIClasses()


val FomanticUIClasses.approve : FomanticUIClasses
    get() {
        classes("approve")
        return this
    }
