package com.artemiod.dogapimejorada.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/breed/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    // test
    @GET(value = "akita/images")
    suspend fun getListImg(): DogApi

    @GET(value = "{breed}/images")
    suspend fun getListImg(@Path("breed") breed: String): DogApi

    @GET(value = "{breed}/{subBreed}/images")
    suspend fun getListImg(@Path("breed") breed: String, @Path("subBreed") subBreed: String): DogApi
}

object DogApiService {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}