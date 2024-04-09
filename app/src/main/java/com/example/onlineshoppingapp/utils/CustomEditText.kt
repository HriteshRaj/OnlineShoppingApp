package com.example.onlineshoppingapp.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.onlineshoppingapp.R

class CustomEditText(context: Context, attrs:AttributeSet) :AppCompatEditText(context,attrs){

    init {
        applyfont()
        applyborders()


    }

    private fun applyfont() {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"customfont.ttf")
        setTypeface(typeface)

    }
    private fun applyborders() {

        setBackgroundResource(R.drawable.border_cardview)

        setPadding(20,20,20,20)



    }



}