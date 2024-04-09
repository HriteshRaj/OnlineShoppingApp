package com.example.onlineshoppingapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


open class BaseFragment : Fragment() {
    private lateinit var mprogressDialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

         fun showprogressDialog(text:String){
            mprogressDialog=Dialog(requireActivity())
            mprogressDialog.setContentView(R.layout.dialog_progress)
            mprogressDialog.setCancelable(false)
            mprogressDialog.setCanceledOnTouchOutside(false)
            mprogressDialog.show()

        }
    fun hideprogressdialog(){
        mprogressDialog.dismiss()
    }
    }
