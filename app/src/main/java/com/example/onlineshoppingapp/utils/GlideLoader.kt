package com.example.onlineshoppingapp.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.onlineshoppingapp.R
import java.io.IOException

class GlideLoader(val context: Context) {
    fun loadPicture(ImageURI: Any, imageView: ImageView){
        try{
            Glide
                .with(context)
                .load(ImageURI)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }catch (
            e:IOException
        ){
            e.printStackTrace()
        }

    }
    fun loadproductPicture(Image: Any, imageView: ImageView){
        try{
            Glide
                .with(context)
                .load(Image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }catch (
            e:IOException
        ){
            e.printStackTrace()
        }
    }
}