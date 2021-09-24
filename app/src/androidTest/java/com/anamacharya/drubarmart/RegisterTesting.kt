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
                .perform(ViewActions.typeText("Anam new "))
        Espresso.onView(ViewMatchers.withId(R.id.username))
                .perform(ViewActions.typeText("anam05"))
        Espresso.onView(ViewMatchers.withId(R.id.address))
                .perform(ViewActions.typeText("Kathmandu"))
        Espresso.onView(ViewMatchers.withId(R.id.contact))
                .perform(ViewActions.typeText("97410367891"))
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.typeText("anam07@gmail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText("anam05"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.btnRegister))
                .perform(ViewActions.click())
        Thread.sleep(2000)
    }
}