package com.anamacharya.drubarmart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.entity.Product
import com.anamacharya.drubarmart.model.CartModel
import com.anamacharya.drubarmart.repository.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToCartActivity : AppCompatActivity(),View.OnClickListener{
    private lateinit var itemImage:ImageView
    private lateinit var txtName:TextView
    private lateinit var txtPrice:TextView
    private lateinit var txtAvailability:TextView
    private lateinit var txtDesc:TextView
    private lateinit var addtocart:ExtendedFloatingActionButton
    var image=""
    var data=""

 var addcart:Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)
        supportActionBar?.hide()
        itemImage = findViewById(R.id.itemImage)
        txtName = findViewById(R.id.txtName)
        txtPrice = findViewById(R.id.txtPrice)
        txtAvailability = findViewById(R.id.txtAvailability)
        txtDesc = findViewById(R.id.txtDesc)
        addtocart = findViewById(R.id.addtocart)

        addtocart.setOnClickListener(this)



            addcart = intent.getParcelableExtra("data")
            if (addcart != null) {
                image = ServiceBuilder.loadImagePath()
                image += addcart?.ProductImage
                ServiceBuilder.image = addcart?.ProductImage.toString()
                Glide.with(this)
                    .load(image)
                    .into(itemImage)
                txtName.setText(addcart?.ProductName)
                txtPrice.setText(addcart?.ProductPrice)
                txtAvailability.setText(addcart?.ProductAvailable)
                txtDesc.setText(addcart?.ProductDesc)
            }

    }

    private fun showHighPriorityNotification() {
        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Product Added")
            .setContentText("Your Product has been sucessfully added")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(1, notification)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addtocart->{
                addToCart()
            }
        }
    }

    private fun addToCart() {
        Toast.makeText(this, "path ${ServiceBuilder.image}", Toast.LENGTH_SHORT).show()

        val ProductName=txtName.text.toString()
        val ProductImage=ServiceBuilder.image
        val ProductPrice=txtPrice.text.toString()
        val ProductAvailable=txtAvailability.text.toString()
        val ProductDesc=txtDesc.text.toString()


        val cart= CartModel(ProductName = ProductName, ProductImage =ProductImage,ProductPrice =ProductPrice,ProductAvailable = ProductAvailable,ProductDesc = ProductDesc)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cartRepository= CartRepository()
                val response =cartRepository.addcart(cart)
                if (response.success==true) {
                    showHighPriorityNotification()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddToCartActivity, "Added", Toast.LENGTH_LONG).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddToCartActivity, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}