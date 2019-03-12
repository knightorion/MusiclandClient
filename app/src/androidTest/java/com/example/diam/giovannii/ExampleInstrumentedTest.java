package com.example.diam.giovannii;

import android.content.Context;

import com.example.diam.giovannii.menu.MainPrincipale;
import com.example.diam.giovannii.presenter.PresenterAutenticazione;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainPrincipale> activityTestRule = new ActivityTestRule<>(MainPrincipale.class);

    @Test
    public void luisa(){
        onView(withId(R.id.textViewWelcome)).check(matches(isDisplayed()));
    }



}
