package com.anamacharya.drubarmart.response

import com.anamacharya.drubarmart.entity.Product

data class GetProductResponse(
        val success : Boolean? = null,
        val data : MutableList<Product>? = null,
        val alldata:MutableList<Product>?=null
)