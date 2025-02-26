package org.example;

import java.util.Objects;

public class Game {

    private final Board board;
    private String winner = "";
    private final char playerSign;

    public Game(char playerSign) {
        Board board = new Board();
        this.board = board;
        this.playerSign = playerSign;
        board.printBoard();
    }


    public void startGame() {
        board.resetBoard();

        while (Objects.equals(winner, "") && board.checkFreeSpaces() > 0) {
            board.printBoard();


        }
    }


    public void playerMove() {
        int row;
        int column;
        
        do {
            System.out.println("Enter row (1-3): ");
            row = Integer.parseInt(System.console().readLine());
            row--;
            System.out.println("Enter column (1-3): ");
            column = Integer.parseInt(System.console().readLine());
            column--;

            if (board.getCell(row, column) != ' ') {
                System.out.println("This space is already taken");
            } else {
                board.setCell(row, column, playerSign);
                break;
            }
        } while (board.getCell(row, column) != ' ');
    }
}
