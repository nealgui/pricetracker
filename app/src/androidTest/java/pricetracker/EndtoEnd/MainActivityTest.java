package pricetracker.EndtoEnd;


import android.view.KeyEvent;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pricetracker.Presentation.MainActivity;
import pricetracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void showElementsTest() {
//        Check that logo shows
        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageView), withContentDescription("logo"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

//        Check that OrderHistory button shows
        ViewInteraction textView = onView(
                allOf(withId(R.id.order), withContentDescription("Order"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

//        Check that the cart button shows
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.cart), withContentDescription("Cart"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

//        Check that the "More options" button shows you the two sorting type choices
        ViewInteraction imageView2 = onView(
                allOf(withContentDescription("More options"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    @Test
    public void searchBasicTest() {
//        Check that the search button and search bar show
        ViewInteraction searchView = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.ImageView.class), withContentDescription("Search"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                withParent(withId(R.id.searchView)))),
                        isDisplayed()));
        searchView.check(matches(isDisplayed()));

//        Check that we can search for something and results will show
        searchView.perform(click());
        String searchVal = "Soup";
        onView(withId(R.id.searchView)).
                perform(ViewActions.typeText(searchVal)).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));
    }
}