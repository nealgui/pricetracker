package pricetracker.EndtoEnd;


import android.view.KeyEvent;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pricetracker.Presentation.MainActivity;
import pricetracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AvailabilityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void orderActivityTest() {
//        Check that the cart button shows, and takes you to the cart and checkout page
//        Then go back to main, using the checkout button
        ViewInteraction cart = onView(
                Matchers.allOf(ViewMatchers.withId(R.id.cart), withContentDescription("Cart"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        cart.check(matches(isDisplayed()));

//        Check that the search button and search bar show
        ViewInteraction searchView = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.ImageView.class), withContentDescription("Search"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                withParent(withId(R.id.searchView)))),
                        isDisplayed()));
        searchView.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                        withParent(allOf(withId(R.id.searchView),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

//        Add first "Soup" item to cart, check that this reduces stock
        searchView.perform(click());
        String searchVal = "Soup";
        onView(withId(R.id.searchView)).perform(ViewActions.typeText(searchVal)).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.recyclerView)).
                check(new CartActivityTest.checkItemValue("Soup - Chicken And Wild Rice")).
                perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.stock), withText("17")));
        onView(withId(R.id.button)).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.stock), withText("16")));

        Espresso.pressBack();
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));
        cart.perform(click());
        onView(withId(R.id.button2)).perform(click());

//        Check that the item has been "restocked"
        searchView.check(matches(isDisplayed()));
        searchView.perform(click());
        onView(withId(R.id.searchView)).perform(ViewActions.typeText("Soup")).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));
//        Check that the first item shown has expected value for search query "Soup"
        onView(withId(R.id.recyclerView)).
                check(new CartActivityTest.checkItemValue("Soup - Chicken And Wild Rice")).
                perform(actionOnItemAtPosition(0, click()));
//        Purchase directly, check that this depletes stock
        onView(allOf(withId(R.id.stock), withText("17")));
        onView(withId(R.id.butButton)).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.stock), withText("16")));
        onView(withId(R.id.butButton)).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.stock), withText("15")));
    }
}
