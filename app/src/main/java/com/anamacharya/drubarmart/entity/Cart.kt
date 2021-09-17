package com.anamacharya.drubarmart.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class Cart (
    var ProductName:String?=null,
    var ProductImage:String?=null,
    var ProductPrice:String?=null,
    var ProductAvailable:String?=null,
    var ProductDesc:String?=null

):Parcelable {
    @PrimaryKey
    var pID:Int?=0
    constructor(parcel: Parcel) : this(

    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString()) {
        parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ProductName)
        parcel.writeString(ProductImage)
        parcel.writeString(ProductPrice)
        parcel.writeString(ProductDesc)
        parcel.writeString(ProductAvailable)
        parcel.writeValue(pID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}