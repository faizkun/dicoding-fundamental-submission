package com.faizdev.fundamentalsubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizdev.fundamentalsubmission.API.GitApi
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.DetailEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.DetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private var _getDetailUser = MutableStateFlow<DetailState?>(null)
    var getDetailUser = _getDetailUser.asStateFlow()


    fun onScreen(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetDataUser -> {
                viewModelScope.launch {
                    _getDetailUser.emit(DetailState.Loading)
                    try {
                        val result = GitApi.create().getDetailDatauser(event.query)
                        _getDetailUser.emit(DetailState.Success(result))
                    } catch (e: Exception) {
                        _getDetailUser.emit(DetailState.Error(e.message.toString()))
                    }
                }
            }


        }
    }
}