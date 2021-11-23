package pricetracker.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructorTwoTest() {

        System.out.println("\nStarting ItemTest: Constructor Two");
        Item newItem = new Item.Builder().setName("Name").setPrice(1.99f).setStore("Store,").setDesc("Description").createItem();

        assertNotNull(newItem);
        System.out.println("Completed ItemTest: Constructor Two");

    }

    @Test
    public void constructorThreeTest() {

        System.out.println("\nStarting ItemTest: Constructor Three");
        Item newItem = new Item.Builder().setName("Name").setPrice(1.99f).setStore("Store").setDesc("Description").setImg("Item").createItem();

        assertNotNull(newItem);
        System.out.println("Completed ItemTest: Constructor Three");

    }

    @Test
    public void defaultDescTest() {

        System.out.println("\nStarting ItemTest: defaultDescTest()");
        Item newItem = new Item.Builder().setName("Item1").setPrice(1.99f).setStore("StoreX").createItem();

        assertEquals("", newItem.getDesc());
        System.out.println("Completed ItemTest: defaultDescTest()");
    }

    @Test
    public void defaultImageTest() {

        System.out.println("\nStarting ItemTest: defaultImageTest()");
        Item newItem = new Item.Builder().setName("Item1").setPrice(1.99f).setStore("StoreX").createItem();

        assertEquals("", newItem.getImage());
        System.out.println("Completed ItemTest: defaultImageTest()");
    }

    @Test
    public void defaultReviewTest() {

        System.out.println("\nStarting ItemTest: defaultReviewTest()");
        Item newItem = new Item.Builder().setName("Item1").setPrice(1.99f).setStore("StoreX").createItem();

        assertEquals("", newItem.getReview());
        System.out.println("Completed ItemTest: defaultReviewTest()");
    }

    @Test
    public void defaultStockTest() {

        System.out.println("\nStarting ItemTest: defaultStockTest()");
        Item newItem = new Item.Builder().setName("Item1").setPrice(1.99f).setStore("StoreX").createItem();

        assertEquals(0, newItem.getProdStock());
        System.out.println("Completed ItemTest: defaultStockTest()");
    }

    @Test
    public void getProdName() {

        System.out.println("\nStarting ItemTest: getProdName()");
        Item newItem = new Item.Builder().setName("ProdNameTest").createItem();

        assertNotNull(newItem);
        assertEquals("ProdNameTest", newItem.getProdName());
        System.out.println("Completed ItemTest: getProdName()");

    }

    @Test
    public void getProdPrice() {

        System.out.println("\nStarting ItemTest: getProdPrice()");
        Item newItem = new Item.Builder().createItem();
        newItem.updatePrice(1.99f);

        assertNotNull(newItem);
        assertEquals(1.99, newItem.getProdPrice(), 0.1);
        System.out.println("Completed ItemTest: getProdPrice()");

    }

    @Test
    public void getStoreName() {

        System.out.println("\nStarting ItemTest: getProdPrice()");
        Item newItem = new Item.Builder().createItem();
        newItem.updateStore("StoreNameTest");

        assertNotNull(newItem);
        assertEquals("StoreNameTest", newItem.getStoreName());
        System.out.println("Completed ItemTest: getProdPrice()");

    }

    @Test
    public void getDesc() {

        System.out.println("\nStarting ItemTest: getDesc()");
        Item newItem = new Item.Builder().setName("Name").setPrice(1.99f).setStore("Store,").setDesc("Description").createItem();

        assertNotNull(newItem);
        assertEquals("Description", newItem.getDesc());
        System.out.println("Completed ItemTest: getDesc()");

    }

    @Test
    public void getImage() {

        System.out.println("\nStarting ItemTest: getImage()");
        Item newItem = new Item.Builder().setName("Name").setPrice(1.99f).setStore("Store,").setDesc("Description").setImg("Image").createItem();

        assertNotNull(newItem);
        assertEquals("Image", newItem.getImage());
        System.out.println("Completed ItemTest: getImage()");

    }

    @Test
    public void getReview() {

        System.out.println("\nStarting ItemTest: getReview()");
        Item newItem = new Item.Builder().setName("Name").setPrice(1.99f).setStore("Store,").setReview("Wonderful product!").createItem();

        assertNotNull(newItem);
        assertEquals("Wonderful product!", newItem.getReview());
        System.out.println("Completed ItemTest: getReview()");

    }

    @Test
    public void getProdStock() {

        System.out.println("\nStarting ItemTest: getImage()");
        Item newItem = new Item.Builder().setName("Name").setPrice(1.99f).setStore("Store,").setDesc("Description").setImg("Image").setStock(1).createItem();

        assertNotNull(newItem);
        assertEquals(1, newItem.getProdStock());
        System.out.println("Completed ItemTest: getImage()");

    }

    @Test
    public void setProdName() {

        System.out.println("\nStarting ItemTest: setProdName()");
        Item newItem = new Item.Builder().setName("Product").createItem();

        assertNotNull(newItem);
        assertEquals("Product", newItem.getProdName());
        System.out.println("Completed ItemTest: setProdName()");

    }

    @Test
    public void setProdPrice() {

        System.out.println("\nStarting ItemTest: setProdPrice()");
        Item newItem = new Item.Builder().createItem();

        newItem.updatePrice(1.99f);
        assertNotNull(newItem);
        assertEquals(1.99,newItem.getProdPrice(),0.1);
        System.out.println("Completed ItemTest: setProdPrice()");

    }

    @Test
    public void setStoreName() {

        System.out.println("\nStarting ItemTest: setStoreName()");
        Item newItem = new Item.Builder().createItem();

        newItem.updateStore("Store");
        assertNotNull(newItem);
        assertEquals("Store", newItem.getStoreName());
        System.out.println("Completed ItemTest: setStoreName()");

    }

    @Test
    public void setDesc() {

        System.out.println("\nStarting ItemTest: setDesc()");
        Item newItem = new Item.Builder().setDesc("Description").createItem();

        assertNotNull(newItem);
        assertEquals("Description", newItem.getDesc());
        System.out.println("Completed ItemTest: setDesc()");
    }

    @Test
    public void setImage() {

        System.out.println("\nStarting ItemTest: setImage()");
        Item newItem = new Item.Builder().createItem();

        newItem.updateImage("Image");
        assertNotNull(newItem);
        assertEquals("Image", newItem.getImage());
        System.out.println("Completed ItemTest: setImage()");
    }

    @Test
    public void setProdStock() {

        System.out.println("\nStarting ItemTest: setProdStock()");
        Item newItem = new Item.Builder().createItem();

        newItem.updateStock(1);
        assertNotNull(newItem);
        assertEquals(1, newItem.getProdStock());
        System.out.println("Completed ItemTest: setProdStock()");
    }

    @Test
    public void setReview() {

        System.out.println("\nStarting ItemTest: setReview()");
        Item newItem = new Item.Builder().createItem();

        newItem.updateReview("Item arrived late.");
        assertNotNull(newItem);
        assertEquals("Item arrived late.", newItem.getReview());
        System.out.println("Completed ItemTest: setReview()");
    }
}