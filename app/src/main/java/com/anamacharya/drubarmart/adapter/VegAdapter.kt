package com.anamacharya.drubarmart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.anamacharya.drubarmart.AddToCartActivity
import com.anamacharya.drubarmart.R
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.entity.Product

class VegAdapter (
        val vegList: MutableList<Product>,
        val context: Context
): RecyclerView.Adapter<VegAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var vegImges: ImageView

        var vegName: TextView

        var value: TextView
        var cart: Button

        init {

            vegImges=itemView.findViewById(R.id.item_image)

            vegName=itemView.findViewById(R.id.name)

            value=itemView.findViewById(R.id.value)
            cart=itemView.findViewById(R.id.cart)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.veg_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return vegList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val veg = vegList[position]
        holder.vegName.text = veg.ProductName
        holder.value.text = veg.ProductPrice
        var image= ServiceBuilder.loadImagePath()
        image+=veg.ProductImage
        image=image.replace("\\","/")

        Glide.with(context)
                .load(image)
                .into(holder.vegImges)

        holder. cart.setOnClickListener() {
            val intent = Intent(context, AddToCartActivity::class.java)
            intent.putExtra("data",veg)
            context.startActivity(intent)
        }

    }
}