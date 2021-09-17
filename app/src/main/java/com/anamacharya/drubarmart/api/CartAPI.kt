package com.anamacharya.drubarmart.api

import com.anamacharya.drubarmart.model.CartModel
import com.anamacharya.drubarmart.response.CartResponse
import com.anamacharya.drubarmart.response.GetCartResponse
import retrofit2.Response
import retrofit2.http.*

interface CartAPI {


    @POST("cart/add")
    suspend fun addCart(
            @Header("Authorization") token:String,
            @Body cart: CartModel
    ): Response<CartResponse>

    @GET("cart")
    suspend fun getCart(
        @Header("Authorization") token:String,
    ):Response<GetCartResponse>

}