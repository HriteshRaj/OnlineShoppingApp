package com.example.onlineshoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.onlineshoppingapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

        private lateinit var navController:NavController
    private lateinit var btmnabview:BottomNavigationView
    private lateinit var appbarconfig: AppBarConfiguration
        private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addproductsbtnmain.setOnClickListener{
            startActivity(Intent(this,AddProductsActivity::class.java))
        }
        binding.yourcart.setOnClickListener {
            startActivity(Intent(this,CartListActivity::class.java))
        }

        btmnabview=binding.bottomNavigationView
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        as NavHostFragment

       navController= navHostFragment.navController

        btmnabview.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.id != R.id.homeFragment) {

                binding.addproductsbtnmain.visibility = View.GONE
            } else {

                binding.addproductsbtnmain.visibility = View.VISIBLE
            }
        }

    }

}
