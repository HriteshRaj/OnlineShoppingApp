package com.example.onlineshoppingapp.utils


import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.onlineshoppingapp.R


class CustomButton(context: Context, attrs:AttributeSet):AppCompatButton(context,attrs) {
    init{
        applyfont()
        applycolor()
    }

    private fun applyfont() {
        val typeface: Typeface =Typeface.createFromAsset(context.assets,"customfont.ttf")
        setTypeface(typeface)

    }
    private fun applycolor(){
        setTextColor(Color.WHITE)
        setBackgroundResource(R.drawable.custom_btn)

    }


}
