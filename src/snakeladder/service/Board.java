package snakeladder.service;

import snakeladder.model.Cell;
import snakeladder.model.Jump;

public class Board {

    Cell [][] cell;

    public Board(int n){
        initializeBoard(n,n);
        initializeSnakeAndLadder(cell,15,15);
       /* for (int i=0;i<cell.length;i++) {
            for (int j = 0; j < cell[0].length; j++) {
                if(cell[i][j].getJump() != null) {
                    System.out.print(1 +" "+cell[i][j].getJump().toString());
                }else {
                    System.out.print(0);
                }
                System.out.print(" ");
            }
            System.out.println();
        }*/
    }

    public void initializeSnakeAndLadder(Cell[][] cell, int snakeCount, int ladderCount) {
        int m = cell.length;
        int n = cell[0].length;
        System.out.println("snake positions");
        while (snakeCount > 0) {
            int snakeHead = Utility.getRandomNumber(1, m * n - 1);
            int snakeTail = Utility.getRandomNumber(1, m * n - 1);
            if(snakeTail >= snakeHead){
                continue;
            }
            System.out.println("snakeHead :"+snakeHead +" snakeTail:"+snakeTail);
           Cell snakeCell = fillCellByJump(snakeHead, snakeTail);
            snakeCount--;
        }

        System.out.println("ladder positions");
        while (ladderCount > 0) {
            int ladderStart = Utility.getRandomNumber(1, m * n - 1);
            int ladderEnd = Utility.getRandomNumber(1, m * n - 1);
            if(ladderStart >= ladderEnd){
                continue;
            }
            System.out.println("ladderStart :"+ladderStart +" ladderEnd:"+ladderEnd);
            fillCellByJump(ladderStart, ladderEnd);
            ladderCount--;
        }
    }

    public Cell fillCellByJump(int jumpStart, int jumpEnd) {
        Jump jump = new Jump();
        jump.setStart(jumpStart);
        jump.setEnd(jumpEnd);
        Cell cell = getCellNumber(jumpEnd);
        cell.setJump(jump);
        return cell;
    }

    public Cell getCellNumber(int position) {
        int m = cell.length;
        int n = cell[0].length;
        return cell[position/m][position%n];
    }

    public void initializeBoard(int n,int m) {
        cell = new Cell[n][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                cell[i][j] = new Cell();
            }
        }
    }
}
