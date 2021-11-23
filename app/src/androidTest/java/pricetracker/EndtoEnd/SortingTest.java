package pricetracker.EndtoEnd;


import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pricetracker.Presentation.MainActivity;
import pricetracker.Presentation.itemAdapter;
import pricetracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class SortingTest {
    ViewInteraction searchView;
    ViewInteraction imageView2;
    String item1 = "Soup";

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() {
//        Check that the search button and search bar show
        ViewInteraction searchView = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.ImageView.class), withContentDescription("Search"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                withParent(withId(R.id.searchView)))),
                        isDisplayed()));
        this.searchView = searchView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withContentDescription("More options"),
                        withParent(withParent(withId(R.id.action_bar))),
                        isDisplayed()));
        this.imageView2 = imageView2.check(matches(isDisplayed()));
    }

    @Test
    public void checkAlphabeticalSorting() {
        searchView.perform(click());
        onView(withId(R.id.searchView)).perform(ViewActions.typeText(item1)).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));

        imageView2.perform(click());
        onView(withText("A->Z")).perform(click());

        onView(withId(R.id.recyclerView)).
                check(matches(isSortedAlphabetically()));

        SystemClock.sleep(2500);

        onView(withId(R.id.recyclerView)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition(10));

        SystemClock.sleep(2500);
    }

    @Test
    public void checkPriceSorting() {
        searchView.perform(click());
        onView(withId(R.id.searchView)).perform(ViewActions.typeText(item1)).
                perform(pressKey(KeyEvent.KEYCODE_ENTER));

        imageView2.perform(click());
        onView(withText("price")).perform(click());

        onView(withId(R.id.recyclerView)).check(matches(isSortedByPrice()));

        SystemClock.sleep(2500);

        onView(withId(R.id.recyclerView)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition(10));

        SystemClock.sleep(2500);
    }

//    Referred to https://blog.egorand.me/testing-a-sorted-list-with-espresso/ for steps
//    on how to go from recycler to adapter (instead of the other way)
    private Matcher<? super View> isSortedByPrice() {
        return new TypeSafeMatcher<View>() {
            private final List<Float> items = new ArrayList<>();

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView) item;
                itemAdapter adapter = (itemAdapter) recyclerView.getAdapter();
                items.clear();
                assert adapter != null;
                items.addAll(adapter.getPrices());
                Collections.sort(items);
                return items.equals(adapter.getPrices());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("- items sorted by price");
            }
        };
    }

    private Matcher<? super View> isSortedAlphabetically() {
        return new TypeSafeMatcher<View>() {

            private final List<String> items = new ArrayList<>();

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView) item;
                itemAdapter adapter = (itemAdapter) recyclerView.getAdapter();
                items.clear();
                items.addAll(adapter.getItems());
                Collections.sort(items);
                return items.equals(adapter.getItems());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("- items sorted alphabetically");
            }
        };
    }
}
