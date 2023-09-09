package snakeladder.service;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    private Utility(){

    }

    public static int getRandomNumber(int start,int end){
        return ThreadLocalRandom.current().nextInt(start,end+1); // start inclusive , end exclusive
    }
}
