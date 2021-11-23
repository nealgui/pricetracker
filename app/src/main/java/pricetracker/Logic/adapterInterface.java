package pricetracker.Logic;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pricetracker.Presentation.cartAdapter;
import pricetracker.Presentation.historyAdapter;
import pricetracker.Presentation.itemAdapter;

public interface adapterInterface {

    RecyclerView setAdapter(cartAdapter adapter, RecyclerView recyclerView, Context context);
    RecyclerView setAdapter(historyAdapter adapter, RecyclerView recyclerView, Context context);
    RecyclerView setAdapter(itemAdapter adapter, RecyclerView recyclerView, Context context);
}
