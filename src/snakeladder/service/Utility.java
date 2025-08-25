package snakeladder.service;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    private Utility(){

    }

    public static int getRandomNumber(int start,int end){
        // new Random().nextInt(end) +1 ; ( stat 0 so we can add + 1
        return ThreadLocalRandom.current().nextInt(start,end+1); // start inclusive , end exclusive
    }
}
