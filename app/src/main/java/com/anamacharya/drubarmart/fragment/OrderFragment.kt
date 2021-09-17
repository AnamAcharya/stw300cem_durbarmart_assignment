package com.anamacharya.drubarmart.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anamacharya.drubarmart.R
import com.anamacharya.drubarmart.adapter.OrderAdapter
import com.anamacharya.drubarmart.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OrderFragment : Fragment() {

    private lateinit var recycleView_order: RecyclerView


    var t=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_order, container, false)
        recycleView_order = view.findViewById(R.id.recycleView_order)


        loadOrder()
        return view
    }
    private fun loadOrder() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val orderRepository= OrderRepository()
                val response =orderRepository.getOrder()
                val lstOrder = response.data!!

//              for (i in lstOrder){
//                  var sum=0
//                  sum =lstOrder[t].ProductPrice!!.toInt()
//                  var total=0
//                  total+=sum
//                  t++
//                  withContext(Dispatchers.Main) {
//                      Toast.makeText(context, "${total}", Toast.LENGTH_SHORT).show()
//                      price.setText(sum)
//                  }
//
//              }


//                Log.d("values","${lstOrder}")

                withContext(Dispatchers.Main) {

                    recycleView_order.adapter = OrderAdapter(lstOrder, requireContext())
                    recycleView_order.layoutManager =
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