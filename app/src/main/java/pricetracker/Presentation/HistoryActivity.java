package pricetracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pricetracker.Logic.adapterInterface;
import pricetracker.Objects.Item;
import pricetracker.R;

public class HistoryActivity extends AppCompatActivity implements adapterInterface{

    Item[] s1;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recyclerView);
        s1 = MainActivity.newLogic.getOrderHistory().toArray(new Item[0]);

        historyAdapter historyAdapter = new historyAdapter(this, s1);
        recyclerView = setAdapter(historyAdapter, recyclerView, this);
    }

    @Override
    public RecyclerView setAdapter(historyAdapter adapter, RecyclerView recyclerView, Context context) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return recyclerView;
    }

    @Override
    public RecyclerView setAdapter(itemAdapter adapter, RecyclerView recyclerView, Context context) {
        return null;
    }

    @Override
    public RecyclerView setAdapter(cartAdapter adapter, RecyclerView recyclerView, Context context) {
        return null;
    }
}