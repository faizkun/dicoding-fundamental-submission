package com.faizdev.fundamentalsubmission.githubuser.dashboard.state

import com.faizdev.fundamentalsubmission.response.DetailResponse

sealed class TabState {
    object Loading : TabState()
    data class Success<T>(val TabList: List<T>) : TabState()
    data class Error(val message: String) : TabState()
}