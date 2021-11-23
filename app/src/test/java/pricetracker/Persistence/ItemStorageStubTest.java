package pricetracker.Persistence;

import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import pricetracker.Data.ItemStorageStub;
import pricetracker.Logic.logicLayer;
import pricetracker.Objects.Item;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ItemStorageStubTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSetDatabase() throws IOException {
        System.out.println("\nStarting ItemStorageStubTest: testSetDatabase");
        InputStream inp = new FileInputStream("src\\main\\java\\pricetracker\\sample_data.csv");
        ItemStorageStub storage = new ItemStorageStub();
        storage.setDatabase(inp);
        assertNotNull(storage);
        inp.close();
        System.out.println("Completed ItemStorageStubTest: testSetDatabase");
    }


    public void testGetDatabase() throws IOException {
        System.out.println("\nStarting ItemStorageStubTest: testGetDatabase");
        ItemStorageStub storage = new ItemStorageStub();
//        Note: using defined items One and Two, so shouldn't be assigned a database file
        assertNull(storage.getDatabase());
        System.out.println("Completed ItemStorageStubTest: testGetDatabase");
    }

    public void testSearch() throws IOException {
        System.out.println("\nStarting ItemStorageStubTest: testSearch");
        InputStream inp = new FileInputStream("src\\main\\java\\pricetracker\\sample_data.csv");
        ItemStorageStub storage = new ItemStorageStub();
        storage.setDatabase(inp);
        assertNotNull(storage);
//        Stub database has two elements, check that both are found
        assertEquals(2, storage.search("Fake").size());
        inp.close();
        System.out.println("Completed ItemStorageStubTest: testSearch");
    }

    public void testUpdateStock() throws FileNotFoundException {
        System.out.println("\nStarting ItemStorageStubTest: testUpdateStock");

//        Set logic instance to stub database
        InputStream inp = new FileInputStream("src\\main\\java\\pricetracker\\sample_data.csv");
        ItemStorageStub storage = new ItemStorageStub();
        storage.setDatabase(inp);
        logicLayer newlogic = new logicLayer(storage);

//        Find "Fake Item Two" in stub database, add to cart, get current stock
        Item itemA_before = newlogic.db.search("Two").get(1);
        int initial_stock = itemA_before.getProdStock();
        newlogic.getCart().add(itemA_before);

//        Update the database stock value for "Fake Item Two" to remove one from cart
        int updated_stock = initial_stock - 1;
        newlogic.cart.element().updateStock(updated_stock);
//        Check that the stock was updated
        Item itemA_after = newlogic.db.search("Two").get(1);

        assertEquals(initial_stock - 1, itemA_after.getProdStock());
        System.out.println("\nCompleted ItemStorageStubTest: testUpdateStock");
    }
}