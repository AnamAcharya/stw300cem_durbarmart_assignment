package com.anamacharya.drubarmart.response

import com.anamacharya.drubarmart.model.CartModel

data class GetCartResponse(
        val success : Boolean? = null,
        val data : MutableList<CartModel>? = null
)