package com.example.onlineshoppingapp.utils

import android.app.Activity
import android.net.Uri
import android.webkit.MimeTypeMap


object Constants {
    const val USERS:String="users"
    const val mypreference:String="mypref"
    const val loggedinemail:String="logged_in_email"
    const val loggedinusername:String="logged_in_username"
    const val extrauserdetails:String="extra_user_details"

    const val gender:String="gender"
    const val mobile:String="mobile"
    const val IMAGE :String="image"
    const val completeprofile="profileCompleted"

    const val PRODUCT:String="products"
    const val PRODUCT_PRICE="productprice"
    const val PRODUCT_NAME="productname"
    const val PRODUCT_IMAGE="productimage"
    const val PRODUCT_DESCRIPTION="productdesc"
    const val PRODUCT_CATAGORY="productcatagory"
    const val PRODUCT_STOCK="productstock"
    const val USERID:String="id"
    const val PRODUCTID:String="product_id"
    const val EXTRA_PRODUCT_ID:String ="extra_product_id"
    const val EXTRA_PRODUCT_OWNER_ID:String="extra_product_owner_id"

    const val DEFAULT_CAR_QUANTITY:String="1"
    const val DEFAULT_STOCK_QUANTITY="1"
    const val CART_ITEMS:String="cart_items"
    const val CARTID:String= "cart_id"
    const val CART_QUANTITYFORUPDATE="cartquantity"

    const val SENDURLPROFILEFRAGMENT:String=""

    const val tshirt:String="T_Shirt"
    const val NONE="products"
    const val HOODIE="Hoodie"
    const val keychain="Key_Chain"
    const val poster="poster"
    const val figurine="figurine"

    const val ADDRESS="address"
    const val EXTRA_DETAILS_ADDRESS="addressdetails"
    const val addreess_requst_code:Int=121





    const val uriofimage:String="userprofileimage"





    fun getfileExtension(activity: Activity,uri: Uri?):String?{
               return MimeTypeMap.getSingleton()
                   .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}