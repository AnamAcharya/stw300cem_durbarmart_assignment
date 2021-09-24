package com.anamacharya.drubarmart

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class InstrumentedTestingFoodmart {
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
        onView(withId(R.id.Name))
            .perform(typeText("Anam Acharya"))
        onView(withId(R.id.Email))
            .perform(typeText("acharyaanamm@gmail.com"))
        onView(withId(R.id.Phone))
            .perform(typeText("97410367890"))
        onView(withId(R.id.message))
            .perform(typeText("anam here"))
        onView(withId(R.id.btnSubmit))
            .perform(click())
        Thread.sleep(2000)
    }
}
