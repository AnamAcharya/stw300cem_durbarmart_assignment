package com.anamacharya.drubarmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.entity.Order
import com.anamacharya.drubarmart.model.CartModel
import com.anamacharya.drubarmart.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var itemImage: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtPrice: TextView
    private lateinit var addtocart: ExtendedFloatingActionButton
    var addorder: CartModel? = null
    var image=""
    var data=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.hide()
        itemImage = findViewById(R.id.itemImage)
        txtName = findViewById(R.id.txtName)
        txtPrice = findViewById(R.id.txtPrice)
        addtocart = findViewById(R.id.addtocart)
        addtocart.setOnClickListener(this)
        addorder=intent.getParcelableExtra("data")
        if (addorder!=null){
            image= ServiceBuilder.loadImagePath()+ addorder!!.ProductImage
            ServiceBuilder.image=addorder?.ProductImage.toString()
            Glide.with(this)
                .load(image)
                .into(itemImage )
            txtName.setText(addorder?.ProductName)
            txtPrice.setText(addorder?.ProductPrice)

        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addtocart->{
                addToCart()
            }
        }
    }

    private fun addToCart() {
        val ProductName=txtName.text.toString()
        val ProductImage=ServiceBuilder.image
        val ProductPrice=txtPrice.text.toString()


        val order= Order(ProductName = ProductName, ProductImage =ProductImage,ProductPrice =ProductPrice)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val orderRepository= OrderRepository()
                val response =orderRepository.addOrder(order)
                if (response.success==true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@OrderActivity, "Congratulation ! Ordered Placed Successfully", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@OrderActivity, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}