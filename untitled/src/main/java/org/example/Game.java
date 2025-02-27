package org.example;

import javafx.application.Platform;
import org.example.presenter.GamePresenter;

public class Game {

    private final Board GameBoard;
    private char winner = ' ';
    private final char playerSign;
    private final char computerSign;
    private GamePresenter presenter;
    private char[][] board;

    public Game(char playerSign, GamePresenter presenter) {
        this.GameBoard = new Board();
        this.board = GameBoard.getBoard();
        this.playerSign = playerSign;
        this.presenter = presenter;
        if (playerSign == 'X') {
            computerSign = 'O';
        } else {
            computerSign = 'X';
        }
    }


    public void startGame() {
//        board.resetBoard();

        while (winner == ' ' && GameBoard.checkFreeSpaces() > 0) {
            Platform.runLater(() -> presenter.drawBoard(board));

            playerMove();
            winner = GameBoard.checkWinner();

            if (winner != ' ' || GameBoard.checkFreeSpaces() == 0) {
                break;
            }

            computerMove();
            winner = GameBoard.checkWinner(); //even move, so there will be always one more space
            //so we don't need to check as above if(...) statement
        }

        Platform.runLater(() -> presenter.drawBoard(board));
        printWinner();
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
            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (GameBoard.getCell(row, column) != ' ') {
                System.out.println("This space is already taken");
            } else {
                GameBoard.setCell(row, column, playerSign);
                break;
            }
        } while (true);
    }


    public void computerMove(){
        int[] emptyCells = GameBoard.getEmptyCells();
        int randomIndex = (int) (Math.random() * emptyCells.length); //like Math.floor
        int cell = emptyCells[randomIndex];
        GameBoard.setCell(cell / 3, cell % 3, computerSign);
    }


    public void printWinner(){
        if (winner == playerSign){
            System.out.println("Congratulations! You won!");
        } else if (winner == computerSign){
            System.out.println("Computer won!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}

