package com.anamacharya.drubarmart.repository

import com.anamacharya.drubarmart.api.ContactAPI
import com.anamacharya.drubarmart.api.MyApiRequest
import com.anamacharya.drubarmart.api.ServiceBuilder

import com.anamacharya.drubarmart.entity.Contact
import com.anamacharya.drubarmart.response.ContactResponse

class ContactRepository: MyApiRequest(){
    val myApi = ServiceBuilder.buildService(ContactAPI::class.java)

    suspend fun contactUser(contact: Contact) : ContactResponse {
        return apiRequest {
            myApi.contactUser(contact)
        }
    }
}