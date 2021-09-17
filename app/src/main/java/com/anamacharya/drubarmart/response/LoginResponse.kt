package com.anamacharya.drubarmart.response

import com.anamacharya.drubarmart.entity.Consumer

data class LoginResponse (
    val success : Boolean? = null,
    val token : String? = null,
    val data:String?=null,
    val userdata :Consumer?=null,
    val usertype:String?=null
)