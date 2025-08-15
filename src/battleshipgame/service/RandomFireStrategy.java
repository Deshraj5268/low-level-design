package battleshipgame.service;

import battleshipgame.model.Coordinate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomFireStrategy implements FireStrategy {
    private final int size;
    private final Set<Coordinate> fired = new HashSet<>();
    private final Random random = new Random();
    public RandomFireStrategy(int size){
        this.size = size;
    }

    @Override
    public Coordinate getNextTarget() {
        while (true) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            Coordinate coordinate = new Coordinate(x, y);
            if(!fired.contains(coordinate)){
                fired.add(coordinate);
                return coordinate;
            }
        }
    }
}
