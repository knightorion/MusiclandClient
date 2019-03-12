package com.example.diam.giovannii;

import com.example.diam.giovannii.presenter.PresenterAutenticazione;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class VisualizzaProfiloBandTest {

    private String emailDaDigitare;
    private String passwordDaDigitare;


    @Rule
    public ActivityTestRule<PresenterAutenticazione> testLogin =
            new ActivityTestRule<>(PresenterAutenticazione.class);


    @Before
    public void initValidString() {
        emailDaDigitare = "primaBand@gmail.com";
        passwordDaDigitare = "bandando";
    }

    @Test
    public void clickLogin_accedi() throws InterruptedException {

        onView(withId(R.id.email)).perform(typeText(emailDaDigitare));
        onView(withId(R.id.editTextPassword)).perform(typeText(passwordDaDigitare), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        assertEquals("primaBand@gmail.com", emailDaDigitare);
        assertEquals("bandando", passwordDaDigitare);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Mio Profilo")).perform(click());

        //onView(withId(R.id.textView_profilo_promotore)).check(matches(withText("Visualizza il tuo profilo")));
        onView(withId(R.id.textView_nome_band_visualizzabile)).check(matches(withText("Apocalittici")));

        sleep(1000);
    }
}