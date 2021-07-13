package io.fajarca.project.daggerplayground.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.fajarca.project.daggerplayground.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun whenLaunched_buttonMovieModuleShouldDisplayed() {
        onView(withId(R.id.btnGoToMovieModule))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenLaunched_buttonPostModuleShouldDisplayed() {
        onView(withId(R.id.btnGoToPostModule))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenLaunched_buttonUserModuleShouldDisplayed() {
        onView(withId(R.id.btnGoToUserModule))
            .check(matches(isDisplayed()))
    }
}