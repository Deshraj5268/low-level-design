package snakeladder.model;

import snakeladder.service.Utility;

public class Dice {

    private int diceCount;

    private final int min=1;
    private final int max = 6;

    public Dice(int n){
        this.diceCount = n;
    }

    public int rollDice(){
        int totalSum = 0;
        int diceIncrement=0;
        while (diceIncrement < diceCount){
            totalSum += Utility.getRandomNumber(min,max);// max exclusive
            diceIncrement++;
        }
        return totalSum;
    }
}
