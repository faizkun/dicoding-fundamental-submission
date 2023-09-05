package com.faizdev.kotpref

import com.chibatching.kotpref.KotprefModel

object Preferences : KotprefModel() {
    var theme by booleanPref(true)
}