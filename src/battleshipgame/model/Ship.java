package battleshipgame.model;

import java.util.HashSet;
import java.util.Set;

public class Ship {

    private String shipId;
    private int size;
    private Set<Coordinate> positions;

    public Ship(String shipId, int size, Coordinate topLeft){
        this.shipId = shipId;
        this.size = size;
        positions = new HashSet<>();
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                Coordinate position = new Coordinate(topLeft.getX() + i, topLeft.getY() + j);
                positions.add(position);
            }
        }
    }

    public boolean isHit(Coordinate missile){
        return positions.contains(missile);
    }

    public boolean isDestroyed(Coordinate missile){
        positions.remove(missile);
        return positions.isEmpty();
    }

    public int getRemainingCells() {
        return positions.size();
    }


    public String getShipId() {
        return shipId;
    }

    public int getSize() {
        return size;
    }

    public Set<Coordinate> getPositions() {
        return positions;
    }
}
