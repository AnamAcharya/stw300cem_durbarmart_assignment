package com.anamacharya.drubarmart.repository

import com.anamacharya.drubarmart.api.MyApiRequest
import com.anamacharya.drubarmart.api.OrderAPI
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.entity.Order

import com.anamacharya.drubarmart.response.CartResponse
import com.anamacharya.drubarmart.response.GetOrderResponse

class OrderRepository : MyApiRequest() {
    val myApi = ServiceBuilder.buildService(OrderAPI::class.java)

    suspend fun addOrder(order:Order): CartResponse {
        return apiRequest {
            myApi.addOrder(
                ServiceBuilder.token!!,
                order
            )
        }
    }
    suspend fun getOrder(): GetOrderResponse {
        return apiRequest {
            myApi.getOrder(
                    ServiceBuilder.token!!
            )
        }
    }


}