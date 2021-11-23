package pricetracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import pricetracker.Objects.Item;

//probably going to switch to SQLite, it's a lot more understandable than HSQLDB
public class ItemStorageSQL extends SQLiteOpenHelper implements IDBLayer {
    //Variables
    SQLiteDatabase db;
    private InputStream dbfile;
    private Context context;

    public ItemStorageSQL(@Nullable Context context) {
        super(context, "items.db", null, 1);
        if (context != null) {
            this.context = context;
            copyDB();
        }
    }

    public InputStream getDatabase(){return this.dbfile;}
    public void setDatabase(InputStream pathToFile){this.dbfile = pathToFile;}// populateDatabase();}

    public ArrayList<Item> search(String query){
        ArrayList<Item> items = new ArrayList<Item>();
        //Build SQL statement from given query
        String sqlStatement = "SELECT * FROM ITEMS WHERE ITEMNAME LIKE '%"+query+"%'";
        SQLiteDatabase db = this.getReadableDatabase();

        //Get result set from constructed SQL statement
        Cursor cursor = db.rawQuery(sqlStatement,null);
        items = scanWithCursor(cursor, items);

        cursor.close();
        db.close();
        return items;
    }

    public ArrayList<Item> scanWithCursor(Cursor cursor, ArrayList<Item> items) {
        if(cursor.moveToFirst()){
            //loop through results and build items
            do{
                //Get values from columns of the table
                String prodName = cursor.getString(0);
                float prodPrice = cursor.getFloat(1);
                String storeName = cursor.getString(2);
                String desc = cursor.getString(3);
                String img = cursor.getString(4);
                int stock = cursor.getInt(5);
                int ph1 = cursor.getInt(6);
                int ph2 = cursor.getInt(7);
                int ph3 = cursor.getInt(8);
                int ph4 = cursor.getInt(9);
                int ph5 = cursor.getInt(10);
                int ph6 = cursor.getInt(11);
                String review = cursor.getString(12);
                //Build the item and add it to the return list
                Item item = new Item.Builder().setName(prodName).setPrice(prodPrice).setStore(storeName).setDesc(desc).setImg(img).setStock(stock).setPh1(ph1).setPh2(ph2).setPh3(ph3).setPh4(ph4).setPh5(ph5).setPh6(ph6).setReview(review).createItem();
                items.add(item);
            }while(cursor.moveToNext());
        }
        else{
            //Do not add anything to the list
        }
        return items;
    }

    //This gets called the first time we access the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating Database\n");
        String createTableStatement = "CREATE TABLE ITEMS (ITEMNAME VARCHAR(40) PRIMARY KEY, ITEMPRICE FLOAT, STORENAME VARCHAR(20), DESCRIPTION VARCHAR(150), IMAGE VARCHAR(50), ITEMSTOCK INTEGER, PRICEHISTORY1 INTEGER, PRICEHISTORY2 INTEGER, PRICEHISTORY3 INTEGER, PRICEHISTORY4 INTEGER, PRICEHISTORY5 INTEGER, PRICEHISTORY6 INTEGER)";
        db.execSQL(createTableStatement);
        copyDB();
        populateDatabase();
    }

    //Called when database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing to do here yet
    }


    public boolean addItem(Item item, SQLiteDatabase db){
        long insert = -1;
        try{
            //System.out.println("Adding Item "+item.getProdName()+" to DB.\n");
            //3. Insert the content values into the table
            insert = db.insert("ITEMS", null, addToCV(item));
            //System.out.println("Successfully added item: "+item.getProdName()+"\n");
        }catch(Exception e) {
            //Probably SQL UNIQUE CONSTRAINT FAILED, will get a lot of those messages so ignore them
            System.out.println("Failed to insert.\n");
        }
        return insert >= 0; //return if the item was properly added
    }

    public ContentValues addToCV(Item item) {
        //2. Build the content values to be inserted into the table from the given item
        ContentValues cv = new ContentValues();
        cv.put("ITEMNAME",item.getProdName());
        cv.put("ITEMPRICE",item.getProdPrice());
        cv.put("STORENAME",item.getStoreName());
        cv.put("DESCRIPTION",item.getDesc());
        cv.put("IMAGE",item.getImage());
        cv.put("ITEMSTOCK",item.getProdStock());
        cv.put("PRICEHISTORY1",item.getPh1());
        cv.put("PRICEHISTORY2",item.getPh2());
        cv.put("PRICEHISTORY3",item.getPh3());
        cv.put("PRICEHISTORY4",item.getPh4());
        cv.put("PRICEHISTORY5",item.getPh5());
        cv.put("PRICEHISTORY6",item.getPh6());
        cv.put("REVIEW",item.getReview());

        return cv;
    }

    public void copyDB(){
        try {
            String file = "items.db";
            String out = "data/data/com.example.pricetracker/databases/items.db";
            InputStream ip =  this.context.getAssets().open(file);
            dbInput(ip, out);
            ip.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dbInput(InputStream ip, String out) throws IOException {
        OutputStream output = new FileOutputStream(out);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = ip.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
    }

    public void populateDatabase(){
        //don't do anything if there's no database to load
        if(this.dbfile==null){
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            openDB(this.dbfile, db);
            db.close();
        }
    }

    public void openDB(InputStream inp, SQLiteDatabase db) {
        try{ //1. Open a csv file
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inp));
            System.out.println("Populating Database.\n");
            buildFromFile(bReader, db);
            bReader.close();
        }//try
        catch(FileNotFoundException e){
            System.out.println("The file was not found.");
            e.printStackTrace();
        }//catch file not existing
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }//catch everything
    }

    public void buildFromFile(BufferedReader bReader, SQLiteDatabase db) throws IOException {
        int counter = 0;
        //2. Read each line, split by ","
        String line;
        while((line = bReader.readLine()) != null){
            String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //the regex is used to properly split each line accounting for commas in fields

            //3. Change string price into a float
            data[1] = data[1].replace("$","");//get rid of $ at front of price
            float price = Float.parseFloat(data[1]);
            int stock = Integer.parseInt(data[5]);

            int ph1 = Integer.parseInt(data[6]);
            int ph2 = Integer.parseInt(data[7]);
            int ph3 = Integer.parseInt(data[8]);
            int ph4 = Integer.parseInt(data[9]);
            int ph5 = Integer.parseInt(data[10]);
            int ph6 = Integer.parseInt(data[11]);

            //4. Create Item based on data provided and push into our ItemList
            //System.out.println("Attempting to add item#"+counter+" to the database.\n");
            Item item = new Item.Builder().setName(data[0]).setPrice(price).setStore(data[2]).setDesc(data[3]).setImg(data[4]).setStock(stock).setPh1(ph1).setPh2(ph2).setPh3(ph3).setPh4(ph4).setPh5(ph5).setPh6(ph6).setReview(data[12]).createItem();
            this.addItem(item, db);
            //System.out.println("Added item#"+counter+".\n");
        }//while there are still lines to read
    }

}//class ItemStorageSQL
