package com.example.onlineshoppingapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.example.onlineshoppingapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },1500)
    }
}