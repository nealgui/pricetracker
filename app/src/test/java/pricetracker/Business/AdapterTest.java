package pricetracker.Business;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import pricetracker.Data.IDBLayer;
import pricetracker.Data.ItemStorageSQL;
import pricetracker.Logic.logicLayer;
import pricetracker.Objects.Item;
import pricetracker.Presentation.CartActivity;
import pricetracker.Presentation.HistoryActivity;
import pricetracker.Presentation.cartAdapter;
import pricetracker.Presentation.historyAdapter;
import pricetracker.Presentation.itemAdapter;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AdapterTest {

    Context mContext = mock(Context.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void itemAdapterBaseTest() {
        Item itemOne = new Item.Builder().setName("Fake Item One").setPrice(1.99f).setStore("Store One").setDesc("Description One").setImg("Image1").setStock(1).createItem();
        Item itemTwo = new Item.Builder().setName("Fake Item Two").setPrice(5.95f).setStore("Store Two").setDesc("Description Two").setImg("Image2").setStock(2).createItem();
        ArrayList<Item> starter = new ArrayList<>();
        starter.add(itemOne);
        starter.add(itemTwo);

        itemAdapter itemAd = new itemAdapter(mContext, starter);

        assertEquals(2, itemAd.getItemCount());
        assertEquals("Fake Item One", itemAd.getItems().get(0));
        assertEquals(5.95f, itemAd.getPrices().get(1));
    }

    @Test
    public void AdapterMockTest() {
//        Same for the different types of adapters
        itemAdapter mItemAdapter = mock(itemAdapter.class);
        RecyclerView.ViewHolder viewHolder = null;
        doNothing().when(mItemAdapter).onBindViewHolder(viewHolder, 1);

        mItemAdapter.onBindViewHolder(viewHolder, 1);
        mItemAdapter.onBindViewHolder(viewHolder, 1);
        verify(mItemAdapter, times(2)).onBindViewHolder(viewHolder, 1);
    }

    @Test
    public void cartAdapterBaseTest() {
        Item[] starter = new Item[0];
        cartAdapter cartAd = new cartAdapter(mContext, starter);
        assertEquals(0, cartAd.getItemCount());
    }

    @Test
    public void historyAdapterBaseTest() {
        Item[] starter = new Item[0];
        historyAdapter histAd = new historyAdapter(mContext, starter);
        assertEquals(0, histAd.getItemCount());
    }

    @Test
    public void historyActivityTest() {
        HistoryActivity historyActivity = mock(HistoryActivity.class);
        historyAdapter historyAdapter = mock(pricetracker.Presentation.historyAdapter.class);
        RecyclerView recyclerView = mock(RecyclerView.class);

        historyActivity.setAdapter(historyAdapter, recyclerView, mContext);
    }

    @Test
    public void cartActivityTest() {
        CartActivity cartActivity = new CartActivity();
        IDBLayer newDB = mock(ItemStorageSQL.class);

        logicLayer newlogic = new logicLayer(newDB);
        newlogic.setRegion("B.C");

        Item testItemA = new Item.Builder().setName("dvd").setPrice(54).setStock(10).setStore(null).setDesc(null).createItem();
        newlogic.setCurrProd(testItemA);
        newlogic.addToCart(newlogic.newStock());

        double[] vals = cartActivity.roundCart(newlogic);

        assertEquals(((float) newlogic.taxTotal(testItemA.getProdPrice())), ((float) vals[0]));
        assertEquals(((float) Math.round(testItemA.getProdPrice()) * 100) / 100, ((float) vals[1]));
    }


}

