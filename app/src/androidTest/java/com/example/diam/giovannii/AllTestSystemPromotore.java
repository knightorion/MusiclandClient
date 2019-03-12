package com.example.diam.giovannii;

import com.example.diam.giovannii.presenter.PresenterAutenticazione;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

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

public class AllTestSystemPromotore {

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

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Miei Eventi")).perform(click());
        onView(withId(R.id.textView_lista_eventi_promotore)).check(matches(withText("I miei eventi:")));
        sleep(1000);

        onView(withText("Serata Metal")).perform(click());
        onView(withId(R.id.textView_nome_evento_visualizzabile)).check(matches(withText("Serata Metal")));
        sleep(1000);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Cerca Artisti")).perform(click());
        onView(withId(R.id.textView_lista_artisti)).check(matches(isDisplayed()));

        onView(withText("elizabeth")).perform(click());
        onView(withId(R.id.textView_email_artista_visualizzabile)).check(matches(withText("anna@gmail.com")));
        sleep(1000);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Cerca Band")).perform(click());
        onView(withId(R.id.textView_lista_band)).check(matches(isDisplayed()));
        sleep(1000);

        onView(withText("Apocalittici")).perform(click());
        onView(withId(R.id.textView_email_band_visualizzabile)).check(matches(withText("primaBand@gmail.com")));
        sleep(1000);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Mie Proposte Evento")).perform(click());
        onView(withId(R.id.textView_proposte_evento_promotore)).check(matches(isDisplayed()));

        onView(withText("Proposta Serata Trap")).perform(click());
        onView(withId(R.id.textView_nome_proposta_evento)).check(matches(withText("Proposta Serata Trap")));
        sleep(1000);

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        onView(withText("Logout")).perform(click());
    }
}