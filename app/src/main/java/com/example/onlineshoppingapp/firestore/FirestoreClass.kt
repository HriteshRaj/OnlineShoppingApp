package com.example.onlineshoppingapp.firestore
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.onlineshoppingapp.models.Itemcart
import com.example.onlineshoppingapp.AddProductsActivity
import com.example.onlineshoppingapp.AddressActivity
import com.example.onlineshoppingapp.AddressEditTextActivity
import com.example.onlineshoppingapp.DashBoardFragment
import com.example.onlineshoppingapp.HomeFragment
import com.example.onlineshoppingapp.LoginActivity

import com.example.onlineshoppingapp.RegisterActivity
import com.example.onlineshoppingapp.UserProfileActivity
import com.example.onlineshoppingapp.models.Products
import com.example.onlineshoppingapp.models.User
import com.example.onlineshoppingapp.CartListActivity
import com.example.onlineshoppingapp.PlaceOrderActivity
import com.example.onlineshoppingapp.ProductsDetailsActivity
import com.example.onlineshoppingapp.models.Address

import com.example.onlineshoppingapp.utils.Constants
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirestoreClass {
    private val myFirestore= Firebase.firestore

    //this will help in storing all data of firestore so
    //dont do these things in ui lebel which is activity

    fun registerUser(activity:RegisterActivity,userinfo: User){

        myFirestore.collection(Constants.USERS).document(userinfo.id).set(userinfo, SetOptions.merge())

            .addOnSuccessListener {
               activity.userRegistrationsuccess()
            }
            .addOnFailureListener{
               Log.d("FIRESTORE CLASS","firestroec class")
                activity.hideprogressdialog()
            }

    }
    fun getcurrentUserId():String{
     val currentUser= FirebaseAuth.getInstance().currentUser

        var currentUserId=""
        if(currentUser!=null){
            currentUserId=currentUser.uid

        }
        return currentUserId
    }
    fun getUserDetails(activity: Activity){
        myFirestore.collection(Constants.USERS)
            .document(getcurrentUserId()).get()
            .addOnSuccessListener {
            document->
                val user=document.toObject(User::class.java)!!

                val sharedpref=activity.getSharedPreferences(Constants.mypreference, Context.MODE_PRIVATE)
                val editor:SharedPreferences.Editor=sharedpref.edit()
                editor.putString(Constants.loggedinusername, user.firstname)
                editor.putString(Constants.loggedinemail,user.email)
                editor.apply()


                when(activity){
                    is LoginActivity->{
                        activity.userloggedinSucess(user)
                    }

                }
            }
            .addOnFailureListener{

            }

    }
    fun updateUserdetails(activity: Activity,userhashmap:HashMap<String,Any>){
        myFirestore.collection(Constants.USERS).document(getcurrentUserId())
            .update(userhashmap)
            .addOnSuccessListener {


            }
            .addOnFailureListener{

            }


    }

    fun uploadimagetofirebase(activity: Activity,imagefileuri: Uri?){

        val sref:StorageReference=FirebaseStorage.getInstance().reference.child(
            Constants.uriofimage + System.currentTimeMillis()+
                    "." + Constants.getfileExtension(activity,imagefileuri)

        )
        sref.putFile(imagefileuri!!)
            .
                addOnSuccessListener {
                    imagesnapshot->

                    Log.e("firebase image url",imagesnapshot.metadata!!.reference!!.downloadUrl.toString())
                    imagesnapshot.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            uri->
                            Log.e("download image uri",uri.toString())

                            when(activity){
                                is UserProfileActivity->
                                {
                                    activity.uploadimagesuccess(uri.toString())
                                }
                                is AddProductsActivity->{
                                    activity.uploadimagesuccess(uri.toString())
                                }

                            }

                        }
                        .addOnFailureListener{exception->
                            when(activity){
                                is UserProfileActivity->{
                                    activity.hideprogressdialog()
                                }
                                is AddProductsActivity->{
                                    activity.hideprogressdialog()
                                }
                            }
                            Log.e(activity.javaClass.simpleName,exception.message,exception)

                        }

                }


    }
    fun getproductinfo(activity: AddProductsActivity,productinfo: Products){
        myFirestore.collection(Constants.PRODUCT)
            .document()
            .set(productinfo, SetOptions.merge())
            .addOnSuccessListener {
            activity.productUploadSuccess()
            }
            .addOnFailureListener{

            }


    }

    fun getProductList(fragment: Fragment){
        myFirestore.collection(Constants.PRODUCT)
            .whereEqualTo(Constants.USERID,getcurrentUserId())
            .get()
            .addOnSuccessListener {
                document->
                Log.e("product list",document.documents.toString())
                val productList:ArrayList<Products> = ArrayList()
                for(i in document.documents){
                    val product=i.toObject(Products::class.java)
                    product!!.product_id=i.id
                    productList.add(product)
                    Log.e("in firesore class", product.toString())

                }
                when(fragment){
                    is HomeFragment->{
                      fragment.sucessProductListFromFirestore(productList)

                    }
                }


            }
            .addOnFailureListener{

            }
    }
    fun getProductListbyCatagory(fragment: DashBoardFragment,catagory:String){
        myFirestore.collection(Constants.PRODUCT)
            .whereEqualTo(Constants.PRODUCT_CATAGORY,catagory)
            .get()
            .addOnSuccessListener { document ->
                val productList:ArrayList<Products> = ArrayList()
                for (i in document) {
                    val product = i.toObject(Products::class.java)
                    productList.add(product!!)
                }
                when(fragment){
                    is DashBoardFragment->{
                        fragment.FetchProductsByCatagory(productList)
                    }
                }

            }
            .addOnFailureListener { exception ->
                // Handle errors here
              // or handle error with another callback
            }

    }



    fun getProductItemList(fragment: DashBoardFragment){
        myFirestore.collection(Constants.PRODUCT)
            .get()
            .addOnSuccessListener {
                document->
                val productItemList:ArrayList<Products> = ArrayList()

                for(i in document.documents){
                    val productitem=i.toObject(Products::class.java)
                    productitem!!.product_id=i.id
                    productItemList.add(productitem)
                }
                fragment.sucessDashBoardItemList(productItemList)



            }
            .addOnFailureListener{
            e->
                fragment.hideprogressdialog()
                Log.e("error is getting getproductitem list","error error")

            }

    }
    fun deleteProduct(fragment:HomeFragment,productId:String){
        myFirestore.collection(Constants.PRODUCT)
            .document(productId)
            .delete()
            .addOnSuccessListener {
                fragment.deleteproductSucess()
            }.addOnFailureListener{
                e->
                fragment.hideprogressdialog()
                Log.e(fragment.requireActivity().javaClass.simpleName,"ERROR WHILE DELETING",e)
            }

    }

    fun getProductDetails(activity: ProductsDetailsActivity,productId: String){
        myFirestore.collection(Constants.PRODUCT)
            .document(productId)
            .get()
            .addOnSuccessListener {
                document->
                Log.e("got the product details","product details sucess")
                val product=document.toObject(Products::class.java)
                activity.productDetailsSucess(product!!)



            }
            .addOnFailureListener{

            }
    }


    fun addCartItems(activity: ProductsDetailsActivity,addtocart: Itemcart){
        myFirestore.collection(Constants.CART_ITEMS)
            .document()
            .set(addtocart, SetOptions.merge())
            .addOnSuccessListener {
                Log.e("cart details sucess","cart Prdocut detaisla ctivity")
                activity.addtocartSuccess()

            }
            .addOnFailureListener{
                Log.e("the cart fail","productdetaislactivyt")
            }
    }



    fun getCarList(activity:Activity){
        myFirestore.collection(Constants.CART_ITEMS)
            .whereEqualTo(Constants.USERID,getcurrentUserId())
            .get()
            .addOnSuccessListener {
                document->
                val list:ArrayList<Itemcart> = ArrayList()
                for(i in document.documents){
                    var cartItem=i.toObject(Itemcart::class.java)!!
                    cartItem.id=i.id
                    list.add(cartItem)
                }
                when(activity){
                    is CartListActivity->{
                        activity.sucessCartItemList(list)
                    }

                        is PlaceOrderActivity->{
                            activity.sucessCartItemList(list)

                    }

                }
            }
            .addOnFailureListener{
                Log.e("cart not happened","cartlist error")
            }



    }
    fun getallCartList(activity: Activity){
        myFirestore.collection(Constants.PRODUCT)
            .get()
            .addOnSuccessListener {document->
                val productlist:ArrayList<Products> = ArrayList()
                for(i in document.documents){
                    val product=i.toObject(Products::class.java)
                    product!!.product_id=i.id
                    productlist.add(product)

                }
                when(activity){
                    is CartListActivity->{
                        activity.succesproductListfromFirestore(productlist)
                    }
                    is PlaceOrderActivity->{
                        activity.succesproductListfromFirestore(productlist)

                    }

                }

            }
            .addOnFailureListener{e->
                Log.e("erorr in getting allcart list","error",e)
            }
    }

    fun deleteproductcart(context: Context,cartId:String){
        myFirestore.collection(Constants.CART_ITEMS)
            .document(cartId)
            .delete()
            .addOnSuccessListener {


                       when(context){
                           is CartListActivity->{
                               context.deleteproductSuccess()

            }
            }



            }.addOnFailureListener{
                    e->

                Log.e("activity","ERROR WHILE DELETING",e)
            }

    }

    fun updatecart(context: Context,cartId: String,itemhashmap:HashMap<String,Any>){
        myFirestore.collection(Constants.CART_ITEMS)
            .document(cartId)
            .update(itemhashmap)
            .addOnSuccessListener {
                    document->
                when(context){
                    is CartListActivity->{
                        context.cartupdatesucess()
                    }
                }



            }
            .addOnFailureListener {
                    e->

                Log.e("cart update firestore","ERROR WHILE DELETING",e)
            }
    }

  fun addAddress(activity: AddressEditTextActivity,addressinfo:Address){
      myFirestore.collection(Constants.ADDRESS)
          .document()
          .set(addressinfo, SetOptions.merge())
          .addOnSuccessListener {
                activity.addressSuccess()
          }.addOnFailureListener {
            e->
                Log.e("firestore addressclass","in firestore class",e)

          }
  }
    fun getAddressList(activity: AddressActivity){
        myFirestore.collection(Constants.ADDRESS)
            .whereEqualTo(Constants.USERID,getcurrentUserId())
            .get()
            .addOnSuccessListener {
                document->
                val addresslist:ArrayList<Address> = ArrayList()

                for(i in document.documents){
                    val address =i.toObject(Address::class.java)
                    address!!.addressid=i.id
                    addresslist.add(address)
                }
                activity.successAddressListfromFirestore(addresslist)




            }.addOnFailureListener {exception->
                Log.e("getAddress", "Error getting documents: ", exception)
            }

    }

    }


