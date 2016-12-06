package com.example.mylogger2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Memo extends AppCompatActivity implements SensorEventListener {

    Position p = new Position();
    String content = new String();
    Button btn = null;
    ImageView iv = null;
    public static int count = 0;
    int walk = count;

    TextView textView;
    Button buttonReset;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setup();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // 메모 기능
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
                else if(position==5)
                {
                    purposes="휴식";
                }
                else
                {
                    purposes="(이벤트)";
                }

                final DataBase db = new DataBase(getApplicationContext(), "MyLogger2.db", null, 1);

                final Intent intent = getIntent();
                final double latitude = intent.getExtras().getDouble("latitude");
                final double longitude = intent.getExtras().getDouble("longitude");

                db.insert(purposes, content, latitude, longitude, position, walk);

                Intent intent1 = new Intent(getApplicationContext(), ShowDB.class);
                startActivity(intent1);
            }
        });

        textView = (TextView) findViewById(R.id.Steps);
        buttonReset = (Button) findViewById(R.id.button_Reset);

        textView.setText("You Walked " + walk + " Steps!!!");
    }

    // 만보계 기능
    public void onStart() {
        super.onStart();
        if (sensor != null)
            sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > 800) {
                    textView.setText("You Walked " + (++walk) + " Steps!!!");
                }

                lastX = event.values[SensorManager.DATA_X];
                lastY = event.values[SensorManager.DATA_Y];
                lastZ = event.values[SensorManager.DATA_Z];
            }
        }
    }

    @Override
    public void onAccuracyChanged (Sensor sensor, int accuracy) {
    }

    public void onClick_Reset (View v) {
        walk = 0;
        textView.setText("You Walked " + walk + " Steps!!!");
    }

    // 카메라 기능
    private void setup() {
        btn = (Button)findViewById(R.id.btn);
        iv = (ImageView)findViewById(R.id.iv);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        iv.setImageURI(data.getData());
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


