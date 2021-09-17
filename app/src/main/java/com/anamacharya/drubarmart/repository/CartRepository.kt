package com.anamacharya.drubarmart.repository

import com.anamacharya.drubarmart.api.CartAPI
import com.anamacharya.drubarmart.api.MyApiRequest
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.model.CartModel
import com.anamacharya.drubarmart.response.CartResponse
import com.anamacharya.drubarmart.response.GetCartResponse


class CartRepository : MyApiRequest() {
    val myApi = ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addcart(cart: CartModel): CartResponse {
        return apiRequest {
            myApi.addCart(
                    ServiceBuilder.token!!,
                    cart
            )
        }
    }

    suspend fun getCart(): GetCartResponse {
        return apiRequest {
            myApi.getCart(
                    ServiceBuilder.token!!
            )
        }
    }
}