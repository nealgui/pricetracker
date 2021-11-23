package pricetracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pricetracker.Business.AdapterTest;
import pricetracker.Business.LogicLayerTest;
import pricetracker.Persistence.ItemStorageSQLTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        LogicLayerTest.class,
        AdapterTest.class,
        ItemStorageSQLTest.class
})


public class AllIntegrationTests {
}
