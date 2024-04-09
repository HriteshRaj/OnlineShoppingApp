package com.example.onlineshoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.databinding.EachDashboardProductBinding
import com.example.onlineshoppingapp.models.Products
import com.example.onlineshoppingapp.utils.GlideLoader

open class DashBoardItemListAdapter(private val context: Context, private var list:ArrayList<Products>,
):
    RecyclerView.Adapter<DashBoardItemListAdapter.ViewHolder>() {

    private lateinit var mylistener:OnItemClickListener
    interface OnItemClickListener{
        fun OnItemClick(position:Int,products: Products)
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.mylistener=listener
    }

    class ViewHolder(val binding:EachDashboardProductBinding):RecyclerView.ViewHolder(binding.root) {

    val dbitemname=binding.dashboardItemname
        val dbitemprice=binding.dashboardItemPrice
        val dbitemimage=binding.dashboardItemImage


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EachDashboardProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val model=list[position]
        holder.apply {
            GlideLoader(context).loadproductPicture(model.productimage!!,dbitemimage)
            dbitemname.text=model.productname
            dbitemprice.text= model.productprice.toString()

        }
        holder.itemView.setOnClickListener{
            if(mylistener!=null){
                mylistener!!.OnItemClick(position,model)

            }
        }


    }

}