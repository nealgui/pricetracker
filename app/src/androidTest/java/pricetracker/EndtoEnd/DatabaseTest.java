package pricetracker.EndtoEnd;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import pricetracker.Data.ItemStorageSQL;
import pricetracker.Objects.Item;
import pricetracker.Data.IDBLayer;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.pricetracker", appContext.getPackageName());
    }

    @Test
    public void searchRunsTest(){
        System.out.println("\nStarting ItemStorageSQLTest: searchTest()");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        IDBLayer newDB = new ItemStorageSQL(appContext);
        assertNotNull(newDB);
        assertNotNull(newDB.search(""));
    }

    @Test
    public void searchWorksTest(){
        System.out.println("\nStarting ItemStorageSQLTest: searchTest()");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        IDBLayer newDB = new ItemStorageSQL(appContext);
        assertNotNull(newDB);
        ArrayList<Item> searchResults = newDB.search("Kiwano");
        assertNotNull(searchResults);
    }
}
