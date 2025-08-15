package battleshipgame.service;

import battleshipgame.model.Coordinate;
import battleshipgame.model.Player;
import battleshipgame.model.Ship;

public class Game {

    private int boardSize;
    private Player playerA;
    private Player playerB;
    private FireStrategy strategyA;
    private FireStrategy strategyB;

    public void initGame(int n){
        this.boardSize = n;
        this.playerA = new Player("PlayerA");
        this.playerB = new Player("PlayerB");
       // this.fireStrategy = new RandomFireStrategy(boardSize);
        this.strategyA = new SequenceFireStrategy(n, true);
        this.strategyB = new SequenceFireStrategy(n, false);

    }

    public void addShip(String id, int size,int ax, int ay, int bx, int by){
        Coordinate coordinateOfShipA = new Coordinate(ax, ay);
        Coordinate coordinateOfShipB =  new Coordinate(bx, by);
        if (ay + size > boardSize/2 || by+size < boardSize/2) {
            throw new IllegalArgumentException("Ship goes out of player's territory");
        }
        Ship shipA = new Ship("A-"+id, size,coordinateOfShipA);
        Ship shipB = new Ship("B-"+id, size, coordinateOfShipB);
        boolean isPlayerAdded = playerA.addShip(shipA);
        boolean isPlayerBAdded = playerB.addShip(shipB);
        System.out.println("ship "+shipA.getShipId()+" is added for the player ?"+isPlayerAdded);
        System.out.println("ship "+shipB.getShipId()+" is added for the player ?"+isPlayerBAdded);
        System.out.println("shipA at position :"+coordinateOfShipA);
        System.out.println("shipB at position :"+coordinateOfShipB);
    }

    public void startGame() {
        Player current = playerA;
        Player opponent = playerB;

        while (true) {
            Coordinate fire = "PlayerA".equals(opponent.getName()) ? strategyA.getNextTarget() : strategyB.getNextTarget();
            boolean hit = opponent.receiveMissile(fire);
            System.out.println(current.getName() + " fired at " + fire + " : " + (hit ? "Hit" : "Miss"));
            System.out.println("Ships Remaining - PlayerA:" + playerA.getFleet().size() + ", PlayerB:"
                    + playerB.getFleet().size());

           /* playerA.printShipStatus();
            playerB.printShipStatus();
            System.out.println("---------------------------------");*/


            if (opponent.hasLost()) {
                System.out.println("Game Over. " + current.getName() + " wins!");
                break;
            }
            // Swap turns
            Player temp = current;
            current = opponent;
            opponent = temp;
        }
    }

    public void viewBattleField(){
        String [][] grid = new String[this.boardSize][this.boardSize];

        for (Ship shipA : playerA.getFleet()){
            for (Coordinate c : shipA.getPositions()){
                grid[c.getX()][c.getY()] = shipA.getShipId();
            }
        }

        for (Ship shipB : playerB.getFleet()){
            for (Coordinate c : shipB.getPositions()){
                grid[c.getX()][c.getY()] = shipB.getShipId();
            }
        }

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                System.out.print((grid[i][j] != null ? grid[i][j] : "--") + "\t");
            }
            System.out.println();
        }
    }
}
