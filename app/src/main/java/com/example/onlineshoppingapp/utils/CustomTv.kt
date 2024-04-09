package com.example.onlineshoppingapp.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import com.example.onlineshoppingapp.R

class CustomTv(context:Context,attrs:AttributeSet):AppCompatTextView (context,attrs){
    init {
        applyfont()

    }



    private fun applyfont() {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"customfont.ttf")
      setTypeface(typeface)

    }


}