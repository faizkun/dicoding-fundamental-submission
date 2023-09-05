package com.faizdev.fundamentalsubmission.githubuser.dashboard.event

sealed class DetailEvent {
    data class GetDataUser(val query: String?) :DetailEvent()
}