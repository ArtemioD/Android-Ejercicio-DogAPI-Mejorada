package com.artemiod.dogapimejorada

import com.artemiod.dogapimejorada.model.ApiService
import junit.framework.TestCase.*

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogApiServiceTests: BaseTest() {

    private lateinit var service: ApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")

        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Test
    fun api_service_respond() {
        enqueue("dogs_photos.json")
        runBlocking {
            val apiResponse = service.getListImg()
            assertNotNull(apiResponse)
        }
    }

    @Test
    fun api_service_list_not_empty() {
        enqueue("dogs_photos.json")
        runBlocking {
            val apiResponse = service.getListImg()
            assertTrue("The list was empty", apiResponse.images.isNotEmpty())
        }
    }

    @Test
    fun api_service_list_not_empty_imgId() {
        enqueue("dogs_photos.json")
        runBlocking {
            val apiResponse = service.getListImg()
            assertEquals("The IDs did not match", "https://images.dog.ceo/breeds/doberman/doberman.jpg", apiResponse.images[0])
        }
    }
}