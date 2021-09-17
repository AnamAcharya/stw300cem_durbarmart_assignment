package com.anamacharya.drubarmart

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class DashboardActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var btnhome:Button

    private lateinit var btnorder:Button
    private lateinit var btncart:Button
    private lateinit var btnprofile:Button

    private lateinit var textView:TextView
    private lateinit var topView: View
    private lateinit var view5:View
    private lateinit var sensorManager: SensorManager
    private var sensor:Sensor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()
        btnhome= findViewById(R.id.btnhome)

        textView = findViewById(R.id.textView)
        topView = findViewById(R.id.topView)
        view5 = findViewById(R.id.view5)

        btnhome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }


        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }



  }
    private fun checkSensor():Boolean{
        var flag = true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)==null){
            flag = false
        }
        return flag
    }


    override fun onSensorChanged(event:SensorEvent?){
        val values = event!!.values[0]

        if(values<=4) {
            topView.setBackgroundResource(R.color.teal_200)
            view5.setBackgroundResource(R.color.teal_200)
        }
            else{
            topView.setBackgroundResource(R.color.purple_200)
            view5.setBackgroundResource(R.color.purple_200)
            }
        }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}