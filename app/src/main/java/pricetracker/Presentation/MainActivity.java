package pricetracker.Presentation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import pricetracker.Data.ItemStorageSQL;
import pricetracker.Data.IDBLayer;
import pricetracker.Logic.logicInterface;
import pricetracker.Logic.logicLayer;
import pricetracker.Objects.Item;
import pricetracker.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    itemAdapter adapter;
    TextView TextRegion;
    ArrayList<Item> starter = new ArrayList<>();
    public static logicInterface newLogic = new logicLayer(null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String region = "";

        if(getIntent().getStringExtra("region")!=null)region = getIntent().getStringExtra("region");
        newLogic.setRegion(region);

        IDBLayer newDB = new ItemStorageSQL(MainActivity.this);
        newLogic.setDb(newDB);

        // Set to ids, can be found in activity_main.xml (individual items are linked to id in list_view.xml)
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        TextRegion = findViewById(R.id.regionText);

        // Making link between UI and data (adapters) (new instance of adapter class)
        adapter = new itemAdapter(this, starter);
        recyclerView.setAdapter(adapter);
        TextRegion.setText(newLogic.getRegion());

        // Specifying direction of layout (note, if we use grids, this can be flexible)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Adding line divider between items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // For now, only filters when you click enter
        // Once this is cleaner, we can have filtering as the text changes
        // Will add toast message for when query is not found, etc.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                starter = newLogic.querySearch(starter, newDB, query);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                starter.clear();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_price:
                starter.sort(Comparator.comparing(Item::getProdPrice));
                Toast.makeText(getBaseContext(), "price ! ",
                        Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_a_z:
                starter.sort(Comparator.comparing(Item::getProdName));
                Toast.makeText(getBaseContext(), "A_Z ! ",
                        Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.cart:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                return true;

            case R.id.order:
                intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
