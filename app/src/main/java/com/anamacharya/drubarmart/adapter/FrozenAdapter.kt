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


class FrozenAdapter(
        val frozenList: MutableList<Product>,
        val context: Context
): RecyclerView.Adapter<FrozenAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var frozenImges: ImageView

        var frozenName: TextView

        var value: TextView
        var cart: Button

        init {

            frozenImges=itemView.findViewById(R.id.item_image)

            frozenName=itemView.findViewById(R.id.name)

            value=itemView.findViewById(R.id.value)
            cart=itemView.findViewById(R.id.cart)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.frozen_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return frozenList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val frozen = frozenList[position]
        holder.frozenName.text = frozen.ProductName
        holder.value.text = frozen.ProductPrice
        var image= ServiceBuilder.loadImagePath()
        image+=frozen.ProductImage
        image=image.replace("\\","/")

        Glide.with(context)
                .load(image)
                .into(holder.frozenImges)

        holder. cart.setOnClickListener() {
            val intent = Intent(context, AddToCartActivity::class.java)
            intent.putExtra("data",frozen)
            context.startActivity(intent)
        }

    }
}