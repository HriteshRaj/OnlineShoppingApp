package com.example.onlineshoppingapp

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlineshoppingapp.databinding.ActivityPlaceOrderBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass

import com.example.onlineshoppingapp.models.Address
import com.example.onlineshoppingapp.models.Itemcart
import com.example.onlineshoppingapp.models.Products

class PlaceOrderActivity : BaseActivity() {
    private lateinit var binding:ActivityPlaceOrderBinding
    private lateinit var productlist:ArrayList<Products>
    private lateinit var cartlist:ArrayList<Itemcart>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityPlaceOrderBinding.inflate(layoutInflater
        )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val selectedAddress=intent.getParcelableExtra<Address>(com.example.onlineshoppingapp.utils.Constants.EXTRA_DETAILS_ADDRESS)
        binding.addressname.text=selectedAddress!!.name.toString()
        binding.AddressPhno.text= selectedAddress.phonenumber.toString()
        binding.addressinplaceorder.text=selectedAddress.address.toString()


    }
    fun succesproductListfromFirestore(productList:ArrayList<Products>){
            productlist=productList
        getProductList()

    }


    fun getProductList(){
        FirestoreClass().getallCartList(this@PlaceOrderActivity)
    }
    fun sucessCartItemList(cartList:ArrayList<Itemcart>){
        cartlist=cartList

    }
}