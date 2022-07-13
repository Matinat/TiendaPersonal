package com.example.tiendapersonal.ui

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.activityScenarioRule

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.tiendapersonal.R
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkActivityVisibility(){
       onView(withId(R.id.mainContainer))
           .check(matches(isDisplayed()))
    }

    @Test
    fun checkProductListFragmentVisibility(){
        onView(withId(R.id.product_list_fragment))
            .check(matches(isDisplayed()))
    }
}