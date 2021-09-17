package com.anamacharya.drubarmart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.anamacharya.drubarmart.channel.NotificationChannels
import com.anamacharya.drubarmart.db.FoodMartDB
import com.anamacharya.drubarmart.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class AddProducts : AppCompatActivity(), View.OnClickListener {
    val category= arrayOf("Noodles","Drinks","Frozen")
    val typeNoodle= arrayOf("Ramen","waiwai","rara","quick")
    val typeDrinks= arrayOf("Coke","Fanta","Sprite","wine")
    val typeFrozen= arrayOf("Sausage","momo","pork","salami")

    private lateinit var spinCategory:Spinner
    private lateinit var spintype:Spinner
    private lateinit var spinquant:EditText
    private lateinit var buttonadd:Button
    var text=""
    var typetext=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_products)
        supportActionBar?.hide()
        spinCategory=findViewById(R.id.spincategory)
        spintype=findViewById(R.id.spintype)
        spinquant=findViewById(R.id.etQuantity)
        buttonadd=findViewById(R.id.buttonadd)

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,category)
        spinCategory.adapter=adapter
        buttonadd.setOnClickListener(this)

        text= spinCategory.selectedItem.toString()


        if (text=="Noodles"){
            val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,typeNoodle)
            spintype.adapter=adapter
            typetext=spintype.selectedItem.toString()

        }
//        if (text=="Drinks"){
//            val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,typeDrinks)
//            spintype.adapter=adapter
//        }
//         if (text=="Frozen"){
//            val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,typeFrozen)
//            spintype.adapter=adapter
//        }

    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.buttonadd->{
               saveProduct()
               AddProductsNotification()
            }
       }
    }

    private fun saveProduct() {

        val quantity=spinquant.text.toString()
         val product=Product(text,typetext,quantity)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                FoodMartDB.getInstance(this@AddProducts).getProductDAO().registerProduct(product)
                withContext(Dispatchers.Main){
                    Toast.makeText(this@AddProducts, "ProductAdded", Toast.LENGTH_SHORT).show()
                }

            }

        }catch (ex:Exception){
            Toast.makeText(this, "Error${ex.localizedMessage}", Toast.LENGTH_SHORT).show()
        }

    }
    private fun AddProductsNotification(){
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Add Products")
                .setContentText("Product added succesfully")
                .setColor(Color.BLUE)
                .build()
        notificationManager.notify(1, notification)
    }
}