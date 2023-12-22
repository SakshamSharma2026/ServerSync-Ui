package com.saksham.jetpack.serversyncui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saksham.jetpack.serversyncui.utils.NetworkResult
import com.saksham.jetpack.serversyncui.domain.use_cases.ServerSyncUiUseCase
import com.saksham.jetpack.serversyncui.domain.model.UiDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ServerSyncUiViewModel @Inject constructor(private val serverSyncUiUseCase: ServerSyncUiUseCase) :
    ViewModel() {

    private val _dataFlow =
        MutableStateFlow<NetworkResult<UiDataResponse>>(NetworkResult.Loading())
    val getServerSyncUiData: StateFlow<NetworkResult<UiDataResponse>> get() = _dataFlow


    init {
        viewModelScope.launch {
            serverSyncUiUseCase.getServerSyncUiData().collect { value ->
                _dataFlow.value = value
            }
        }
    }
}