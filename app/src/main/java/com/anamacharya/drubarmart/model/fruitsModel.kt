package com.anamacharya.drubarmart.model

import android.os.Parcel
import android.os.Parcelable

data class fruitsModel (
        val fruitImg:String? = null,
        val fruitName:String? = null,
        val price:String? = null,
        val value:String? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(fruitImg)
                parcel.writeString(fruitName)
                parcel.writeValue(price)
                parcel.writeValue(value)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<fruitsModel> {
                override fun createFromParcel(parcel: Parcel): fruitsModel {
                        return fruitsModel(parcel)
                }

                override fun newArray(size: Int): Array<fruitsModel?> {
                        return arrayOfNulls(size)
                }
        }
}
