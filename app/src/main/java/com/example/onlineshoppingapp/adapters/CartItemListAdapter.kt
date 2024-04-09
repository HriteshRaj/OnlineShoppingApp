package com.example.onlineshoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.CartListActivity
import com.example.onlineshoppingapp.R

import com.example.onlineshoppingapp.models.Itemcart
import com.example.onlineshoppingapp.databinding.CartItemBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.utils.Constants
import com.example.onlineshoppingapp.utils.GlideLoader

class CartItemListAdapter (private val context: Context, private val cartList:ArrayList<Itemcart>)
    : RecyclerView.Adapter<CartItemListAdapter.CartViewHolder>()
{

    class CartViewHolder(val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root) {
        val cimage=binding.cartitemimage
        var cprice=binding.cartitemprice
        val cname=binding.cartitemname
        val cartplus=binding.plus
        val cartminus=binding.minus
        var quanity=binding.cartquantity
        val cdel=binding.cartitemdelete


    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
            return CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

    override fun getItemCount(): Int {
      return cartList.size
    }

    override fun onBindViewHolder(
        holder:CartViewHolder,
        position: Int
    ) {
        val model=cartList[position]
        holder.apply {
            GlideLoader(context).loadproductPicture(model.carttimage!!,cimage)
            cprice.text=model.cartprice.toString()
            cname.text=model.cartitemname
            quanity.text=model.cartquantity.toString()
            cdel.setOnClickListener {
               when(context){
                   is CartListActivity->{
                       context.showprogressdialog("wait")

                   }
               }
                FirestoreClass().deleteproductcart(context,model.id!!)
            }

                if(model.cartquantity.toInt()==0){
                holder.cartplus.visibility=View.GONE
                    holder.cartminus.visibility=View.GONE
                    holder.quanity.text= context.getString(R.string.outofstock)

                }else{
                    holder.cartminus.visibility=View.VISIBLE
                    holder.cartplus.visibility=View.VISIBLE
                }



                    holder.cartplus.setOnClickListener {
                        if(model.cartquantity.toInt()>=model.cproductstock.toInt()){
                            if(context is CartListActivity){
                                context.showerrorsnackbar(context.resources.getString(R.string.morethanstock),true)
                            }

                        }else{
                            var cartitemquantity:Int=model.cartquantity.toInt()
                             val itemhashMap=HashMap<String,Any>()
                            itemhashMap[Constants.CART_QUANTITYFORUPDATE]=(cartitemquantity+1)
                            if(context is CartListActivity){
                                context.showprogressdialog("wait")
                            }
                            FirestoreClass().updatecart(context,model.id!!,itemhashMap)
                        }
                    }
            holder.cartminus.setOnClickListener {
                if(model.cartquantity.toInt()==1){
                    FirestoreClass().deleteproductcart(context,model.id!!)
                }else{
                    var cartitemquantity:Int=model.cartquantity.toInt()

                    val itemHashMap=HashMap<String,Any>()
                    itemHashMap[Constants.CART_QUANTITYFORUPDATE]= (cartitemquantity-1)
                    if(context is CartListActivity){
                        context.showprogressdialog("wait")
                    }
                    FirestoreClass().updatecart(context,model.id!!,itemHashMap)
                }
            }




    }
        }
    }






