package org.example;


public class Board {

    private final char[][] board = new char[3][3];

    public Board(){
        resetBoard();
    }

    public void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
    }


    public int checkFreeSpaces(){
        int freeSpaces = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == ' '){
                    freeSpaces++;
                }
            }
        }
        return freeSpaces;
    }

    public char checkWinner(){
        for (int i = 0; i < 3; i++){
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return board[i][0];
            }
        }
        for (int i = 0; i < 3; i++){
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                return board[0][i];
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            return board[0][2];
        } else {
            return ' ';
        }
    }


    public int[] getEmptyCells(){
        int freeSpaces = checkFreeSpaces();
        int[] emptyCells = new int[freeSpaces]; //contains indexes of empty cells from Board array
        int index = 0;
        for (int i = 0; i < 9; i++){
            if (board[i / 3][i % 3] == ' '){
                emptyCells[index] = i;
                index++;
            }
        }

        return emptyCells;
    }

    public char[][] getBoard(){
        return board;
    }


    public char getCell(int row, int column){
        return board[row][column];
    }

    public void setCell(int row, int column, char sign){
        board[row][column] = sign;
    }

    public void printBoard() {
        System.out.printf(" %s  |  %s  |  %s  %n", board[0][0], board[0][1], board[0][2]);
        System.out.println("---------------");
        System.out.printf(" %s  |  %s  |  %s  %n", board[1][0], board[1][1], board[1][2]);
        System.out.println("---------------");
        System.out.printf(" %s  |  %s  |  %s  %n", board[2][0], board[2][1], board[2][2]);
    }
}
