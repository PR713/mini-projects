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
