package com.example.quickcoding01;

public class MyMinimum extends MyValues {

    public int getResult() {
        int mini = arr[0];
        for(int i=0; i<arr.length; i++) {
            if(arr[i]<mini) {
                mini = arr[i];
            }

        }
        return  mini;
    }
}
