package com.example.onlineshoppingapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.adapters.CartItemListAdapter
import com.example.onlineshoppingapp.databinding.ActivityCartListBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Itemcart
import com.example.onlineshoppingapp.models.Products

class CartListActivity : BaseActivity() {
    private lateinit var binding:ActivityCartListBinding
    private lateinit var cartrv:RecyclerView
    private lateinit var mproductList:ArrayList<Products>
    private lateinit var mcartitemList:ArrayList<Itemcart>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartrv=binding.carlistRv
        binding.checkoutbtn.setOnClickListener {
            startActivity(Intent(this,AddressActivity::class.java))

        }

    }
    override fun onResume() {
        super.onResume()
        //getCartItemList()
        getproductList()

    }

    fun sucessCartItemList(cartList:ArrayList<Itemcart>){
    hideprogressdialog()


        for(product in mproductList){
            for(cartitem in cartList){
                if(cartitem.product_id == product.product_id){
                    cartitem.cproductstock=product.productstock
                    if(product.productstock.toInt()==0){
                        cartitem.cartquantity=product.productstock

                    }
                }
            }
        }

        mcartitemList=cartList

        if(mcartitemList.size>0){


        cartrv.layoutManager=LinearLayoutManager(this)
        cartrv.setHasFixedSize(true)
        val cartadapter= CartItemListAdapter(this@CartListActivity,cartList)

        cartrv.adapter=cartadapter
        var subtotal:Double=0.0
        for(item in mcartitemList){
            val availablestock=item.cproductstock.toInt()
            if(availablestock>0){
                val price=String.format("%.2f",item.cartprice.toDouble()).toDouble()
                val quantity=item.cartquantity.toInt()
                subtotal+= (price*quantity)
            }

        }

        binding.cartsubtotal.text= "$ $subtotal"
        binding.cartGst.text="$5"

        if(subtotal>0){
            binding.checkoutll.visibility= View.VISIBLE
            val total=subtotal+5
            binding.carttotal.text= "$ $total"
        }else{
            binding.checkoutll.visibility= View.GONE
        }
    }else{
            binding.checkoutll.visibility=View.GONE
            binding.carlistRv.visibility=View.GONE
            binding.emptycarttv.visibility=View.VISIBLE
        }



}
    fun getCartItemList(){
       // showprogressdialog("wait")
        FirestoreClass().getCarList(this)

    }

  fun deleteproductSuccess(){
      hideprogressdialog()
      Toast.makeText(this,"cart item deleted",Toast.LENGTH_SHORT).show()
      getCartItemList()

   }
    fun succesproductListfromFirestore(productList:ArrayList<Products>){
        hideprogressdialog()
        mproductList=productList

        getCartItemList()
    }
    fun getproductList(){
        showprogressdialog("wait")
        FirestoreClass().getallCartList(this)
    }

    fun cartupdatesucess(){
        hideprogressdialog()
        getCartItemList()

    }



}