package com.example.onlineshoppingapp

import android.app.Dialog
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.ContextCompat
import com.example.onlineshoppingapp.utils.CustomTv
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
        private lateinit var myprogressdialog: Dialog


        fun showerrorsnackbar(message:String,errormessage:Boolean){
            val snackBar=
                Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
            val snackbarview=snackBar.view
            if(errormessage){
        snackbarview.setBackgroundColor(ContextCompat.getColor(this@BaseActivity,R.color.fail_register))
            }else{
            snackbarview.setBackgroundColor(ContextCompat.getColor(this@BaseActivity,R.color.success_register))
            }
            snackBar.show()
        }

    fun showprogressdialog(text:String){

        myprogressdialog=Dialog(this)
        myprogressdialog.setContentView(R.layout.dialog_progress)

        myprogressdialog.setCancelable(false)
        myprogressdialog.setCanceledOnTouchOutside(false)
        myprogressdialog.show()
    }
    fun hideprogressdialog(){
        myprogressdialog.dismiss()
    }



    }
