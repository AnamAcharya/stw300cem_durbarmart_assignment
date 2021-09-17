package com.anamacharya.drubarmart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anamacharya.drubarmart.R
import com.anamacharya.drubarmart.entity.Product

class ProductAdapter(val context: Context,
                     val lstProduct: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val category: TextView
        val type: TextView
        val quantity: TextView

        init {
            category=view.findViewById(R.id.category)
            type=view.findViewById(R.id.type)
            quantity=view.findViewById(R.id.quantity)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lstProduct.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = lstProduct[position]

    }


}