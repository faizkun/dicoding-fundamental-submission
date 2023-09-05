package com.faizdev.fundamentalsubmission.githubuser.dashboard.event

sealed class TabEvent {
    data class GetFollowingList(val query: String?) : TabEvent()
    data class GetFollowerList(val query: String?) : TabEvent()
}