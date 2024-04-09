package com.example.onlineshoppingapp

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshoppingapp.adapters.DashBoardItemListAdapter
import com.example.onlineshoppingapp.databinding.FragmentDashBoardBinding
import com.example.onlineshoppingapp.firestore.FirestoreClass
import com.example.onlineshoppingapp.models.Products
import com.example.onlineshoppingapp.utils.Constants


class DashBoardFragment : BaseFragment() {
    private lateinit var binding:FragmentDashBoardBinding
    private lateinit var dbrv:RecyclerView
    private lateinit var dbadapter:DashBoardItemListAdapter
    private lateinit var productlist:ArrayList<Products>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDashBoardBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        dbrv=binding.dashboardrv
        binding.tshirtcatagory.setOnClickListener {
            FirestoreClass().getProductListbyCatagory(this,Constants.tshirt)

        }
        binding.hoodiecatagory.setOnClickListener {
            FirestoreClass().getProductListbyCatagory(this,Constants.HOODIE)

        }
        binding.postercatagory.setOnClickListener {
            FirestoreClass().getProductListbyCatagory(this,Constants.poster)

        }
        binding.keychaincatagory.setOnClickListener {
            FirestoreClass().getProductListbyCatagory(this,Constants.keychain)

        }
        binding.figurinecatagory.setOnClickListener {
            FirestoreClass().getProductListbyCatagory(this,Constants.figurine)

        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
       getProductItemList()
    }
    private fun getProductItemList(){
        showprogressDialog("wait")
        FirestoreClass().getProductItemList(this)
    }

    fun sucessDashBoardItemList(dashboarditemList:ArrayList<Products>){
        hideprogressdialog()
       dbrv.layoutManager=GridLayoutManager(requireContext(),1)
        dbrv.setHasFixedSize(true)
         dbadapter = DashBoardItemListAdapter(requireContext(),dashboarditemList)
        dbrv.adapter=dbadapter

        dbadapter.setOnItemClickListener(object:DashBoardItemListAdapter.OnItemClickListener{
            override fun OnItemClick(position: Int, products: Products) {
                val intent= Intent(context,ProductsDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_PRODUCT_ID,products.product_id)
                intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID,products.id)
                startActivity(intent)
            }
        })


    }

    fun FetchProductsByCatagory(productList:ArrayList<Products>){

        productlist=productList
        dbadapter= DashBoardItemListAdapter(requireContext(),productlist)
        dbrv.adapter=dbadapter

    }


}