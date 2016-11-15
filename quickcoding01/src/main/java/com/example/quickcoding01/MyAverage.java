package com.example.quickcoding01;

public class MyAverage extends MyValues {

    int ave;

    public int getResult() {
        int sum=0;
        for(int i=0; i<arr.length; i++)
            sum += arr[i];
        return ave = sum/arr.length;
    }

}
