package com.anamacharya.drubarmart.model

import android.os.Parcel
import android.os.Parcelable

data class CartModel(
        var ProductName:String?=null,
        var ProductImage:String?=null,
        var ProductPrice:String?=null,
        var ProductAvailable:String?=null,
        var ProductDesc:String?=null,
        var username:String?=null
) :Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(ProductName)
                parcel.writeString(ProductImage)
                parcel.writeString(ProductPrice)
                parcel.writeString(ProductAvailable)
                parcel.writeString(ProductDesc)
                parcel.writeString(username)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<CartModel> {
                override fun createFromParcel(parcel: Parcel): CartModel {
                        return CartModel(parcel)
                }

                override fun newArray(size: Int): Array<CartModel?> {
                        return arrayOfNulls(size)
                }
        }
}