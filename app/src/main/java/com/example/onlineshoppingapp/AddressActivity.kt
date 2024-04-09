package com.example.onlineshoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.onlineshoppingapp.adapters.AddressListAdapter
import com.example.onlineshoppingapp.databinding.ActivityAddressBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Address

class AddressActivity : BaseActivity() {
    private lateinit var binding:ActivityAddressBinding
    private lateinit var adrv: RecyclerView
    private var selectedAddress:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAddressList()
        binding.addaddress.setOnClickListener{
        startActivity(Intent(this,AddressEditTextActivity::class.java))

        }



    }

    fun successAddressListfromFirestore(addresslist:ArrayList<Address>){
        hideprogressdialog()

        adrv=binding.addressrv
        adrv.layoutManager=LinearLayoutManager(this)

        adrv.setHasFixedSize(true)
        val adapter=AddressListAdapter(this,addresslist)
        adrv.adapter=adapter


    }
    fun getAddressList(){
        showprogressdialog("wait")
        FirestoreClass().getAddressList(this)
    }
}