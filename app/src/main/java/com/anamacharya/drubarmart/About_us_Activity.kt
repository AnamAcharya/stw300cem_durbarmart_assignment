package com.anamacharya.drubarmart

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class About_us_Activity : AppCompatActivity(), SensorEventListener {
      lateinit var sensorManager: SensorManager
    private lateinit var linear:LinearLayout
  var sensor: Sensor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_about_us_)

        linear = findViewById(R.id.linear)
        //GyroscopeActivity
        sensorManager= this?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
        if (!checkSensor1()) {
            return
        }
        else{
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
        private fun checkSensor1(): Boolean {
        var flag=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)==null){
            flag=false
        }
        return flag
    }
    override fun onSensorChanged(event: SensorEvent?) {
        val values=event!!.values[2]
        if(values < 0){
            linear.setBackgroundResource(R.color.colorAccent)

        }
        else if (values>-0){
            linear.setBackgroundResource(R.color.teal_700)
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}