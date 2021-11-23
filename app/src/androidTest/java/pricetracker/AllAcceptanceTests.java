package pricetracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pricetracker.EndtoEnd.AvailabilityTest;
import pricetracker.EndtoEnd.CartActivityTest;
import pricetracker.EndtoEnd.DatabaseTest;
import pricetracker.EndtoEnd.MainActivityTest;
import pricetracker.EndtoEnd.OrderHistoryTest;
import pricetracker.EndtoEnd.SortingTest;
import pricetracker.EndtoEnd.WelcomeActivityTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AvailabilityTest.class,
        CartActivityTest.class,
        DatabaseTest.class,
        MainActivityTest.class,
        OrderHistoryTest.class,
        SortingTest.class,
        WelcomeActivityTest.class
})

public class AllAcceptanceTests
{

}
