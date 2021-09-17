package com.anamacharya.drubarmart.repository

import com.anamacharya.drubarmart.api.ConsumerAPI
import com.anamacharya.drubarmart.api.MyApiRequest
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.entity.Consumer
import com.anamacharya.drubarmart.response.GetProfileResponse
import com.anamacharya.drubarmart.response.ImageResponse
import com.anamacharya.drubarmart.response.LoginResponse
import okhttp3.MultipartBody

class CustomerRepository : MyApiRequest() {
    val myApi = ServiceBuilder.buildService(ConsumerAPI::class.java)

    suspend fun registerUser(consumer: Consumer): LoginResponse {
        return apiRequest {
            myApi.registerUser(consumer)
        }
    }

    suspend fun checkUser(username: String, password: String): LoginResponse {
        return apiRequest {
            myApi.checkUser(username, password)
        }
    }

    suspend fun getCitizen(): GetProfileResponse {
        return apiRequest {
            myApi.getProfile(
                    ServiceBuilder.token!!,

                    )
        }
    }

    suspend fun uploadImage(Cemail: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            myApi.uploadImage(ServiceBuilder.token!!, Cemail, body)
        }
    }

    suspend fun updateMe(consumer: Consumer,username: String): GetProfileResponse {
        return apiRequest {
            myApi.updateMe(
                    ServiceBuilder.token!!, consumer,username)
        }
    }

    suspend fun logout(): LoginResponse {
        return apiRequest {
            myApi.logout(ServiceBuilder.token!!)
        }
    }
}