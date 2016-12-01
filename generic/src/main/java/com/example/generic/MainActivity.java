package com.example.generic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String string = "= ";
    String number = "= ";
    int count1 = 0;
    int count2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button input = (Button) findViewById(R.id.input);
        Button finish = (Button) findViewById(R.id.finish);
        final TextView tv_N = (TextView) findViewById(R.id.NumberView);
        final TextView tv_S = (TextView) findViewById(R.id.StringView);
        final EditText ed = (EditText) findViewById(R.id.editText);

        final List<String> stringList = new LinkedList<>();
        final List<Integer> intList = new LinkedList<>();

        input.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                String str = String.valueOf(ed.getText());
                try
                {
                    int i = Integer.parseInt(str);
                    intList.add(i);
                    number += intList.get(count1++) + ", ";
                }
                catch(NumberFormatException nf)
                {
                    stringList.add(str);
                    string += stringList.get(count2++) + ", ";
                }
            }
        });

        finish.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                tv_N.setText(string);
                tv_S.setText(number);
            }
        });
    }
}

