package org.example;

import org.example.presenter.GamePresenter;

public class Game {

    private final Board gameBoard;
    private char winner = ' ';
    private final char playerSign;
    private final char computerSign;
    private GamePresenter presenter;
    private char[][] board;

    public Game(char playerSign, GamePresenter presenter) {
        this.gameBoard = new Board();
        this.board = gameBoard.getBoard();
        this.playerSign = playerSign;
        this.presenter = presenter;
        if (playerSign == 'X') {
            computerSign = 'O';
        } else {
            computerSign = 'X';
        }
    }


    public void play(int row, int col) {

        playerMove(row, col);
        winner = gameBoard.checkWinner();

        if (winner != ' ' || gameBoard.checkFreeSpaces() == 0){
            presenter.endGame(winner);
            return;
        }


        computerMove();
        winner = gameBoard.checkWinner();

        if (winner != ' ' || gameBoard.checkFreeSpaces() == 0){
            presenter.endGame(winner);
            return;
        }

        presenter.drawBoard(board);
    }


    public void playerMove(int row, int col) {
        if (gameBoard.getCell(row, col) == ' ') {
            gameBoard.setCell(row, col, playerSign);
        }
    }


    public void computerMove(){
        int[] emptyCells = gameBoard.getEmptyCells();
        int randomIndex = (int) (Math.random() * emptyCells.length); //like Math.floor
        int cell = emptyCells[randomIndex];
        gameBoard.setCell(cell / 3, cell % 3, computerSign);
    }


    public char[][] getBoard() {
        return board;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public char getComputerSign() {
        return computerSign;
    }
}

