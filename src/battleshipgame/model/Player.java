package battleshipgame.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private String name;
    private List<Ship> fleet;
    private Set<Coordinate> fields;

    public Player(String name){
        this.name = name;
        this.fleet = new ArrayList<>();
        this.fields = new HashSet<>();
    }

    public boolean addShip(Ship ship){
        for (Coordinate c: ship.getPositions()) {
            if (fields.contains(c)) {
                System.out.println("Overlapping ship at: " + c);
                return false;
            }
        }
        fleet.add(ship);
        fields.addAll(ship.getPositions());
        return true;
    }

    public boolean receiveMissile(Coordinate missile){
        for(Ship ship : fleet){
            if(ship.isHit(missile) && ship.isDestroyed(missile)){
                System.out.println(name + "'s ship " + ship.getShipId() + " destroyed");
                fleet.remove(ship);
                return true;
            }
        }
        return false;
    }

    public void printShipStatus() {
        System.out.println(name + " Ship Status:");
        for (Ship ship : fleet) {
            System.out.println("  " + ship.getShipId() + " - Remaining Cells: " + ship.getRemainingCells());
        }
    }


    public boolean hasLost(){
        return fleet.isEmpty();
    }

    public String getName() {
        return name;
    }

    public List<Ship> getFleet() {
        return fleet;
    }

    public Set<Coordinate> getFields() {
        return fields;
    }
}
