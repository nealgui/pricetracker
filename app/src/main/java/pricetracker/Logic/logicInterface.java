package pricetracker.Logic;

import java.util.ArrayList;
import java.util.LinkedList;

import pricetracker.Data.IDBLayer;
import pricetracker.Data.ItemStorageSQL;
import pricetracker.Objects.Item;

public interface logicInterface {

     // Each time when we select the product (enter the item detail page),
     // we're going to check out, we set it as the
    // current product, then access its price/ description/ ...
     LinkedList<Item> getCart();
     LinkedList<Item> getOrderHistory();

     float accessPrice();
     String accessImg();
     String accessDes();
     String accessReview();
     String getRegion();
     boolean getAvailability();
     int newStock();

     void setDb(IDBLayer db);
     IDBLayer getDb();
     void setCurrProd(Item prod);
     void setRegion(String r);
     void updateStock(int newStock);

     void addToCart(int newStock); // add the current product to the cart (if available)
     void addToHistory();
     void addToHistoryDir();

     double totalCartVal();
     double checkOut();
     double noTaxTotal(); // checkout and return the total value, Also return the total price you need to pay that
    // can be shown in GUI
    ArrayList<Item> querySearch(ArrayList<Item> itemList, IDBLayer db, String query);

}
