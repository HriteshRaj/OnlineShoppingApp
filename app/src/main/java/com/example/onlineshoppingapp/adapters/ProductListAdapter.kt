package com.example.onlineshoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.HomeFragment


import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.models.Products
import com.example.onlineshoppingapp.ProductsDetailsActivity
import com.example.onlineshoppingapp.utils.Constants
import com.example.onlineshoppingapp.utils.CustomButton
import com.example.onlineshoppingapp.utils.CustomTv
import com.example.onlineshoppingapp.utils.GlideLoader

open class ProductListAdapter(private val context:Context, private var list:ArrayList<Products>, private val fragment: HomeFragment):
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

     class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

        var pname=itemview.findViewById<CustomTv>(R.id.productnameeachitem)
        var pimage=itemview.findViewById<ImageView>(R.id.productimageeachitem)
        var prpice=itemview.findViewById<CustomTv>(R.id.productpriceeachitem)

         var pdeletebtn=itemview.findViewById<CustomButton>(R.id.productdelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model=list[position]

        holder.apply {

            pdeletebtn.setOnClickListener {
            fragment.productDelete(model.product_id!!)
            }
            GlideLoader(context).loadproductPicture(model.productimage!!,pimage)
            pname.text=model.productname
            prpice.text=model.productprice.toString()



        }
        holder.itemView.setOnClickListener {
           val intent=Intent(context,ProductsDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_PRODUCT_ID,model.product_id)
            intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID,model.id)

            context.startActivity(intent)



        }


    }

    override fun getItemCount(): Int {
        return list.size
    }



}