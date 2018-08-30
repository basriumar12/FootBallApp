package com.kotlinje.submit2.activity


import android.support.test.espresso.ViewInteraction
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import com.kotlinje.submit2.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.actionWithAssertions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import com.kotlinje.submit2.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(5000)
        onView(withId(layoutLiga))
                .check(matches(isDisplayed()))
        onView(withId(layoutLiga))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))

    }

    @Test
    fun testKlikRecyclerview() {
        Thread.sleep(5000)
        onView(withId(layoutLiga))
                .check(matches(isDisplayed()))
        onView(withId(layoutLiga)).perform(click())
//        onView(withText("Crystal Palace")).perform(click())

    }


    @Test
    fun testAddFavorite() {

        Thread.sleep(5000)
        onView(withId(layoutLiga))
                .check(matches(isDisplayed()))
        onView(withText("Crystal Palace")).perform(click())

        onView(withId(favoriteteam))
                .check(matches(isDisplayed()))
        onView(withId(favoriteteam)).perform(click())
        Thread.sleep(3000)
//       onView(withText("data berhasil disimpan")).check(matches(isDisplayed()))
        pressBack()
        onView(withId(navigation_buttom)).check(matches(isDisplayed()))
        onView(withId(nav_favorite)).perform(click())


    }
}
