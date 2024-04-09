package com.example.onlineshoppingapp.models

import android.os.Parcel
import android.os.Parcelable

class User (
    val id:String=  "",
    val firstname:String= "",
    val lastname:String= "",
    val email:String="",
    val image:String="",
    val gender:String="",
    val mobile:Long=0,
    val profileCompleted:Int=0

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(firstname)
        parcel.writeString(lastname)
        parcel.writeString(email)
        parcel.writeString(image)
        parcel.writeString(gender)
        parcel.writeLong(mobile)
        parcel.writeInt(profileCompleted)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
