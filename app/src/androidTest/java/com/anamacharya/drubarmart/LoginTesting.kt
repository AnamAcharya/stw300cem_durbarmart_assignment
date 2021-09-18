package com.anamacharya.drubarmart

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginTesting {
    @get:Rule
    val tesRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun checkLoginUI(){
        Espresso.onView(ViewMatchers.withId(R.id.inputUsername))
            .perform(ViewActions.typeText("anam07"))
        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
            .perform(ViewActions.typeText("anam07"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
            .perform(ViewActions.click())
        Thread.sleep(2000)

    }
    @Test
    fun checkContact(){
        checkLoginUI()
        onView(ViewMatchers.withId(R.id.Name))
                .perform(ViewActions.typeText("Anam Acharya"))
       onView(ViewMatchers.withId(R.id.Email))
                .perform(ViewActions.typeText("acharyaanam@gmail.com"))
        onView(ViewMatchers.withId(R.id.Phone))
                .perform(ViewActions.typeText("97410367890"))
        onView(ViewMatchers.withId(R.id.message))
                .perform(ViewActions.typeText("anam here"))
        onView(ViewMatchers.withId(R.id.btnSubmit))
                .perform(ViewActions.click())
        Thread.sleep(2000)
    }
}