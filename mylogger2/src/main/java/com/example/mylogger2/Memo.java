package com.example.mylogger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Memo extends AppCompatActivity {

    Position p = new Position();
    String content = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        final EditText editText = (EditText) findViewById(R.id.MemoText);

        Spinner spi = (Spinner) findViewById(R.id.spinner);

        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                p.setPosition(position);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button Save = (Button) findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                content = editText.getText().toString();
                String purposes;
                int position = p.getPosition();

                if(position==0)
                {
                    purposes="영화";
                }
                else if(position==1)
                {
                    purposes="외식";
                }
                else if(position==2)
                {
                    purposes="오버워치";
                }
                else if(position==3)
                {
                    purposes="학교";
                }
                else if(position==4)
                {
                    purposes="공부";
                }
                else
                {
                    purposes="휴식";
                }

                final DataBase db = new DataBase(getApplicationContext(), "MyLogger2.db", null, 1);

                final Intent intent = getIntent();
                final double latitude = intent.getExtras().getDouble("latitude");
                final double longitude = intent.getExtras().getDouble("longitude");

                db.insert(purposes, content, latitude, longitude, position);

                Intent intent1 = new Intent(getApplicationContext(), ShowDB.class);
                startActivity(intent1);
            }
        });
    }
}

class Position{

    private int position;

    public void setPosition(int position){
        this.position=position;
    }

    public int getPosition(){
        return position;
    }
}
