


package com.example.onlineshoppingapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.onlineshoppingapp.databinding.ActivityRegisterBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterActivity : BaseActivity() {
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val loginnow=findViewById<TextView>(R.id.redirectlogin)
         //   val toolbar=findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //toolbar.setNavigationOnClickListener{
          //  onBackPressed()
            ///startActivity(Intent(this, LoginActivity::class.java))
            //finish()
       // }

        binding.registerbutton.setOnClickListener{
            val etname=binding.etname.text.toString()
            val etmail=binding.etmail.text.toString()
            val etpass=binding.etpassword.text.toString()
            val etconfirmpass=binding.etconfirmassword.text.toString()
            if(validateregisterdetails(etname,etmail,etpass
                    ,etconfirmpass)){
                showprogressdialog("wait please")
                registeringuser(etname,etmail,etpass,etconfirmpass)

            }else{
                showerrorsnackbar(resources.getString(R.string.invalidcredentials),true)

            }

        }
        loginnow.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
                finish()
        }
    }
private fun validateregisterdetails(ename:String,email:String,epass:String,ecpass:String):Boolean{

    if((TextUtils.isEmpty(ename) || TextUtils.isEmpty(email) || TextUtils.isEmpty(epass) || TextUtils.isEmpty(ecpass))){
            return false

    }
    return epass == ecpass
}
    private fun registeringuser(
        etname: String,
        etmail: String,
        etpass: String,
        etconfirmpass: String
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(etmail,etpass).addOnCompleteListener {
            task->
            if(task.isSuccessful){

                val firebaseuser:FirebaseUser=task.result!!.user!!


                val user= User(
                    firebaseuser.uid,etname,"",etmail
                )
                //creating data in firestore
                FirestoreClass().registerUser(this,user)




                showerrorsnackbar(resources.getString(R.string.registersucess),false)

            }else{
                showerrorsnackbar(resources.getString(R.string.invalidcredentials),true)
                hideprogressdialog()
            }
        }
    }
    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
     fun userRegistrationsuccess(){

        hideprogressdialog()
         FirebaseAuth.getInstance().signOut()
         finish()
         navigateToLoginActivity()

    }
}
