package com.anamacharya.drubarmart.model

import android.os.Parcel
import android.os.Parcelable

data class OrderModel(
        var ProductName:String?=null,
        var ProductImage:String?=null,
        var ProductPrice:String?=null,
        var username:String?=null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ProductName)
        parcel.writeString(ProductImage)
        parcel.writeString(ProductPrice)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderModel> {
        override fun createFromParcel(parcel: Parcel): OrderModel {
            return OrderModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderModel?> {
            return arrayOfNulls(size)
        }
    }
}