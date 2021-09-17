package com.anamacharya.drubarmart.model

import android.os.Parcel
import android.os.Parcelable

class DrinkModel (
    val drinkImg:String?=null,
    val drinkName:String?=null,
    val price:String?= null,
    val value:String?=null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(drinkImg)
        parcel.writeString(drinkName)
        parcel.writeValue(price)
        parcel.writeValue(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FrozenModel> {
        override fun createFromParcel(parcel: Parcel): FrozenModel {
            return FrozenModel(parcel)
        }

        override fun newArray(size: Int): Array<FrozenModel?> {
            return arrayOfNulls(size)
        }
    }
}
