package battleshipgame.service;

import battleshipgame.model.Coordinate;

import java.util.*;

public class SequenceFireStrategy implements FireStrategy {
   // private final Queue<Coordinate> fireOrder;

    LinkedList<Coordinate> allTargets;
    public SequenceFireStrategy(int gridSize, boolean isPlayerA) {
        allTargets = new LinkedList<>();
        int startCol = !isPlayerA ? gridSize / 2 : 0;
        int endCol = !isPlayerA ? gridSize : gridSize / 2;

        for (int i = 0; i < gridSize; i++) {
            for (int j = startCol; j < endCol; j++) {
                allTargets.add(new Coordinate(i, j));
            }
        }

        Collections.shuffle(allTargets);
        System.out.println("shuffle "+ isPlayerA);
        allTargets.stream().forEach(c-> System.out.print(c.toString() + " , "));
       // this.fireOrder = new LinkedList<>(allTargets);
    }

    @Override
    public Coordinate getNextTarget() {
        if (allTargets.isEmpty()) {
            throw new IllegalStateException("No more coordinates to fire at!");
        }
        return allTargets.removeFirst();
    }
}
