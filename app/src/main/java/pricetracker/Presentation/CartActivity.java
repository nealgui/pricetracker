package pricetracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pricetracker.Logic.adapterInterface;
import pricetracker.Logic.logicInterface;
import pricetracker.Objects.Item;
import pricetracker.R;

public class CartActivity extends AppCompatActivity implements adapterInterface {
    Item s1[];
    RecyclerView recyclerView;

    @SuppressLint("SetTextI18n")
    public double[] roundCart(logicInterface logicCase) {
        double roundOff = (double) Math.round(logicCase.totalCartVal() * 100) / 100;
        double roundOff2 = (double) Math.round(logicCase.noTaxTotal() * 100) / 100;
        return new double[]{roundOff, roundOff2};
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        s1 = MainActivity.newLogic.getCart().toArray(new Item[0]);
        recyclerView = findViewById(R.id.recyclerView);
        Button button2 = findViewById(R.id.button2);
        TextView t = findViewById(R.id.totalValue);
        TextView t2 = findViewById(R.id.taxValue);

        double[] vals = roundCart(MainActivity.newLogic);
        t.setText("$" + vals[0]);
        t2.setText("Without Tax: $" + vals[1]);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double totalPrice = MainActivity.newLogic.checkOut();
                System.out.println("$" + totalPrice);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                
                if (totalPrice > 0) {
                    Toast.makeText(getBaseContext(), "You Just Paid! ",
                            Toast.LENGTH_SHORT).show();
                }

                startActivity(intent);

            }

        });

        cartAdapter cartAdapter = new cartAdapter(this, s1);
        recyclerView = setAdapter(cartAdapter, recyclerView, this);
    }

    @Override
    public RecyclerView setAdapter(cartAdapter adapter, RecyclerView recyclerView, Context context) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return recyclerView;
    }

    @Override
    public RecyclerView setAdapter(historyAdapter adapter, RecyclerView recyclerView, Context context) {
        return null;
    }

    @Override
    public RecyclerView setAdapter(itemAdapter adapter, RecyclerView recyclerView, Context context) {
        return null;
    }
}