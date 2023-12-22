package com.saksham.jetpack.serversyncui.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.saksham.jetpack.serversyncui.utils.NetworkResult
import com.saksham.jetpack.serversyncui.domain.model.UiDataResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val rootReference: DatabaseReference) {

    fun getServerSyncUiData(): Flow<NetworkResult<UiDataResponse>> = callbackFlow {
        trySend(NetworkResult.Loading())
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null) {
                    val response = snapshot.getValue(UiDataResponse::class.java)
                    trySend(NetworkResult.Success(response)).isSuccess
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(NetworkResult.Error(error.message))
            }

        }
        rootReference.addValueEventListener(valueEventListener)

        awaitClose { rootReference.removeEventListener(valueEventListener) }

    }
}
