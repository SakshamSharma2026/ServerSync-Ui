package com.saksham.jetpack.serversyncui.domain.use_cases

import com.saksham.jetpack.serversyncui.domain.model.UiDataResponse
import com.saksham.jetpack.serversyncui.utils.NetworkResult
import com.saksham.jetpack.serversyncui.data.repository.ServerSyncUiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServerSyncUiUseCase @Inject constructor(private val serverSyncUiRepository: ServerSyncUiRepository) {

    suspend fun getServerSyncUiData(): Flow<NetworkResult<UiDataResponse>> =
        serverSyncUiRepository.getServerSyncUiData()
}