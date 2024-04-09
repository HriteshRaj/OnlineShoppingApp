package com.example.onlineshoppingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import com.example.onlineshoppingapp.databinding.ActivityAddProductsBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Products
import com.example.onlineshoppingapp.utils.Constants
import com.example.onlineshoppingapp.utils.GlideLoader


class AddProductsActivity : BaseActivity() {


    private lateinit var selectedoption:String
    private var productimageuri: Uri?=null
    private lateinit var productimageUrl:String
    private lateinit var binding:ActivityAddProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddProductsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val spinner=binding.productcatagoryspinner
        val options=arrayOf(Constants.NONE,Constants.tshirt,Constants.HOODIE,Constants.keychain,Constants.poster,Constants.figurine)
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,options)

        spinner.adapter=arrayAdapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               selectedoption=options.get(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
               Toast.makeText(this@AddProductsActivity,"please select ",Toast.LENGTH_SHORT).show()
            }

        }

        productimageUrl=""

        val imageContract=registerForActivityResult(ActivityResultContracts.GetContent()){it->
            if(it!=null){
                productimageuri=it!!
                GlideLoader(this).loadPicture(productimageuri!!,binding.addproductimage)
            }else{
                Toast.makeText(this@AddProductsActivity,"Please choose product image to proceed",Toast.LENGTH_SHORT).show()
            }

        }

        binding.addproductimage.setOnClickListener{
            imageContract.launch("image/*")
        }

        binding.senddata.setOnClickListener {
            if(productimageuri!=null){
                showprogressdialog("please wait image is uploading")
                FirestoreClass().uploadimagetofirebase(this,productimageuri)
                uploadproductdata()
            }else{
                uploadproductdata()
            }
        }






    }

    private fun uploadproductdata() {
        if(productimageuri!=null){
            var pname=binding.productname.text.toString()
            var prprice=binding.productprice.text.toString().toLong()
            var pdesc=binding.productdesc.text.toString()
            var pstock=binding.productstock.text.toString().toLong()

            if(!TextUtils.isEmpty(pname) && !TextUtils.isEmpty(prprice.toString()) && productimageUrl.isNotEmpty()&& !TextUtils.isEmpty(pdesc)&& !TextUtils.isEmpty(pstock.toString())){
                val product= Products(FirestoreClass().getcurrentUserId(),pname,productimageUrl,prprice.toString().toLong(),
                    pdesc,selectedoption,pstock.toString().toLong()
                )

                FirestoreClass().getproductinfo(this,product)
                Toast.makeText(this, "product added", Toast.LENGTH_SHORT).show()


            }
        }else{
            Toast.makeText(this@AddProductsActivity,"please enter all details",Toast.LENGTH_SHORT).show()
        }


    }
    fun uploadimagesuccess(imageUrl:String){
        hideprogressdialog()
        productimageUrl=imageUrl
        Toast.makeText(this,"sucess image",Toast.LENGTH_SHORT).show()
        uploadproductdata()
    }
    fun productUploadSuccess(){
        hideprogressdialog()
        Toast.makeText(this,"product uploaded success",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}