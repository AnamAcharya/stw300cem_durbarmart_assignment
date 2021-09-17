package com.anamacharya.drubarmart.api

import com.anamacharya.drubarmart.response.GetProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductAPI {

  @GET("product/all/{ProductType}")
  suspend fun getProduct(
          @Path("ProductType")ProductType:String
  ):Response<GetProductResponse>
  @GET("product/all/{ProductType}")
  suspend fun getNoodle(
          @Path("ProductType")ProductType:String
  ):Response<GetProductResponse>

  suspend fun getFrozen(
          @Path("ProductType")ProductType:String
  ):Response<GetProductResponse>

  suspend fun getFruit(
          @Path("ProductType")ProductType:String
  ):Response<GetProductResponse>

  suspend fun getVeg(
          @Path("ProductType")ProductType:String
  ):Response<GetProductResponse>

  @GET("product/all")
  suspend fun getAllProduct(
  ):Response<GetProductResponse>


}