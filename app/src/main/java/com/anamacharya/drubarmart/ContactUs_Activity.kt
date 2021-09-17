package com.anamacharya.drubarmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.anamacharya.drubarmart.entity.Contact
import com.anamacharya.drubarmart.repository.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactUs_Activity : AppCompatActivity() {
    private lateinit var Name:EditText
    private lateinit var Email:EditText
    private lateinit var Phone:EditText
    private lateinit var message:EditText
    private lateinit var btnSubmit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us_)
        supportActionBar?.hide()
        Name= findViewById(R.id.Name)
        Email= findViewById(R.id.Email)
        Phone= findViewById(R.id.Phone)
        message= findViewById(R.id.message)
        btnSubmit= findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener{
            adduser()
        }

    }
    private fun adduser(){
        val fullname = Name.text.toString()
        val email = Email.text.toString()
        val phone = Phone.text.toString()
        val message = message.text.toString()



        val contact = Contact(fullname = fullname, email = email, phone=phone, message=message)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val contactRepository = ContactRepository()
                val response = contactRepository.contactUser(contact)
                if (response.success == true) {

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ContactUs_Activity, " Contact Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ContactUs_Activity, "Error registering user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}