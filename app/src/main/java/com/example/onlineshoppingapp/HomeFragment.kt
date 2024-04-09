package com.example.onlineshoppingapp

import android.os.Bundle
import android.util.Log


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.adapters.ProductListAdapter
import com.example.onlineshoppingapp.databinding.FragmentHomeBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Products


class HomeFragment : BaseFragment() {
    private lateinit var binding:FragmentHomeBinding

        private lateinit var homercv:RecyclerView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)

    homercv=binding.homerv
        // Inflate the layout for this fragment

        return (binding.root)




    }

    override fun onResume() {
        super.onResume()
        getProductListFromFirestore()
    }
    fun sucessProductListFromFirestore(ProductList: ArrayList<Products>) {
        hideprogressdialog()
        if(ProductList.size==0){
            binding.homerv.visibility=View.GONE
            binding.arrowadd.visibility=View.VISIBLE
            binding.addproductshere.visibility=View.VISIBLE
        }else{


        for(i in ProductList){
            Log.e("product name", i.productname.toString())
        }
      homercv.layoutManager=GridLayoutManager(requireActivity(),1)
        homercv.setHasFixedSize(true)

        val adapterProducts=ProductListAdapter(requireActivity(),ProductList,this)
        homercv.adapter=adapterProducts


    } }

    fun getProductListFromFirestore() {
        showprogressDialog("please wait")
        FirestoreClass().getProductList(this)


    }
   fun productDelete(productId:String){
       FirestoreClass().deleteProduct(this,productId)
        Toast.makeText(requireActivity(),"delete the product $productId", Toast.LENGTH_SHORT).show()

    }
    fun deleteproductSucess(){
        hideprogressdialog()

        Toast.makeText(requireContext(),"product deleted",Toast.LENGTH_SHORT).show()
        getProductListFromFirestore()

    }

}

