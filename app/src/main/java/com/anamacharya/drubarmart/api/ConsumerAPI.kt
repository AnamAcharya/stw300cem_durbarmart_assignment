package com.anamacharya.drubarmart.api

import com.anamacharya.drubarmart.entity.Consumer
import com.anamacharya.drubarmart.response.GetProfileResponse
import com.anamacharya.drubarmart.response.ImageResponse
import com.anamacharya.drubarmart.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ConsumerAPI {
    //Register user
    @POST("user/register")
    suspend fun registerUser(
            @Body consumer: Consumer
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
            @Field("username") username: String,
            @Field("password") password: String
    ): Response<LoginResponse>

    @GET("logout")
    suspend fun logout(
            @Header("Authorization") token: String
    ): Response<LoginResponse>

    @POST("/profile")
    suspend fun getProfile(
            @Header("Authorization") token: String
    ): Response<GetProfileResponse>


    @PUT("updateMe/{username}")
    suspend fun updateMe(
            @Header("Authorization") token: String,
            @Body consumer: Consumer,
            @Path("username") username: String
    ): Response<GetProfileResponse>;


    @Multipart
    @PUT("user/image/{username}")
    suspend fun uploadImage(
            @Header("Authorization") token: String,
            @Path("username") username: String,
            @Part file: MultipartBody.Part
    ): Response<ImageResponse>


}
