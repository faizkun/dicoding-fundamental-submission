package com.faizdev.fundamentalsubmission.githubuser.dashboard.state

import com.faizdev.fundamentalsubmission.response.SearchResponse
import com.faizdev.fundamentalsubmission.response.UserResponse

sealed class DashboardState{
    object Loading: DashboardState()
    data class Success(val listUser : UserResponse) : DashboardState()
    data class Error(val message: String)   : DashboardState()
}

