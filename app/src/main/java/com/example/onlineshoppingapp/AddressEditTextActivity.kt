package com.example.onlineshoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.databinding.ActivityAddressEditTextBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Address

class AddressEditTextActivity : BaseActivity() {
    private lateinit var binding:ActivityAddressEditTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding=ActivityAddressEditTextBinding.inflate(layoutInflater)

        setContentView(binding.root)

            binding.submitaddress.setOnClickListener {
              uploadAddress()

            }


    }

    private fun uploadAddress() {
        var fullname=binding.AddressFullName.text
        var phoneno=binding.AddressPhoneNumber.text
        var address=binding.Address.text
        var pincode=binding.pincode.text
        if(!TextUtils.isEmpty(fullname.toString()) && !TextUtils.isEmpty(phoneno.toString()) && !TextUtils.isEmpty(address.toString())
            &&  !TextUtils.isEmpty(pincode.toString())){
            val addressEntered=Address(FirestoreClass().getcurrentUserId(),fullname.toString(),phoneno.toString(), address.toString(),pincode.toString())
            showprogressdialog("wait")
            FirestoreClass().addAddress(this,addressEntered)
        }else{
            Toast.makeText(this@AddressEditTextActivity,"Please Enter All Details",Toast.LENGTH_SHORT).show()
        }

            }

    fun addressSuccess() {
       hideprogressdialog()
        Toast.makeText(this@AddressEditTextActivity,"Address Added Success",Toast.LENGTH_SHORT).show()
        showprogressdialog("wait")
        startActivity(Intent(this,AddressActivity::class.java))



    }



}