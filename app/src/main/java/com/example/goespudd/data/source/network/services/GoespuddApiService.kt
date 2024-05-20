package com.example.goespudd.data.source.network.services

import com.example.goespudd.BuildConfig
import com.example.goespudd.data.source.network.model.category.CategoryResponse
import com.example.goespudd.data.source.network.model.checkout.CheckoutRequestPayload
import com.example.goespudd.data.source.network.model.checkout.CheckoutResponse
import com.example.goespudd.data.source.network.model.menu.MenuResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GoespuddApiService {
    @GET("category")
    suspend fun getCategory(): CategoryResponse

    @GET("listmenu")
    suspend fun getMenu(
        @Query("category") category: String? = null,
    ): MenuResponse

    @POST("order")
    suspend fun createOrder(
        @Body payload: CheckoutRequestPayload,
    ): CheckoutResponse

    companion object {
        @JvmStatic
        operator fun invoke(): GoespuddApiService {
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(GoespuddApiService::class.java)
        }
    }
}
