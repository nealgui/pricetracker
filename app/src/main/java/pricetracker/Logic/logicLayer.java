package pricetracker.Logic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.*;

import pricetracker.Objects.*;
import pricetracker.Data.*;

public class logicLayer implements logicInterface {
    public IDBLayer db; //database
    Item currProd;// The current product we're going to access price/ image/ or description of it.
    boolean availability = true;

    public LinkedList<Item> cart; //linked list to store the item that user put into cart
    public LinkedList<Item> orderHistory; //linked list to store the items that user has bought

    public enum region
    {
        // Manitoba is our default region
        Alberta , Manitoba , BritishColumbia, Ontario;
    }

    region reg = region.Manitoba; // the region that user locate, use this to calcuate the tax
    boolean regionSet = false;

    public logicLayer(IDBLayer db) {
        cart = new LinkedList<Item>();
        orderHistory = new LinkedList<Item>();
        this.db = db;
    }

    public IDBLayer getDb() {
        return db;
    }

    public void setDb(IDBLayer db){this.db = db;}

    public LinkedList<Item> getCart() {
        return cart;
    }

    public LinkedList<Item> getOrderHistory() {
        return orderHistory;
    }

    public float accessPrice() {
        return currProd.getProdPrice();
    }

    public String accessImg() {
        return currProd.getImage();
    }

    public String accessDes() {
        return currProd.getDesc();
    }

    public String accessReview() {
        return currProd.getReview();
    }

    public void setRegion(String r)
    {

        if(!regionSet || !r.equals("")) {
            switch (r) {
                case "Manitoba":
                    reg = region.Manitoba;
                    break;
                case "B.C":
                    reg = region.BritishColumbia;
                    break;
                case "Alberta":
                    reg = region.Alberta;
                    break;
                case "Ontario":
                    this.reg = region.Ontario;
                    break;
            }
            regionSet = true;
        }
    }

    public String getRegion() {
        return reg.name();
    }

    public void setCurrProd(String name) {
        //When we select enter a product page, we need to set its as the currProduct
        currProd = db.search(name).get(0);
    }

    public ArrayList<Item> querySearch(ArrayList<Item> itemList, IDBLayer db, String query) {
        itemList.clear();

        // searching through the db, add results to list to display
        ArrayList<Item> x = db.search(query);

        itemList.addAll(x);

        System.out.println(itemList.size());
        return itemList;
    }


    public void setCurrProd(Item prod) {
        //When we select enter a product page, we need to set its as the currProduct
        currProd = prod;
    }

//    public addToCart() {
//        //put the currProd( item that user are checking out) into the cart
//        cart.add(currProd);
//    }

    public void updateStock(int newStock) {
        currProd.updateStock(newStock);
    }

    public void addToCart(int newStock) {
        //put the currProd( item that user are checking out) into the cart
        //otherwise should do nothing
        if (newStock > -1) {
            updateStock(newStock);
            cart.add(currProd);
        }
    }

    public int newStock() {
        int initStock = currProd.getProdStock();
        if (initStock > 0) {
            return initStock - 1;
        }else {
            return -1;
        }
    }

    public boolean getAvailability() {
        return this.availability;
    }

    public void addToHistoryDir() {
        //when you directly bought the item, it automatically added to the history
        orderHistory.add(currProd);
    }
    public void addToHistory() {
        //After checking out in the cart page, the item in the cart are put into the order history
        orderHistory.addAll(cart);
    }

    public float tax() {

        float tax;
        switch (reg) {
            case Manitoba:
            case BritishColumbia:
                tax = (float) 1.12;
                break;
            case Alberta:
                tax = (float) 1.15;
                break;
            default:
                tax = 1;
                break;
        }

        return tax;
    }

    public double checkOut() {
        double withOutTax = totalCartVal(); // total Value of the cart without tax
        double withTax = taxTotal(withOutTax); // total Value of the cart with Tax, it is what user need to pay
        addToHistory();
        System.out.println(cart + "\n");
        cart.clear(); // Clear The Cart After Checking Out
        return withTax;
    }

    //Invoke this function once you press the "buy" button in GUI, also display in GUI,  this one include the tax
    public double totalCartVal() {
        //Iterate the cart to calculate the total $
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total = total + cart.get(i).getProdPrice();
        }
        // withTax
        return taxTotal(total);
    }

    //Display the total Value of Cart in GUI, provide the total value without tax to user
    public double noTaxTotal() {
        //Iterate the cart to calculate the total $
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total = total + cart.get(i).getProdPrice();
        }
        return total;
    }

    public float taxTotal(double withoutTax) {
        //Iterate the cart to calculate the tax
        float tax = tax();
        // withTaxTotal:
        return (float) (withoutTax * tax);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortName() {

        cart.sort(Comparator.comparing(Item::getProdName));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortPrice() {

        cart.sort(Comparator.comparing(Item::getProdPrice));
    }

}
