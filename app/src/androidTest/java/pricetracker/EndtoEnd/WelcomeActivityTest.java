package pricetracker.EndtoEnd;


import android.content.Context;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pricetracker.Presentation.Welcome_Activity;
import pricetracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {

    @Rule
    public ActivityScenarioRule<Welcome_Activity> wActivityTestRule = new ActivityScenarioRule<Welcome_Activity>(Welcome_Activity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.pricetracker", appContext.getPackageName());
    }

    @Test
    public void selectButton1Test() {
        //        Check that B.C. region button click region tax to correct value
        ViewInteraction button1 = onView(
                allOf(withId(R.id.button1), withText("B.C."),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        button1.check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.regionText), withText("BritishColumbia")));

//        Check that each region button takes you to the search page (main activity)
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));
    }

    @Test
    public void selectButton5Test() {
//        Check that MANITOBA region button click sets region to correct value
        ViewInteraction button5 = onView(
                allOf(withId(R.id.button5), withText("MANITOBA"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        button5.check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.regionText), withText("Manitoba")));

//        Check that each region button takes you to the search page (main activity)
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));
    }

    @Test
    public void selectButton6Test() {
//        Check that ALBERTA region button click sets region to correct value
        ViewInteraction button6 = onView(
                allOf(withId(R.id.button6), withText("ALBERTA"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        button6.check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.regionText), withText("Alberta")));

//        Check that each region button takes you to the search page (main activity)
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));
    }

    @Test
    public void selectButton7Test() {
//        Check that ONTARIO region button click sets region to correct value
        ViewInteraction button7 = onView(
                allOf(withId(R.id.button7), withText("ONTARIO"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        button7.check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.regionText), withText("Ontario")));

//        Check that each region button takes you to the search page (main activity)
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));
    }
}
