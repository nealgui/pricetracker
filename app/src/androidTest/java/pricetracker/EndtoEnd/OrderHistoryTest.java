package pricetracker.EndtoEnd;


import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pricetracker.Presentation.MainActivity;
import pricetracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
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
public class OrderHistoryTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void orderHistory() {
        String[] item1 = {"Soup", "Soup - Chicken And Wild Rice", "$94.59"};
        String[] item2 = {"Apple", "Apple - Mackintosh", "$20.86"};

        //        Check that the order history button shows
        ViewInteraction order = onView(
                allOf(withId(R.id.order), withContentDescription("Order"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        order.check(matches(isDisplayed()));

        //        Check that the cart button shows
        ViewInteraction cart = onView(
                allOf(withId(R.id.cart), withContentDescription("Cart"),
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

        //        Search for the first item, then add to cart, then checkout from cart
        searchView.perform(click());
        onView(withId(R.id.searchView)).
                perform(ViewActions.typeText(item1[0])).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.button)).check(matches(isDisplayed())).perform(click());

        pressBack();
        onView(withId(R.id.cart)).perform(click());
        onView(withId(R.id.button2)).perform(click());

//        Search for the second item, then buy directly
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));

        searchView.perform(click());
        onView(withId(R.id.searchView)).
                perform(ViewActions.typeText(item2[0])).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.butButton)).perform(click());

//        Check that the purchased items now show in order history
        pressBack();
        onView(withId(R.id.order)).perform(click());
        onView(allOf(withId(R.id.textView), withText(item1[1]), withText(item2[1])));
        onView(allOf(withId(R.id.textPrice), withText(item1[2]), withText(item2[2])));
    }

}
