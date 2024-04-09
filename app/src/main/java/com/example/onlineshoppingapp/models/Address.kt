package com.example.onlineshoppingapp.models

import android.os.Parcel
import android.os.Parcelable

class Address(

    val id:String?="",
    val name:String?="",
    val phonenumber:String?="",

    val address:String?="",
    val pincode:String?="",
    var addressid:String? =""



):Parcelable {
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
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(phonenumber)
        parcel.writeString(address)
        parcel.writeString(pincode)
        parcel.writeString(addressid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}