package pricetracker.Data;

import java.io.InputStream;
import java.util.ArrayList;

import pricetracker.Objects.Item;


public interface IDBLayer{
	ArrayList<Item> search(String query);
	InputStream getDatabase();
	void setDatabase(InputStream pathToFile);
}