package com.example.park.updown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UpDown ud = new UpDown();

        final TextView random = (TextView) findViewById(R.id.RandomNumber);

        random.setText("I think the Number is..." + ud.random);

        Button big = (Button)findViewById(R.id.Bigger);
        Button small = (Button)findViewById(R.id.Smaller);
        Button bingo = (Button)findViewById(R.id.Bingo);

        final TextView count = (TextView)findViewById(R.id.Count);

        final EditText editText = (EditText)findViewById(R.id.setNumber);
        Button set = (Button)findViewById(R.id.set);

        final TextView textView = (TextView)findViewById(R.id.setView);
        set.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                String text = editText.getText().toString();
                textView.setText("setNumber = " + text);
            }
        });

        big.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                String value = String.valueOf(ud.Bigger());
                random.setText("I think the Number is..." + value);
            }
        });


        small.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                String value = String.valueOf(ud.Smaller());
                random.setText("I think the Number is..." + value);
            }
        });

        bingo.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View V) {
                String value = String.valueOf(ud.random);
                random.setText("Number is " + value + " ->  BINGO!" );
                String counts = String.valueOf(ud.count);
                count.setText("Count : " + counts);
            }
        });

        Button reset = (Button)findViewById(R.id.Reset);
        reset.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

    }
}
