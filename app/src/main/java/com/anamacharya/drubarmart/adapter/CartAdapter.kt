package com.anamacharya.drubarmart.adapter

import android.app.AlertDialog
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
import com.anamacharya.drubarmart.OrderActivity
import com.anamacharya.drubarmart.R
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.model.CartModel


class CartAdapter(
        val lstcart: MutableList<CartModel>,
        val context: Context
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
var data=""
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var tvname: TextView
        var tvprice: TextView
        var tvAvailability: TextView
        var tvDesc: TextView
        val btnDelete: Button
        val btnOrder:Button
        var imgProfile:ImageView


        init {

            tvname=itemView.findViewById(R.id.tvname)

            tvprice=itemView.findViewById(R.id.tvprice)
            imgProfile=itemView.findViewById(R.id.imgProfile)
            tvAvailability=itemView.findViewById(R.id.tvAvailability)
            tvDesc=itemView.findViewById(R.id.tvDesc)
            btnDelete=itemView.findViewById(R.id.btnDelete)
            btnOrder=itemView.findViewById(R.id.btnorder)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lstcart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = lstcart[position]
        holder.tvname.text = cart.ProductName
        holder.tvprice.text = cart.ProductPrice
        holder.tvAvailability.text = cart.ProductAvailable
        holder.tvDesc.text = cart.ProductDesc
        var image= ServiceBuilder.loadImagePath()+cart.ProductImage
        data=cart.ProductImage.toString()
//        Toast.makeText(context, "${image}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(context, "${data}", Toast.LENGTH_SHORT).show()
        Glide.with(context).load(image).into(holder.imgProfile)
        holder.btnDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Logout User")
                builder.setMessage("Are you sure you want to logout??")
                builder.setIcon(android.R.drawable.ic_delete)
                builder.setPositiveButton("Yes") { _, _ ->
                    lstcart.removeAt(position);
                    notifyDataSetChanged();
                }
                builder.setNegativeButton("No") { _, _ ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
//                lstcart.removeAt(position);
//                notifyDataSetChanged();
            }
        })

        holder.btnOrder.setOnClickListener() {
            val intent = Intent(context, OrderActivity::class.java)
            intent.putExtra("data",cart)
            context.startActivity(intent)
        }








    }
}