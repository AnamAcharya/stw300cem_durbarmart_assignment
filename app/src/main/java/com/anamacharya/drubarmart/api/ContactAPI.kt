package com.anamacharya.drubarmart.api


import com.anamacharya.drubarmart.entity.Contact
import com.anamacharya.drubarmart.response.ContactResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactAPI {
    @POST("user/contact")
    suspend fun contactUser(
        @Body contact: Contact
    ): Response<ContactResponse>

}