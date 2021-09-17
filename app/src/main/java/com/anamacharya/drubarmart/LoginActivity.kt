package com.anamacharya.drubarmart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.snackbar.Snackbar

import com.anamacharya.drubarmart.api.ServiceBuilder

import com.anamacharya.drubarmart.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var RegisterHere: TextView
    private lateinit var linearLayout: LinearLayout
    private lateinit var admin: RadioButton
    private lateinit var user: RadioButton

    var username = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        inputUsername = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        RegisterHere = findViewById(R.id.RegisterHere)



        btnLogin.setOnClickListener {
            login()
            showHighPriorityNotification()
            vibratePhone()
        }
        RegisterHere.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


    }

    private fun showHighPriorityNotification() {
        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Login Notification")
            .setContentText("You have sucessfully logged in")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(1, notification)

    }

    @SuppressLint("ServiceCast")
    private fun vibratePhone() {
        val vibrator = this?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(2000)
        }
    }


    private fun login() {
        username = inputUsername.text.toString()
        password = inputPassword.text.toString()

        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
        Toast.makeText(
            this@LoginActivity,
            "Username and password saved",
            Toast.LENGTH_SHORT
        ).show()
        try {
            CoroutineScope(Dispatchers.IO).launch {


                val repository = CustomerRepository()
                val response = repository.checkUser(username, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer ${response.token}"
                    ServiceBuilder.username = "${response.data}"
                    ServiceBuilder.data = response.userdata!!

//                    saveUsernamePassword()
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            HomeActivity::class.java
                        )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }
            }
        } catch (ex: IOException) {

            Toast.makeText(
                this@LoginActivity,
                ex.toString(),
                Toast.LENGTH_SHORT
            ).show()

        }


    }

}

