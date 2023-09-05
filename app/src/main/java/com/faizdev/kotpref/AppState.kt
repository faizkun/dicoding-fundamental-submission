package com.faizdev.kotpref

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AppState {
    var theme by mutableStateOf(Preferences.theme)
}