package com.anamacharya.drubarmart.repository

import com.anamacharya.drubarmart.api.MyApiRequest
import com.anamacharya.drubarmart.api.ProductAPI
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.response.GetProductResponse

class ProductRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ProductAPI::class.java)

    suspend fun getNoodle() : GetProductResponse {
        return apiRequest {
            myApi.getNoodle(
                    ServiceBuilder.noodle!!
            )
        }
    }
    suspend fun getDrink() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.drink!!
            )
        }
    }
    suspend fun getFrozen() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.frozen!!
            )
        }
    }
    suspend fun getfruit() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.fruit!!
            )
        }
    }
    suspend fun getVeg() : GetProductResponse {
        return apiRequest {
            myApi.getProduct(
                    ServiceBuilder.veg!!
            )
        }
    }



    suspend fun getAllProduct() : GetProductResponse {
        return apiRequest {
            myApi.getAllProduct()

        }
    }

}