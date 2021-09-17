package com.anamacharya.drubarmart.response


import com.anamacharya.drubarmart.model.OrderModel

data class GetOrderResponse(
        val success : Boolean? = null,
        val data : MutableList<OrderModel>? = null
)