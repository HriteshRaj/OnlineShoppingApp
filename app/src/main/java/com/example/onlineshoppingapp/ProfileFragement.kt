package com.example.onlineshoppingapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onlineshoppingapp.databinding.FragmentProfileFragementBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragement : BaseFragment() {
     private lateinit var binding:FragmentProfileFragementBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileFragementBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment









        binding.homecard.setOnClickListener {
          findNavController().navigate(R.id.action_profileFragment_to_homeFragment)

        }
        binding.ordercard.setOnClickListener {
            startActivity(Intent(requireContext(),CartListActivity::class.java))

        }
        binding.logoutcard.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
           val intent=Intent(requireContext(),LoginActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)




        }


        return binding.root

    }




}