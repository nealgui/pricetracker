package pricetracker.Data;

import java.io.InputStream;
import java.util.ArrayList;

import pricetracker.Objects.Item;

public class ItemStorageStub implements IDBLayer{
    //Variables
    private InputStream database = null;
    private Item itemOne, itemTwo;

    public ItemStorageStub(){
        //Make two fake items to be returned
        itemOne = new Item.Builder().setName("Fake Item One").setPrice(1.99f).setStore("Store One").setDesc("Description One").setImg("Image1").setStock(1).createItem();
        itemTwo = new Item.Builder().setName("Fake Item Two").setPrice(5.95f).setStore("Store Two").setDesc("Description Two").setImg("Image2").setStock(2).createItem();
    }

    public InputStream getDatabase(){return this.database;};
    public void setDatabase(InputStream pathToFile){};

    public ArrayList<Item> search(String query){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(itemOne);
        items.add(itemTwo);
        return items;
    }


}//class ItemStorageStub
