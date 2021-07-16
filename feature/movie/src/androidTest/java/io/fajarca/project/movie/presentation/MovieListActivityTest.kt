package io.fajarca.project.movie.presentation

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
class MovieListActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun test() {
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
    }
}