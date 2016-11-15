package com.example.quickcoding01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Minimum = (Button) findViewById(R.id.min);
        Button Average = (Button) findViewById(R.id.ave);

        final TextView showMinimum = (TextView) findViewById(R.id.showMinimum);
        final TextView showAverage = (TextView) findViewById(R.id.showAverage);

        Minimum.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                MyMinimum minimum = new MyMinimum();
                String m = String.valueOf(minimum.getResult());
                showMinimum.setText("Minimum : "+m);
            }
        });

        Average.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                MyAverage average = new MyAverage();
                String a = String.valueOf(average.getResult());
                showAverage.setText("Average : "+a);
            }
        });
    }
}
