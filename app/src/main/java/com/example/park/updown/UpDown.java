package com.example.park.updown;
import java.util.Random;

public class UpDown {

    Random r = new Random();

    int random = r.nextInt(499);
    int first = 1;
    int last = 500;
    int count;

    public int RandomNumber() {
        return random;
    }
    public int Bingo() {
        return random;
    }
    public int Smaller() {
        last = random+1;
        random = (first+last)/2;
        count++;
        return random;
    }
    public int Bigger() {
        first = random+1;
        random = (first+last)/2;
        count++;
        return random;
    }
}
