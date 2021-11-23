package pricetracker.Business;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import pricetracker.Logic.logicLayer;
import pricetracker.Objects.Item;
import pricetracker.Data.IDBLayer;
import pricetracker.Data.ItemStorageStub;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LogicLayerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testlogicLayer(){
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        Assert.assertNotNull(newlogic.getDb());
    }

    @Test
    public void testAccessPrice() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setCurrProd("Fake Item Three");
        System.out.println(newlogic.accessPrice());
        Assert.assertNotNull(newlogic.accessPrice());
    }

    @Test
    public void testAccessImg() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setCurrProd("Fake Item Three");
        System.out.println(newlogic.accessImg());
        Assert.assertNotNull(newlogic.accessImg());
    }

    @Test
    public void testAccessReview() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setCurrProd("Fake Item Three");
        System.out.println(newlogic.accessReview());
        Assert.assertNotNull(newlogic.accessImg());
    }

    @Test
    public void testAccessDes() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setCurrProd("Fake Item Three");
        System.out.println(newlogic.accessDes());
        Assert.assertNotNull(newlogic.accessDes());
    }

    @Test
    public void testRegion() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);

        newlogic.setRegion("B.C");
        assertEquals("BritishColumbia", newlogic.getRegion());
        newlogic.setRegion("Alberta");
        assertEquals("Alberta", newlogic.getRegion());
        newlogic.setRegion("Manitoba");
        assertEquals("Manitoba", newlogic.getRegion());
    }

    @Test
    public void testTax() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setRegion("B.C");
        float testVal = newlogic.tax();
        assertEquals((float)1.12, testVal);
    }

    @Test
    public void testTaxTotal() throws IOException {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setRegion("B.C");
        float testVal = newlogic.taxTotal(1.00);
        assertEquals((float)1.12, testVal);
    }


    @Test
    public void testSort(){

        logicLayer newlogic = new logicLayer(null);
        Item testItemA = new Item.Builder().setName("dvd").setPrice(54).setStore(null).setDesc(null).createItem();
        Item testItemB = new Item.Builder().setName("zebra").setPrice(3).setStore(null).setDesc(null).createItem();
        Item testItemC = new Item.Builder().setName("Apple").setPrice(66).setStore(null).setDesc(null).createItem();
        Item testItemD = new Item.Builder().setName("orange").setPrice(2).setStore(null).setDesc(null).createItem();

        newlogic.getCart().add(testItemA);
        newlogic.getCart().add(testItemB);
        newlogic.getCart().add(testItemC);
        newlogic.getCart().add(testItemD);

        newlogic.sortName();
        System.out.println(newlogic.getCart().get(0));
        System.out.println(newlogic.getCart().get(1));
        System.out.println(newlogic.getCart().get(2));
        System.out.println(newlogic.getCart().get(3));
        Assert.assertEquals(newlogic.getCart().getFirst(), testItemC);
        newlogic.sortPrice();
        Assert.assertEquals(newlogic.getCart().getFirst(), testItemD);

    }

    @Test
    public void testAddCart() {
        logicLayer newlogic = new logicLayer(null);
        Item testItemA = new Item.Builder().setName("dvd").setPrice(54).setStore(null).setDesc(null).createItem();
        newlogic.setCurrProd(testItemA);
        int updatedStock = newlogic.newStock();
        newlogic.addToCart(updatedStock);
        Assert.assertNotNull(newlogic.getCart());
    }

    @Test
    public void testTotalCartVal() {
        System.out.println("\nStarting LogicLayerTest: testSubTotalCart()");

        logicLayer newlogic = new logicLayer(null);
        Item itemA = new Item.Builder().setName("dvd").setPrice(54).setStock(3).setStore(null).setDesc(null).createItem();
        Item itemB = new Item.Builder().setName("zebra").setPrice(3).setStock(3).setStore(null).setDesc(null).createItem();

        newlogic.setCurrProd(itemA);
        newlogic.addToCart(newlogic.newStock());
        newlogic.setCurrProd(itemB);
        newlogic.addToCart(newlogic.newStock());

        double expected = Math.round((itemA.getProdPrice() + itemB.getProdPrice()) * newlogic.tax() * 100) / 100.0;
        double actual = Math.round(newlogic.totalCartVal() * 100) / 100.0;
        Assert.assertEquals(expected, actual,0.01);
        System.out.println("Completed LogicLayerTest: testSubTotalCart()");
    }

    @Test
    public void testRegionTax() {
        System.out.println("\nStarting LogicLayerTest: testRegionTax()");
        logicLayer newlogic = new logicLayer(null);

        newlogic.setRegion("Ontario");
        Assert.assertEquals(1.00f, newlogic.tax(),0.01);
        newlogic.setRegion("Manitoba");
        Assert.assertEquals(1.12f, newlogic.tax(),0.01);
        newlogic.setRegion("Alberta");
        Assert.assertEquals(1.15f, newlogic.tax(),0.01);
        newlogic.setRegion("B.C");
        Assert.assertEquals(1.12f, newlogic.tax(),0.01);

        Item testItemA = new Item.Builder().setName("dvd").setPrice(54).setStore(null).setDesc(null).createItem();
        newlogic.setCurrProd(testItemA);
        newlogic.addToCart(newlogic.newStock());

        double expected = Math.round(newlogic.totalCartVal() * 1.12 * 100) / 100.0;
        Assert.assertEquals(expected, Math.round(newlogic.checkOut() * 100) / 100.0,0.01);
        System.out.println("\nCompleted LogicLayerTest: testRegionTax()");

        assertNotNull(newlogic.getCart());
    }

   @Test
    public void buyDirectTest(){

       System.out.println("\nStarting LogicLayerTest: orderHistoryTest()");
       logicLayer newlogic = new logicLayer(null);
       LinkedList<Item> orderHistory = mock(LinkedList.class);
       newlogic.orderHistory = orderHistory;
       Item itemA = new Item.Builder().setName("dvd").setPrice(54).setStore(null).setDesc(null).createItem();
       newlogic.setCurrProd(itemA);
       newlogic.addToHistoryDir();

       verify(orderHistory, times(1)).add(itemA);
   }

    @ Test
    public void orderHistoryTest(){
        System.out.println("\nStarting LogicLayerTest: orderHistoryTest()");
        logicLayer newlogic = new logicLayer(null);
        LinkedList<Item> orderHistory = mock(LinkedList.class);
        newlogic.orderHistory = orderHistory;
        Item itemA = new Item.Builder().setName("dvd").setPrice(54).setStore(null).setDesc(null).createItem();
        newlogic.setCurrProd(itemA);
        newlogic.addToCart(newlogic.newStock());
        newlogic.checkOut();
        verify(orderHistory, times(1)).addAll(newlogic.cart);
    }

    @Test
    public void searchTest() {
        IDBLayer newDB = new ItemStorageStub();
        logicLayer newlogic = new logicLayer(null);
        newlogic.setDb(newDB);
        ArrayList<Item> starter = new ArrayList<>();

        int x = newlogic.querySearch(starter, newDB, "Fake Item").size();
        assertEquals(2, x);
    }

    @Test
    public void availabilityTest() {
        logicLayer newlogic = new logicLayer(null);

        Item itemA = new Item.Builder().setName("dvd").setPrice(54).setStore(null).setDesc(null).setStock(1).createItem();
        newlogic.setCurrProd(itemA);
        newlogic.addToCart(newlogic.newStock());
        assertEquals(0, itemA.getProdStock());

        Item itemB = new Item.Builder().setName("zebra").setPrice(3).setStore(null).setDesc(null).setStock(2).createItem();
        newlogic.setCurrProd(itemB);
        newlogic.addToCart(newlogic.newStock());
        assertEquals(1, itemB.getProdStock());

        newlogic.getCart().clear();
        newlogic.setCurrProd(itemA);
        newlogic.addToCart(newlogic.newStock());
        assertEquals(0.0, newlogic.totalCartVal());

    }
}

