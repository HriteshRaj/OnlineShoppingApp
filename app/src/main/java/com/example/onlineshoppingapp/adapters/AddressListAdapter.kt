package com.example.onlineshoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.AddressEditTextActivity
import com.example.onlineshoppingapp.PlaceOrderActivity
import com.example.onlineshoppingapp.databinding.EachAddressBinding
import com.example.onlineshoppingapp.utils.Constants

class AddressListAdapter(val context: Context,val list:ArrayList<com.example.onlineshoppingapp.models.Address>): RecyclerView.Adapter<AddressListAdapter.AddressViewHolder>(){


    class AddressViewHolder(binding:EachAddressBinding):RecyclerView.ViewHolder(binding.root){
       var fullnm=binding.fullname
        var pincd=binding.pincode
        var addressText=binding.addresstext


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(EachAddressBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }


    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
            val model=list[position]
                holder.apply {
                    fullnm.text=model.name.toString()
                    pincd.text=model.pincode.toString()
                    addressText.text=model.address.toString()

                }
        holder.itemView.setOnClickListener {
            val intent=Intent(context,PlaceOrderActivity::class.java)
                intent.putExtra(Constants.EXTRA_DETAILS_ADDRESS,model)
            context.startActivity(intent)

        }
    }
}