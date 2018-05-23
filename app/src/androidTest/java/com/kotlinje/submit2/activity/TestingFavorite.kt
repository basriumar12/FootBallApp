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
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import com.kotlinje.submit2.R.id.*
import com.kotlinje.submit2.adapter.AdapterFavorite
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class TestingFavorite {

    @Rule
    @JvmField var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testingFavorite() {
        val recyclerView = onView(
                allOf(withId(R.id.layoutLiga),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                1)))
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        val actionMenuItemView = onView(
                allOf(withId(favoriteteam), withContentDescription("favorit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        pressBack()
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val bottomNavigationItemView = onView(
                allOf(withId(nav_favorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_buttom),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val recyclerView2 = onView(
                allOf(withId(rv_favorite),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                0)))
        recyclerView2.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.favoriteteam), withContentDescription("favorit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
