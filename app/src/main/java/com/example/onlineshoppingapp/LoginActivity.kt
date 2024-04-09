package com.example.onlineshoppingapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import com.example.onlineshoppingapp.databinding.ActivityLoginBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.User
import com.example.onlineshoppingapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val redirectoreg=findViewById<TextView>(R.id.redirectregister)
        redirectoreg.setOnClickListener{
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
            finish()

        }
        binding.loginbutton.setOnClickListener{
            val etmail=binding.etusername.text.toString()
            val password=binding.etpassword.text.toString()

            if(validatelogindetails(etmail,password)){
                showprogressdialog("Please wait")
                loginuser(etmail,password)
            }else{
                showerrorsnackbar("Something went wrong",true)
            }


        }



    }

    private fun loginuser(etmail: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(etmail,password).addOnSuccessListener {
            showerrorsnackbar("You are logged in sucessfully",false)
            FirestoreClass().getUserDetails(this)

        }.addOnFailureListener{
            showerrorsnackbar("Login faled",true)

            hideprogressdialog()
        }


    }

    private fun validatelogindetails(etmail:String,password:String):Boolean {
        if(TextUtils.isEmpty(etmail)|| TextUtils.isEmpty(password)){
            return false
        }
       return true
    }
    fun userloggedinSucess(user: User){
        hideprogressdialog()

        if(user.profileCompleted==0){
            val intent=Intent(this@LoginActivity,UserProfileActivity::class.java)
            intent.putExtra(Constants.extrauserdetails,user)
            startActivity(intent)


            finish()
        }else{
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }


        finish()
    }

}