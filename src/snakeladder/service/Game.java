package snakeladder.service;

import snakeladder.model.Cell;
import snakeladder.model.Dice;
import snakeladder.model.Jump;
import snakeladder.model.Player;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Player winner;
    Deque<Player> players;

    Dice dice;
    public Game(int n){
        winner = null;
        board = new Board(n);
        dice = new Dice(1);
        addPlayers();
    }

    public void startGame(){
        int newPosition;
        int diceNumbers;
        Player playerTurn;
        while (winner == null){
            playerTurn = players.removeFirst();
            System.out.println("playerTurn is :"+playerTurn.getId()+ " and current position : "+playerTurn.getPosition());
            //rolling dice
            diceNumbers = dice.rollDice();
            newPosition = playerTurn.getPosition()+diceNumbers;
            //jum check
            newPosition = jumpCheck(newPosition);
            playerTurn.setPosition(newPosition);
            System.out.println("playerTurn is :"+playerTurn.getId()+ " and new position : "+playerTurn.getPosition());

            if(playerTurn.getPosition() > board.cell.length*board.cell[0].length-1){
                winner = playerTurn;
            }
            players.addLast(playerTurn);
        }
        System.out.println("winner is :"+ winner.getId());
    }

    public int jumpCheck(int newPosition) {
        if(newPosition > board.cell.length*board.cell[0].length-1){
            return newPosition;
        }
        Cell cell = board.getCellNumber(newPosition);
        Jump jump = cell.getJump();
        if(jump != null && jump.getStart() == newPosition){
            String jumpBy = jump.getStart() > jump.getEnd() ? "Snake":"Ladder";
            System.out.println("jump done by :"+jumpBy);
            return jump.getEnd();
        }
        return newPosition;
    }

    public void addPlayers() {
        Player playerA = new Player("A",0);
        Player playerB = new Player("B",0);
        players = new LinkedList<>();
        players.add(playerA);
        players.add(playerB);
    }
}
