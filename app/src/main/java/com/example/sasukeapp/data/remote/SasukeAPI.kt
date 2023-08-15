package com.example.sasukeapp.data.remote

import com.example.sasukeapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SasukeApi {

    @GET("/sasuke/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/sasuke/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse

}