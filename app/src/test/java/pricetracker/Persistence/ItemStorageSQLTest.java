package pricetracker.Persistence;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pricetracker.Data.ItemStorageSQL;
import pricetracker.Objects.Item;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ItemStorageSQLTest {

    byte[] stubContent = "test input stream".getBytes();
    InputStream stub = new ByteArrayInputStream(stubContent);
    ArrayList<Item> items = new ArrayList<Item>();
    SQLiteDatabase db = mock(SQLiteDatabase.class);

    @Test
    public void initializeTest() {
        new ItemStorageSQL(null);
    }

    @Test
    public void onCreateTest() throws FileNotFoundException {
        System.out.println("\nStarting ItemStorageSQLTest: onCreateTest()");
        ItemStorageSQL storageSQL = mock(ItemStorageSQL.class);

        doCallRealMethod().when(storageSQL).onCreate(db);
        storageSQL.onCreate(db);
        verify(storageSQL, times(1)).onCreate(db);

        System.out.println("onCreate() calls copyDB() and populateDatabase()");
        verify(storageSQL).copyDB();
        verify(storageSQL).populateDatabase();

        System.out.println("Completed ItemStorageSQLTest: onCreateTest()");
    }

    @Test
    public void setDatabaseTest() {
        System.out.println("\nStarting ItemStorageSQLTest: setDatabaseTest()");
        ItemStorageSQL storageSQL = mock(ItemStorageSQL.class);

        doCallRealMethod().when(storageSQL).setDatabase(stub);
        storageSQL.setDatabase(stub);

        verify(storageSQL, times(1)).setDatabase(stub);
        System.out.println("Completed ItemStorageSQLTest: setDatabaseTest()");
    }

    @Test
    public void getDatabaseTest() {
        System.out.println("\nStarting ItemStorageSQLTest: setDatabaseTest()");
        ItemStorageSQL storageSQL = mock(ItemStorageSQL.class);

        doCallRealMethod().when(storageSQL).setDatabase(stub);
        storageSQL.setDatabase(stub);
        verify(storageSQL, times(1)).setDatabase(stub);

        when(storageSQL.getDatabase()).thenCallRealMethod();
        InputStream output = storageSQL.getDatabase();
        verify(storageSQL, times(1)).getDatabase();
        assertNotNull(output);
        System.out.println("Completed ItemStorageSQLTest: setDatabaseTest()");
    }

    @Test
    public void searchTest() throws IOException {
        System.out.println("\nStarting ItemStorageSQLTest: searchTest()");
        InputStream inp = new FileInputStream("src\\main\\java\\pricetracker\\sample_data.csv");
        ItemStorageSQL storageSQL = mock(ItemStorageSQL.class);

        doCallRealMethod().when(storageSQL).setDatabase(inp);
        storageSQL.setDatabase(inp);

        when(storageSQL.search("")).thenReturn(items);
        storageSQL.search("");

        verify(storageSQL, times(1)).search("");
        System.out.println("Completed ItemStorageSQLTest: searchTest()");
    }

    @Test
    public void readFromFileTest() throws IOException {
        ItemStorageSQL storageSQL = mock(ItemStorageSQL.class);
        SQLiteDatabase db = storageSQL.getWritableDatabase();

        BufferedReader mBReader = mock(BufferedReader.class);
        doCallRealMethod().when(storageSQL).buildFromFile(mBReader, db);
        storageSQL.buildFromFile(mBReader, db);
    }

    @Test
    public void addToCVTest() {
        ItemStorageSQL storageSQL = new ItemStorageSQL(null);
        Item testItemA = new Item.Builder().setName("dvd").setPrice(54).setStore("storeA").setDesc("Nothing special").createItem();
        storageSQL.addToCV(testItemA);
    }

    @Test
    public void addItemTest() {
        ItemStorageSQL storageSQL = new ItemStorageSQL(null);
        Item testItemA = new Item.Builder().setName("dvd").setPrice(54).setStore("storeA").setDesc("Nothing special").createItem();
        SQLiteDatabase newDB = storageSQL.getWritableDatabase();
        storageSQL.addItem(testItemA, newDB);
    }

    @Test
    public void dbInputTest() throws IOException {
        ItemStorageSQL storageSQL = new ItemStorageSQL(null);
        String out = "testoutput";
        storageSQL.dbInput(stub, out);
    }

    @Test
    public void upgradeDBTest() {
        System.out.println("\nStarting ItemStorageSQLTest: populateDBTest()");
        ItemStorageSQL storageSQL = mock(ItemStorageSQL.class);

        doCallRealMethod().when(storageSQL).onCreate(db);
        storageSQL.onCreate(db);

        doCallRealMethod().when(storageSQL).onUpgrade(db, 1, 2);
        storageSQL.onUpgrade(db, 1, 2);
        verify(storageSQL, times(1)).onUpgrade(db, 1, 2);
        System.out.println("Completed ItemStorageSQLTest: populateDBTest()");
    }
}
