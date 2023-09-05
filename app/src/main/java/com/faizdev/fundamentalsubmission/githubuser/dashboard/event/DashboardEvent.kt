package com.faizdev.fundamentalsubmission.githubuser.dashboard.event

import retrofit2.http.Query

sealed class DashboardEvent {
    object GetUser: DashboardEvent()
    data class GetSearch(val query: String?): DashboardEvent()
}