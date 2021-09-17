package com.anamacharya.drubarmart.response

import com.anamacharya.drubarmart.entity.Consumer

data class GetProfileResponse(
    val success:Boolean?=null,
    val profile:ArrayList<Consumer>?=null
)