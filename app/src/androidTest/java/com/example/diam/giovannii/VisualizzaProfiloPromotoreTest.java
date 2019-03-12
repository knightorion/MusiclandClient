package com.example.diam.giovannii;

import android.app.Instrumentation;
import android.content.Intent;

import com.example.diam.giovannii.menu.MainActivityBandArtisti;
import com.example.diam.giovannii.menu.MainActivityPromotore;
import com.example.diam.giovannii.presenter.PresenterAutenticazione;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressMenuKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.getIntents;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static java.util.concurrent.CompletableFuture.allOf;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class VisualizzaProfiloPromotoreTest {

    private String emailDaDigitare;
    private String passwordDaDigitare;


    @Rule
    public ActivityTestRule<PresenterAutenticazione> testLogin =
            new ActivityTestRule<>(PresenterAutenticazione.class);

    @Before
    public void initValidString() {
        emailDaDigitare = "promotore2@gmail.com";
        passwordDaDigitare = "promo2";
    }


    @Test
    public void clickLogin_accedi() throws InterruptedException {
        onView(withId(R.id.email)).perform(typeText(emailDaDigitare));
        onView(withId(R.id.editTextPassword)).perform(typeText(passwordDaDigitare), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        assertEquals("promotore2@gmail.com", emailDaDigitare);
        assertEquals("promo2", passwordDaDigitare);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Mio Profilo")).perform(click());

        //onView(withId(R.id.textView_profilo_promotore)).check(matches(withText("Visualizza il tuo profilo")));
        onView(withId(R.id.textView_nome_promotore_visualizzabile)).check(matches(withText("Andrea")));


        sleep(1000);

    }

}
