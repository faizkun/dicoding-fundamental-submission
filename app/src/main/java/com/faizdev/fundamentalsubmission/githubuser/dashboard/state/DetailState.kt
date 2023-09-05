package com.faizdev.fundamentalsubmission.githubuser.dashboard.state

import com.faizdev.fundamentalsubmission.response.DetailResponse
import com.faizdev.fundamentalsubmission.response.UserResponse

sealed class DetailState {
    object Loading : DetailState()
    data class Success(val DetailList : DetailResponse) : DetailState()
    data class Error(val message: String)   : DetailState()
}