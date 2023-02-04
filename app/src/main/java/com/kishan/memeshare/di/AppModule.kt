package com.kishan.memeshare.di

import com.kishan.memeshare.common.Constants
import com.kishan.memeshare.data.remote.MemeApi
import com.kishan.memeshare.data.repository.MemeRepositoryImpl
import com.kishan.memeshare.domain.repository.MemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMemeApi(): MemeApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMemeRepository(api: MemeApi):MemeRepository{
        return MemeRepositoryImpl(api)
    }

}