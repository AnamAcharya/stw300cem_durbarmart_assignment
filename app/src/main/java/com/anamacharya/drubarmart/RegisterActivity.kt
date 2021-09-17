package com.anamacharya.drubarmart

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.anamacharya.drubarmart.channel.NotificationChannels
import com.anamacharya.drubarmart.entity.Consumer
import com.anamacharya.drubarmart.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var fullname: EditText
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var contact: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        fullname = findViewById(R.id.fullname)
        email = findViewById(R.id.email)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        address = findViewById(R.id.address)
        contact = findViewById(R.id.contact)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {

            adduser()
            showHighPriorityNotification()

        }
    }

    private fun adduser() {
        val fullname = fullname.text.toString()
        val email = email.text.toString()
        val username = username.text.toString()
        val password = password.text.toString()
        val address = address.text.toString()
        val contact = contact.text.toString()
        val finalValue: String = contact


        val consumer = Consumer(
            fullname = fullname,
            username = username,
            address = address,
            contact = finalValue,
            email = email,
            password = password
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val custRepository = CustomerRepository()
                val response = custRepository.registerUser(consumer)
                if (response.success == true) {

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Congratulation! Account Created Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@RegisterActivity, "Error registering user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showHighPriorityNotification() {
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Registration")
            .setContentText("Thank You For Choosing DurbarMart")
            .setColor(Color.BLUE)
            .build()
        notificationManager.notify(1, notification)
    }
}
