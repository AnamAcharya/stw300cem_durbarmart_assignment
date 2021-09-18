package com.anamacharya.drubarmart

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class RegisterTesting {

    @get:Rule
    val tesRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun checkRegisterUI(){
        Espresso.onView(ViewMatchers.withId(R.id.fullname))
                .perform(ViewActions.typeText("Reshika Shrestha"))
        Espresso.onView(ViewMatchers.withId(R.id.username))
                .perform(ViewActions.typeText("reshika1"))
        Espresso.onView(ViewMatchers.withId(R.id.address))
                .perform(ViewActions.typeText("Lokhanthali"))
        Espresso.onView(ViewMatchers.withId(R.id.contact))
                .perform(ViewActions.typeText("454554"))
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.typeText("shresthareshika@gmail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText("reshika"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.btnRegister))
                .perform(ViewActions.click())
        Thread.sleep(2000)
    }
}