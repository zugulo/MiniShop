package com.example.api.network

import com.example.api.response.RemoteProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("product/search")
    suspend fun getProducts(
        @Query("lang") lang: String = "de",
        @Query("pageSize") pageSize: Int = 20,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): RemoteProductListResponse
}
