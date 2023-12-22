package com.saksham.jetpack.serversyncui.data.repository

import com.saksham.jetpack.serversyncui.utils.NetworkResult
import com.saksham.jetpack.serversyncui.data.remote.RemoteDataSource
import com.saksham.jetpack.serversyncui.domain.model.UiDataResponse
import kotlinx.coroutines.flow.Flow

class ServerSyncUiRepository(private val remoteDataSource: RemoteDataSource) {

    fun getServerSyncUiData(): Flow<NetworkResult<UiDataResponse>> {
        return remoteDataSource.getServerSyncUiData()
    }

}