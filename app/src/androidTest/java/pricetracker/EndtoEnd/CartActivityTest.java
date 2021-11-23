package pricetracker.EndtoEnd;


import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
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
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CartActivityTest {
    String[] item1 = {"Soup", "Soup - Chicken And Wild Rice", "$94.59", "17", "Tambee",
            "Review: GOOD", "sed sagittis nam congue risus semper porta volutpat quam oede"};

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void showElementsTest() {
//        Check that the cart button shows, and takes you to the cart and checkout page
//        Then go back to main, using the checkout button
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.cart), withContentDescription("Cart"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                        withParent(allOf(withId(R.id.searchView),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    @Test
    public void addToCartTest() {
//        Check that the search button and search bar show
        ViewInteraction searchView = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.ImageView.class), withContentDescription("Search"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                withParent(withId(R.id.searchView)))),
                        isDisplayed()));
        searchView.check(matches(isDisplayed()));

//        Check that we can search for something and results will show
        searchView.perform(click());
        onView(withId(R.id.searchView)).
                perform(ViewActions.typeText(item1[0])).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));

//        Checks there is at least 1 item in the list of results
        onView(withId(R.id.recyclerView)).check(new checkItemCount());

//        Check that the first item shown has expected value for search query "Soup"
        onView(withId(R.id.recyclerView)).
                check(new checkItemValue(item1[1])).
                perform(actionOnItemAtPosition(0, click()));

//        Check that we can see all of the different attributes of an item (each one was a feature)
        onView(allOf(withId(R.id.title), withText(item1[0])));
        onView(allOf(withId(R.id.price), withText(item1[2])));
        onView(allOf(withId(R.id.stock), withText(item1[3])));
        onView(allOf(withId(R.id.store), withText(item1[4])));
        onView(allOf(withId(R.id.textView2), withText(item1[5])));
        onView(allOf(withId(R.id.description), withText(item1[6])));

//        Check that they display (not the values)
        onView(withId(R.id.sampleImg)).check(matches(isDisplayed()));
        onView(withId(R.id.graph)).check(matches(isDisplayed()));

        onView(withId(R.id.button)).
                check(matches(isDisplayed())).
                perform(click());
        pressBack();

//        Check that after navigating to the cart page, a total value is displayed,
//        and that the item was added to the cart
        onView(withId(R.id.cart)).perform(click());
        onView(withId(R.id.totalValue)).check(matches(isDisplayed()));
        String totalVal = String.valueOf((94.59 * 1.12));
        onView(allOf(withId(R.id.totalValue), withText(totalVal)));
        onView(allOf(withId(R.id.taxValue), withText(item1[2])));

//        Check that you can checkout and this takes you back to the main page
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.searchView)).check(matches(isDisplayed()));
    }

//    Notes from https://stackoverflow.com/questions/36399787/how-to-count-recyclerview-items-with-espresso
//    on how to set up these methods for using views
    public class checkItemCount implements ViewAssertion {
        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertTrue(adapter.getItemCount() > 0);
        }
    }

    public static class checkItemValue implements ViewAssertion {
        private final String itemName;
        public checkItemValue(String itemName1) {
            this.itemName = itemName1;
        }
        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
            TextView textView = viewHolder.itemView.findViewById(R.id.textView);
            assertThat(textView.getText(), is(itemName));
        }
    }

}
