package com.faizdev.fundamentalsubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizdev.fundamentalsubmission.API.GitApi
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.DashboardEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.DashboardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DashboardViewmodel : ViewModel() {
    private var _getUserState = MutableStateFlow<DashboardState?>(null)
    var getUserState = _getUserState.asStateFlow()

    private var _getSearchUserState = MutableStateFlow<DashboardState?>(null)
    var getSearchUserState = _getSearchUserState.asStateFlow()

    fun onScreen(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.GetUser -> {
                viewModelScope.launch {
                    _getUserState.emit(DashboardState.Loading)
                    try {
                        val result = GitApi.create().Users()
                        _getUserState.emit(DashboardState.Success(result))
                    } catch (e: Exception) {
                        _getUserState.emit(DashboardState.Error(e.message.toString()))
                    }
                }
            }

            is DashboardEvent.GetSearch -> {
                viewModelScope.launch {
                    _getSearchUserState.emit(DashboardState.Loading)
                    try {
                        val result = GitApi.create().searchUserByUsername(event.query)
                        _getSearchUserState.emit(DashboardState.Success(result.items))
                    } catch (e: Exception) {
                        _getSearchUserState.emit(DashboardState.Error(e.message.toString()))
                    }
                }
            }


        }
    }
}
