package com.example.onlineshoppingapp.models

import android.os.Parcel
import android.os.Parcelable

class ProductsCatagory(
    val id: String? = "",
    val productname: String? = "",
    val productimage: String? = "",
    val productprice: Long = 0,
    val productdesc:String?="",
    val productcatagory:String?="",
    val productstock:Long=0,
    var product_id:String?=""

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),

        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(productname)
        parcel.writeString(productimage)
        parcel.writeLong(productprice)
        parcel.writeString(productdesc)
        parcel.writeString(productcatagory)
        parcel.writeLong(productstock)
        parcel.writeString(product_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Products> {
        override fun createFromParcel(parcel: Parcel): Products {
            return Products(parcel)
        }

        override fun newArray(size: Int): Array<Products?> {
            return arrayOfNulls(size)
        }
    }
}

