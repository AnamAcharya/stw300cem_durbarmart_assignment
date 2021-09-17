package com.anamacharya.drubarmart.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anamacharya.drubarmart.R
import com.anamacharya.drubarmart.adapter.CartAdapter
import com.anamacharya.drubarmart.repository.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment : Fragment() {
    private lateinit var recycleView_cart: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cart, container, false)
        recycleView_cart = view.findViewById(R.id.recycleView_cart)
        loadCart()
        return view
    }
    private fun loadCart() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cartRepository= CartRepository()
                val response =cartRepository.getCart()
                val lstCart = response.data!!
                Log.d("values","${lstCart}")

                withContext(Dispatchers.Main) {

                    recycleView_cart.adapter = CartAdapter(lstCart, requireContext())
                    recycleView_cart.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }


}

