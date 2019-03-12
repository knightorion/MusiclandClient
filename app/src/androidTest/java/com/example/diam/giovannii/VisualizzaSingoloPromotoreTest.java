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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class VisualizzaSingoloPromotoreTest {

    private String emailDaDigitare;
    private String passwordDaDigitare;


    @Rule
    public ActivityTestRule<PresenterAutenticazione> testLogin =
            new ActivityTestRule<>(PresenterAutenticazione.class);


    @Before
    public void initValidString() {
        emailDaDigitare = "artista1@gmail.com";
        passwordDaDigitare = "artista1";
    }

    @Test
    public void clickLogin_accedi() throws InterruptedException {

        onView(withId(R.id.email)).perform(typeText(emailDaDigitare));
        onView(withId(R.id.editTextPassword)).perform(typeText(passwordDaDigitare), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        assertEquals("artista1@gmail.com", emailDaDigitare);
        assertEquals("artista1", passwordDaDigitare);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Cerca Promotori")).perform(click());

        onView(withId(R.id.textView_lista_promotori)).check(matches(isDisplayed()));

        onView(withText("Luigi")).perform(click());
        onView(withId(R.id.textView_email_promotore_visualizzabile)).check(matches(withText("promotore1@gmail.com")));
sleep(1000);
    }
}
