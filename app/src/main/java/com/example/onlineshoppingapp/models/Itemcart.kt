package com.example.onlineshoppingapp.models

import android.os.Parcel
import android.os.Parcelable

class Itemcart(
    var id: String? = "",
    val cartitemname: String? = "",
    val carttimage: String? = "",
    val cartprice: Long = 0,
    val cartdesc:String?="",
    var cartquantity:Long=0,
    var cproductstock:Long=0,
    var product_id:String?="",
    var cart_id:String?=""

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(cartitemname)
        parcel.writeString(carttimage)
        parcel.writeLong(cartprice)
        parcel.writeString(cartdesc)
        parcel.writeLong(cartquantity)
        parcel.writeLong(cproductstock)
        parcel.writeString(product_id)
        parcel.writeString(cart_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Itemcart> {
        override fun createFromParcel(parcel: Parcel): Itemcart {
            return Itemcart(parcel)
        }

        override fun newArray(size: Int): Array<Itemcart?> {
            return arrayOfNulls(size)
        }
    }
}

