package com.anamacharya.drubarmart

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.anamacharya.drubarmart.adapter.*
import com.anamacharya.drubarmart.fragment.CartFragment
import com.anamacharya.drubarmart.fragment.HomeFragment
import com.anamacharya.drubarmart.fragment.OrderFragment
import com.anamacharya.drubarmart.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager;
    private lateinit var tabLayout: TabLayout;
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        loadViewPagerAdapter();
    }
    private fun loadViewPagerAdapter() {

        val adapter = ViewPageAdapter(supportFragmentManager);
        adapter.addFragment(HomeFragment(), "Home");
        adapter.addFragment(CartFragment(), "Cart");
        adapter.addFragment(OrderFragment(), "Order");
        adapter.addFragment(ProfileFragment(),"Profile");

        viewPager.adapter = adapter;
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24);
//        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24);
//        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_notifications_24);


    }


}