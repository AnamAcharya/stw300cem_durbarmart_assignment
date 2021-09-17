package com.anamacharya.drubarmart.api

import com.anamacharya.drubarmart.entity.Order
import com.anamacharya.drubarmart.response.CartResponse
import com.anamacharya.drubarmart.response.GetOrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderAPI {
    @POST("user/order")
    suspend fun addOrder(
        @Header("Authorization") token:String,
        @Body order: Order
    ): Response<CartResponse>
    @GET("order")
    suspend fun getOrder(
             @Header("Authorization") token:String,
    ):Response<GetOrderResponse>
}