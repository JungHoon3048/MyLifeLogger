package com.example.phonenumber;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.tel);
        Button go = (Button)findViewById(R.id.go);

        go.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                String pNumber = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pNumber));
                startActivity(intent);
            }
        });
    }
}
