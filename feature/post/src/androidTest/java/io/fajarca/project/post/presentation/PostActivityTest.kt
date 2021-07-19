package io.fajarca.project.post.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.fajarca.project.post.R
import io.fajarca.project.post.presentation.list.PostActivity
import io.fajarca.project.test_shared.webserver.MockWebServerDispatcher
import io.fajarca.project.test_shared.webserver.MockWebServerRule
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class PostActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule = ActivityScenarioRule(PostActivity::class.java)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun progressBarShouldBeDisplayedAtInitial() {
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenFetchPostsSuccessRecyclerViewShouldBeDisplayed() {
        mockWebServerRule.server.dispatcher = MockWebServerDispatcher().SuccessDispatcher(200)

        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenFetchPostsFailedRecyclerViewShouldBeGone() {
        mockWebServerRule.server.dispatcher = MockWebServerDispatcher().ErrorDispatcher(404)

        onView(withId(R.id.recyclerView))
            .check(matches(not(isDisplayed())))
    }

}