package com.anamacharya.drubarmart.model

import android.os.Parcel
import android.os.Parcelable

data class NoodleModel(
    val noodleImg:String? =null,
    val noodleName:String? = null,
    val price:String? = null,
    val value:String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(noodleImg)
        parcel.writeString(noodleName)
        parcel.writeValue(price)
        parcel.writeValue(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoodleModel> {
        override fun createFromParcel(parcel: Parcel): NoodleModel {
            return NoodleModel(parcel)
        }

        override fun newArray(size: Int): Array<NoodleModel?> {
            return arrayOfNulls(size)
        }
    }

}