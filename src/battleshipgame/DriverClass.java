package battleshipgame;

import battleshipgame.service.Game;

public class DriverClass {


    /*
    * model --
    * Coordinate
    * Ship
    * Player
    *
    * Service
    * Game
    * FireStrategy --> RandomFireStrategy
    *
    * */
    public static void main(String[] args) {
        Game game = new Game();
        game.initGame(6);
        game.addShip("SH1", 2, 1, 1, 4, 4);
        game.addShip("SH2", 2, 3, 1, 1, 4);
        game.viewBattleField();
        game.startGame();
    }
}
