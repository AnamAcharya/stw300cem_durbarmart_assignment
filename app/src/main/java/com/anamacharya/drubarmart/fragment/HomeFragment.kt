package com.anamacharya.drubarmart.fragment


import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anamacharya.drubarmart.*
import com.anamacharya.drubarmart.adapter.*
import com.anamacharya.drubarmart.api.ServiceBuilder
import com.anamacharya.drubarmart.model.DrinkModel
import com.anamacharya.drubarmart.model.FrozenModel
import com.anamacharya.drubarmart.model.NoodleModel
import com.anamacharya.drubarmart.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment(), SensorEventListener {
    //    private var lstpopular = ArrayList<PopularCategoryModel>()
    private lateinit var recycleView: RecyclerView
    private val noodleList= ArrayList<NoodleModel>()

    private lateinit var recycleView_drink: RecyclerView
    private val drinkList= ArrayList<DrinkModel>()

    private lateinit var recycleView_frozen: RecyclerView
    private lateinit var recycleView_fruit: RecyclerView
    private lateinit var recycleView_veg: RecyclerView
    private val frozenList= ArrayList<FrozenModel>()

    private lateinit var cart: TextView
    private lateinit var tvnoodle: TextView
    private lateinit var tvdrink: TextView
    private lateinit var tvfrozen: TextView
    private lateinit var tvfruit: TextView
    private lateinit var tvveg: TextView
    private lateinit var more: TextView

    lateinit var sensorManager: SensorManager
    var sensor: Sensor?=null
    lateinit var linear: LinearLayout
    lateinit var linear1: LinearLayout
    lateinit var linear2: LinearLayout
    lateinit var linear3:LinearLayout
    lateinit var linear4: LinearLayout
    lateinit var linear5: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycleView = view.findViewById(R.id.recycleView)
        tvnoodle= view.findViewById(R.id.tvnoodle)
        tvdrink= view.findViewById(R.id.tvdrink)
        tvfrozen= view.findViewById(R.id.tvfrozen)
        tvfruit= view.findViewById(R.id.tvfruit)
        tvveg= view.findViewById(R.id.tvveg)
        more= view.findViewById(R.id.more)

        linear= view.findViewById(R.id.linear)
        linear1 = view.findViewById(R.id.linear1)
        linear2 = view.findViewById(R.id.linear2)
        linear3 = view.findViewById(R.id.linear3)
        linear4 = view.findViewById(R.id.linear4)
        linear5 = view.findViewById(R.id.linear5)

        recycleView_drink = view.findViewById(R.id.recycleView_drink)
        recycleView_frozen = view.findViewById(R.id.recycleView_frozen)
        recycleView_fruit = view.findViewById(R.id.recycleView_fruit)
        recycleView_veg = view.findViewById(R.id.recycleView_veg)

        more.setOnClickListener {
            loadPopUpMenu()
        }


       ServiceBuilder.noodle=tvnoodle.text.toString()
      loadNoodles()
        ServiceBuilder.drink=tvdrink.text.toString()
        loadDrink()

        ServiceBuilder.frozen=tvfrozen.text.toString()
        loadFrozen()

        ServiceBuilder.fruit=tvfruit.text.toString()
        loadFruit()
        ServiceBuilder.veg=tvveg.text.toString()
        loadVeg()

        sensorManager= context?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
        if (!checkSensor()) {
            return view
        }
        else{
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }



        return view
    }
    private fun checkSensor(): Boolean {
        var flag=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)==null){
            flag=false
        }
        return flag
    }





    override fun onSensorChanged(event: SensorEvent?) {
        val values=event!!.values[0]


        if (values>20){
            linear.setBackgroundResource(R.color.colorAccent)
            linear1.setBackgroundResource(R.color.colorAccent)
            linear2.setBackgroundResource(R.color.colorAccent)
            linear3.setBackgroundResource(R.color.colorAccent)
            linear4.setBackgroundResource(R.color.colorAccent)
            linear5.setBackgroundResource(R.color.colorAccent)
        }
        else if(values<20){
            linear.setBackgroundResource(R.color.white)
            linear1.setBackgroundResource(R.color.white)
            linear2.setBackgroundResource(R.color.white)
            linear3.setBackgroundResource(R.color.white)
            linear4.setBackgroundResource(R.color.white)
            linear5.setBackgroundResource(R.color.white)
        }


    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun loadPopUpMenu() {
        // Load pop up menu
        val popupMenu = context?.let { PopupMenu(it, more) }
        if (popupMenu != null) {
            popupMenu.menuInflater.inflate(R.menu.bottom_navigation, popupMenu.menu)
        }
        if (popupMenu != null) {
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.AbouttUs -> {
                        startActivity(Intent(context, About_us_Activity::class.java))
                        true
                    }
                    R.id.contactUs -> {
                        startActivity(Intent(context, ContactUs_Activity::class.java))
                        true
                    }
                    R.id.locationLokanthali -> {
                        startActivity(Intent(context, DillibazarMapsActivity::class.java))
                        true
                    }
                    R.id.locationPutalisadak -> {
                        startActivity(Intent(context, KalankiMapsActivity::class.java))
                        true
                    }


                    R.id.rating -> {
                        startActivity(Intent(context, RateusActivity::class.java))
                        true
                    }
                    R.id.logout ->{
//                        val builder = context?.let { AlertDialog.Builder(it) }
//                        builder?.setTitle("My Alert")
//                        builder?.setMessage("Are you sure you want to logout?")
//                        builder?.setIcon(android.R.drawable.ic_dialog_alert)
//
//                        //performing positive action
//                        builder?.setPositiveButton("Yes"){
//                            dislogInterface, which ->
//                            startActivity(Intent(context, LoginActivity::class.java))
//                        }
//
//                        builder?.setNegativeButton("No"){
//                            dislogInterface, which ->
//                            Toast.makeText(context,"clicked No", Toast.LENGTH_SHORT).show()
//                        }
//
//                        val alertDialog:AlertDialog = builder?.create()
//                        alertDialog.setCancelable(false)
//                        alertDialog.show()

                        startActivity(
                            Intent(
                                context,
                                LoginActivity::class.java
                            )
                        )
                    }
                }
                true
            }
        }
        if (popupMenu != null) {
            popupMenu.show()
        }
    }
//    fun logout() {
//        val preferences = getSharedPreferences("UsernamePasswordPref", Context.MODE_PRIVATE)
//        val editor = preferences.edit()
//        editor.clear()
//        editor.apply()
//        startActivity(
//            Intent(
//                this,
//                LoginActivity::class.java
//            )
//        )
//    }
    private fun loadNoodles() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
               val productRepository=ProductRepository()
                val response =productRepository.getNoodle()
                val lstProduct = response.alldata!!
                Log.d("values","${lstProduct}")
                val lstNoodle = lstProduct[0]
                withContext(Dispatchers.Main) {
//                    Toast.makeText(requireContext(), "$lstNoodle", Toast.LENGTH_LONG).show()
                    recycleView.adapter = NoodleAdapter(lstProduct, requireContext())
                    recycleView.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }


    private fun loadDrink() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                    val response = productRepository.getDrink()
                    val lstProduct = response.alldata!!
                    withContext(Dispatchers.Main) {

                        recycleView_drink.adapter = lstProduct?.let { DrinkAdapter(it, requireContext()) }
                        recycleView_drink.layoutManager =
                                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }

    private fun loadFrozen() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.getFrozen()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recycleView_frozen.adapter = lstProduct?.let { FrozenAdapter(it, requireContext()) }
                    recycleView_frozen.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }
    private fun loadFruit() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.getfruit()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recycleView_fruit.adapter = lstProduct?.let { FruitsAdapter(it, requireContext()) }
                    recycleView_fruit.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }
    private fun loadVeg() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.getVeg()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recycleView_veg.adapter = lstProduct?.let { VegAdapter(it, requireContext()) }
                    recycleView_veg.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }




}


