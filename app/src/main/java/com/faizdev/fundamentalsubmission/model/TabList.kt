package com.faizdev.fundamentalsubmission.model

import androidx.compose.runtime.Composable

data class TabList(
    val title : String
)

val listTab = listOf(

    TabList(
        title = "Followers"
    ),

    TabList(
        title = "Following"
    )
)


