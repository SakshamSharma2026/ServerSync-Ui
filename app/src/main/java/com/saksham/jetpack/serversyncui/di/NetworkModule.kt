package com.saksham.jetpack.serversyncui.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.saksham.jetpack.serversyncui.data.remote.RemoteDataSource
import com.saksham.jetpack.serversyncui.data.repository.ServerSyncUiRepository
import com.saksham.jetpack.serversyncui.domain.use_cases.ServerSyncUiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideServerSyncUiUseCase(repository: ServerSyncUiRepository): ServerSyncUiUseCase {
        return ServerSyncUiUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideServerSyncUiRepository(remoteDataSource: RemoteDataSource): ServerSyncUiRepository {
        return ServerSyncUiRepository(remoteDataSource)
    }

    @Singleton
    @Provides
    fun proveDatabaseReference(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }
}