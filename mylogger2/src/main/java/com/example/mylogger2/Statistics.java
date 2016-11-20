package com.example.mylogger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        final DataBase db = new DataBase(getApplicationContext(), "MyLogger2.db", null, 1);

        ArrayList<Integer> arrayList;

        arrayList= db.getPosition();

        int purpose0=0;
        int purpose1=0;
        int purpose2=0;
        int purpose3=0;
        int purpose4=0;
        int purpose5=0;
        int purpose6=0;

        for(int i=0;i<arrayList.size();i++) {
            int position = arrayList.get(i);

            if (position == 0) {
                purpose0++;
            } else if (position == 1) {
                purpose1++;
            } else if (position == 2) {
                purpose2++;
            } else if (position == 3) {
                purpose3++;
            } else if (position == 4) {
                purpose4++;
            } else
                purpose5++;
        }

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(purpose0,0));
        entries.add(new BarEntry(purpose1,1));
        entries.add(new BarEntry(purpose2,2));
        entries.add(new BarEntry(purpose3,3));
        entries.add(new BarEntry(purpose4,4));
        entries.add(new BarEntry(purpose5,5));

        BarDataSet dataset = new BarDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("영화");
        labels.add("외식");
        labels.add("오버워치");
        labels.add("학교");
        labels.add("공부");
        labels.add("휴식");

        BarData data = new BarData(labels, dataset);
        data.setValueTextSize(10);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setDescription("Frequency");
        barChart.setData(data);

        barChart.animateY(2500);
    }

}
