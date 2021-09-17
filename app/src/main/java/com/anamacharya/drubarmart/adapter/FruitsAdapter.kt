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

class FruitsAdapter(
        val fruitList: MutableList<Product>,
        val context: Context
): RecyclerView.Adapter<FruitsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var fruitImges: ImageView

        var fruitName: TextView

        var value: TextView
        var cart: Button

        init {

            fruitImges=itemView.findViewById(R.id.item_image)

            fruitName=itemView.findViewById(R.id.name)

            value=itemView.findViewById(R.id.value)
            cart=itemView.findViewById(R.id.cart)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.fruit_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.ProductName
        holder.value.text = fruit.ProductPrice
        var image= ServiceBuilder.loadImagePath()
        image+=fruit.ProductImage
        image=image.replace("\\","/")

        Glide.with(context)
                .load(image)
                .into(holder.fruitImges)

        holder. cart.setOnClickListener() {
            val intent = Intent(context, AddToCartActivity::class.java)
            intent.putExtra("data",fruit)
            context.startActivity(intent)
        }

    }
}