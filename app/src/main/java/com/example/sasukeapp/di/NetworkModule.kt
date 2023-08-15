package com.example.sasukeapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.sasukeapp.data.local.SasukeDatabase
import com.example.sasukeapp.data.remote.SasukeApi
import com.example.sasukeapp.data.repository.RemoteDataSourceImpl
import com.example.sasukeapp.domain.repository.RemoteDataSource
import com.example.sasukeapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    // converter-factory : convert all json response from server to api response
    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideSasukeApi(retrofit: Retrofit): SasukeApi {
        return retrofit.create(SasukeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        sasukeApi: SasukeApi,
        sasukeDatabase: SasukeDatabase
    ): RemoteDataSource {
       return  RemoteDataSourceImpl(
           sasukeApi = sasukeApi ,
           sasukeDatabase = sasukeDatabase
       )
    }
}