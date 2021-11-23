package pricetracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pricetracker.Persistence.ItemStorageSQLTest;
import pricetracker.Persistence.ItemStorageStubTest;
import pricetracker.Business.LogicLayerTest;
import pricetracker.Objects.ItemTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemTest.class,
        ItemStorageStubTest.class,
        LogicLayerTest.class,
        ItemStorageSQLTest.class
})
public class AllTests
{

}
