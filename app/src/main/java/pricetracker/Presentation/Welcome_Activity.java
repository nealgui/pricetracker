package pricetracker.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pricetracker.R;

public class Welcome_Activity extends AppCompatActivity {

    public Intent setIntent(Context context, String newregion, Class newclass) {
        Intent intent = new Intent(context, newclass);
        intent.putExtra("region", newregion);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);

        Button enter1 = findViewById(R.id.button1);
        Button enter2 = findViewById(R.id.button5);
        Button enter3 = findViewById(R.id.button6);
        Button enter4 = findViewById(R.id.button7);


        enter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = setIntent(getApplicationContext(), "B.C", MainActivity.class);
                startActivity(intent);
            }

        });
        enter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = setIntent(getApplicationContext(), "Manitoba", MainActivity.class);
                startActivity(intent);
            }

        });
        enter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = setIntent(getApplicationContext(), "Alberta", MainActivity.class);
                startActivity(intent);
            }
        });
        enter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = setIntent(getApplicationContext(), "Ontario", MainActivity.class);
                startActivity(intent);
            }
        });
    }
}