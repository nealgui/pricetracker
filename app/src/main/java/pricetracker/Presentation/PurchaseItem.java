package pricetracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;

import pricetracker.R;

public class PurchaseItem extends AppCompatActivity {

    TextView title, desciption, price, stock, store, review;
    String data1, data2, data3, data4 = "", data5, data6, data7, data8, data9, data10,data11, data12,data13;
    ImageView sampleImg;
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_item);
        title = findViewById(R.id.title);
        desciption = findViewById(R.id.description);
        price = findViewById(R.id.price);

        stock = findViewById(R.id.stock);
        store = findViewById(R.id.store);
        sampleImg = findViewById(R.id.sampleImg);
        graphView = (GraphView) findViewById(R.id.graph);
        review = findViewById(R.id.textView2);
        getData();
        setData();
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int newStock = MainActivity.newLogic.newStock();
                if (newStock > -1) {
                    MainActivity.newLogic.addToCart(newStock);
                    stock.setText("stock: " + newStock);
                    Toast.makeText(getBaseContext(), "ADDED TO CART ! ",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(), "Nothing available (will be restocked when you next search)",
                            Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i < MainActivity.newLogic.getCart().size(); i++)
                    System.out.println(MainActivity.newLogic.getCart().get(i) + "\n");
                System.out.println(MainActivity.newLogic.getCart().size());
            }

        });
        Button button2 = findViewById(R.id.butButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int newStock = MainActivity.newLogic.newStock();
                if (newStock > -1) {
                    MainActivity.newLogic.updateStock(newStock);
                    MainActivity.newLogic.addToHistoryDir();
                    stock.setText("stock: " + newStock);
                    Toast.makeText(getBaseContext(), "You have bought it ! ",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(), "Nothing available (will be restocked when you next search)",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    private void getData() {

        if (getIntent().hasExtra("tit") && getIntent().hasExtra("des") && getIntent().hasExtra("pri") && getIntent().hasExtra("img")&& getIntent().hasExtra("rev")) {
            Intent I = getIntent();
            data1 = I.getStringExtra("tit");
            data2 = I.getStringExtra("des");
            data3 = I.getStringExtra("pri");
            data4 = I.getStringExtra("sto");
            data5 = I.getStringExtra("stor");
            data6 = I.getStringExtra("img");
            data7 = I.getStringExtra("ph1");
            data8 = I.getStringExtra("ph2");
            data9 = I.getStringExtra("ph3");
            data10 = I.getStringExtra("ph4");
            data11 = I.getStringExtra("ph5");
            data12 = I.getStringExtra("ph6");
            data13 = I.getStringExtra("rev");
        } else {
            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(data1);
        desciption.setText(data2);

        price.setText("$" + data3);
        stock.setText("stock: " + data4);
        store.setText("store: " + data5);
        review.setText("Review:" + data13);
        String url = data6;
        Picasso.get().load(data6).into(sampleImg);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, Integer.parseInt(data7)),
                new DataPoint(1, Integer.parseInt(data8)),
                new DataPoint(2, Integer.parseInt(data9)),
                new DataPoint(3, Integer.parseInt(data10)),
                new DataPoint(4, Integer.parseInt(data11)),
                new DataPoint(4, Integer.parseInt(data12))

        });
        graphView.addSeries(series);
    }
}
