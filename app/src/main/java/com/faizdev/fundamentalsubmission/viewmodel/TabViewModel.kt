package com.faizdev.fundamentalsubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizdev.fundamentalsubmission.API.GitApi
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.DetailEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.TabEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.DetailState
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.TabState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TabViewModel : ViewModel(){
    private var _getFollowingList = MutableStateFlow<TabState?>(null)
    var getFollowingList = _getFollowingList.asStateFlow()

    private val _getFollowerList = MutableStateFlow<TabState?>(null)
    val getFollowerList = _getFollowerList.asStateFlow()


    fun onScreen(event: TabEvent) {
        when (event) {
            is TabEvent.GetFollowingList -> {
                viewModelScope.launch {
                    _getFollowingList.emit(TabState.Loading)
                    try {
                        val result = GitApi.create().getFollowingByUsername(event.query)
                        _getFollowingList.emit(TabState.Success(result))
                    } catch (e: Exception) {
                        _getFollowingList.emit(TabState.Error(e.message.toString()))
                    }
                }
            }

            is TabEvent.GetFollowerList -> {
                viewModelScope.launch {
                    _getFollowerList.emit(TabState.Loading)
                    try {
                        val result = GitApi.create().getFollowersByUsername(event.query)
                        _getFollowerList.emit(TabState.Success(result))
                    } catch (e: Exception) {
                        _getFollowerList.emit(TabState.Error(e.message.toString()))
                    }
                }
            }
        }
    }
}