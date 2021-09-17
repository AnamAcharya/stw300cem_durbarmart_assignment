package com.anamacharya.drubarmart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.anamacharya.drubarmart.R
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.model.OrderModel

class OrderAdapter(
        val lstcart: MutableList<OrderModel>,
        val context: Context
): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var tvname: TextView
        var tvprice: TextView

        val btnDelete: Button

        var imgProfile: ImageView

        init {

            tvname=itemView.findViewById(R.id.tvname)

            tvprice=itemView.findViewById(R.id.tvprice)
            imgProfile=itemView.findViewById(R.id.imgProfile)

            btnDelete=itemView.findViewById(R.id.btnDelete)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lstcart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = lstcart[position]
        holder.tvname.text = cart.ProductName
        holder.tvprice.text = cart.ProductPrice
        var image= ServiceBuilder.loadImagePath()+cart.ProductImage
//        Toast.makeText(context, "${image}", Toast.LENGTH_SHORT).show()
        Glide.with(context).load(image).into(holder.imgProfile)
        holder.btnDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                lstcart.removeAt(position);
                notifyDataSetChanged();
            }
        })






    }
}