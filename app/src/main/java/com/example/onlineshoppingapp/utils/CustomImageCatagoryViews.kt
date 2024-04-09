package com.example.onlineshoppingapp.utils

import android.content.Context

import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.onlineshoppingapp.R


class CustomImageCatagoryViews(context: Context, attrs: AttributeSet):AppCompatImageView(context,attrs) {
    init {

        val margin = resources.getDimensionPixelSize(R.dimen.marginfromlayout) // Replace R.dimen.margin_size with your actual dimension resource
        val params = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(margin, margin, margin, margin)
        layoutParams = params

    }



}
