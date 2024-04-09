package com.example.onlineshoppingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat


import com.example.onlineshoppingapp.models.Products
import com.example.onlineshoppingapp.databinding.ActivityProductsDetailsBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Itemcart
import com.example.onlineshoppingapp.utils.Constants
import com.example.onlineshoppingapp.utils.GlideLoader

class ProductsDetailsActivity : BaseActivity() {
    private lateinit var binding:ActivityProductsDetailsBinding
    private var myproductId:String=""
    private lateinit var mproductdetails: Products
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra(Constants.EXTRA_PRODUCT_ID)){
            myproductId=intent.getStringExtra(Constants.EXTRA_PRODUCT_ID)!!
        }
        var productOwnerId:String=""

        if(intent.hasExtra(Constants.EXTRA_PRODUCT_OWNER_ID)){
            productOwnerId=intent.getStringExtra(Constants.EXTRA_PRODUCT_OWNER_ID)!!
        }
        if(productOwnerId==FirestoreClass().getcurrentUserId()){
            binding.addtocart.visibility= View.GONE

        }else{
            binding.addtocart.visibility=View.VISIBLE
        }



        getproductDetails()
        binding.addtocart.setOnClickListener{
          addtoCart()
            binding.addtocart.visibility=View.GONE
            binding.Gotocart.setBackgroundResource(R.color.yellow)
            binding.Gotocart.setTextColor(ContextCompat.getColor(this,R.color.black))
            binding.Gotocart.visibility=View.VISIBLE



        }
        binding.Gotocart.setOnClickListener {
            startActivity(Intent(this,CartListActivity::class.java))
        }






    }
fun getproductDetails() {
    showprogressdialog("wait")
    FirestoreClass().getProductDetails(this, myproductId)
}
    fun productDetailsSucess(product: Products){
        mproductdetails=product
        hideprogressdialog()
        GlideLoader(this).loadproductPicture(product.productimage!!,binding.productdetailsimage)
        binding.productdetailsname.text=product.productname
        binding.productdetailsdesc.text=product.productdesc
        binding.productdetailsprice.text=product.productprice.toString()
        binding.productdetailsstock.text=product.productstock.toString()
        if(product.productstock.toInt()==0){
            binding.addtocart.visibility=View.GONE
            binding.productdetailsstock.text=resources.getString(R.string.outofstock)


        }


    }


   fun addtoCart(){
        val addToCart= Itemcart(
            FirestoreClass().getcurrentUserId(),mproductdetails.productname,mproductdetails.productimage
        ,mproductdetails.productprice,mproductdetails.productdesc,Constants.DEFAULT_CAR_QUANTITY.toString().toLong(),Constants.DEFAULT_STOCK_QUANTITY.toLong()
        ,myproductId
        )
        showprogressdialog("wait")
        FirestoreClass().addCartItems(this,addToCart)
        addtocartSuccess()




    }



    fun addtocartSuccess(){
        hideprogressdialog()
        Toast.makeText(this@ProductsDetailsActivity,"yay added", Toast.LENGTH_SHORT).show()

    }
}