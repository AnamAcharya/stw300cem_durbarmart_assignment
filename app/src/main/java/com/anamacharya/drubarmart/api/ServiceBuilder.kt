package com.anamacharya.drubarmart.api

import com.anamacharya.drubarmart.entity.Consumer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {
    private const val BASE_URL = "http://10.0.2.2:90/"

    //    private const val BASE_URL = "http://localhost:90/"
//    private const val BASE_URL = "http://192.168.1.77:90/"
    var token: String? = null
    var drink: String? = null
    var noodle: String? = null
    var username: String? = null
    var frozen: String? = null
    var fruit: String? = null
    var veg: String? = null
    var image: String? = null
   var data : Consumer? = null
    private val okHttp = OkHttpClient.Builder()

    //Create retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    //Generic function
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

    fun loadImagePath(): String {
        return BASE_URL
    }
}