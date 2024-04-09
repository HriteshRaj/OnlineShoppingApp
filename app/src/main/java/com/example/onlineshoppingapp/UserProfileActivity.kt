package com.example.onlineshoppingapp

import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.text.TextUtils

import android.widget.Toast

import androidx.activity.result.contract.ActivityResultContracts

import com.example.onlineshoppingapp.databinding.ActivityUserProfileBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.User
import com.example.onlineshoppingapp.utils.Constants
import com.example.onlineshoppingapp.utils.GlideLoader


class UserProfileActivity : BaseActivity() {
    private var imageUri: Uri?=null
    private lateinit var binding:ActivityUserProfileBinding

private lateinit var musedetails: User

private lateinit var userpfoleimageurl:String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        userpfoleimageurl=""

        setContentView(binding.root)

        if (intent.hasExtra(Constants.extrauserdetails)) {
            musedetails = intent.getParcelableExtra<User>(Constants.extrauserdetails)!!

        }


        val imageContract=registerForActivityResult(ActivityResultContracts.GetContent()){

            if(it!=null){
                
            }
            imageUri=it!!
            GlideLoader(this).loadPicture(imageUri!!,binding.profileimage)


        }
        binding.usermail.isEnabled = false
        binding.usermail.setText(musedetails?.email)
        binding.username.isEnabled = false
        binding.username.setText(musedetails?.firstname)


        binding.profileimage.setOnClickListener {
        imageContract.launch("image/*")
        }

        binding.saveuserdetailsbtn.setOnClickListener {
                if(imageUri!=null){
                    showprogressdialog("wait for uploading the image")
                    FirestoreClass().uploadimagetofirebase(this,imageUri)
                }
                    else {

                        uploaduserdetails()
                }
            }

        }



    fun uploaduserdetails(){


        val userphno=binding.phonenumber.text.toString()
        val usergender=binding.Gender.text.toString()
        if(!TextUtils.isEmpty(userphno) && !TextUtils.isEmpty(usergender)&& userpfoleimageurl.isNotEmpty())
        {
            val userhashmap = HashMap<String, Any>()

        userhashmap[Constants.IMAGE] = userpfoleimageurl
        userhashmap[Constants.mobile] = userphno.toLong()
        userhashmap[Constants.gender] = usergender

            userhashmap[Constants.completeprofile]=1

        FirestoreClass().updateUserdetails(this, userhashmap)

        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
    }
    }
    fun uploadimagesuccess(imageUrl:String){


        hideprogressdialog()
        userpfoleimageurl=imageUrl
        Toast.makeText(this,"sucess image",Toast.LENGTH_SHORT).show()
        uploaduserdetails()
        val bundle = Bundle().apply {
            putString(Constants.SENDURLPROFILEFRAGMENT, userpfoleimageurl)
        }

        val profileFragment = ProfileFragement().apply {
            arguments = bundle
        }

    }


}