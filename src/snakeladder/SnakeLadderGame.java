package snakeladder;

import snakeladder.service.Game;

public class SnakeLadderGame {

    public static void main(String[] args) {
        Game game = new Game(5);
        game.startGame();
    }
}
