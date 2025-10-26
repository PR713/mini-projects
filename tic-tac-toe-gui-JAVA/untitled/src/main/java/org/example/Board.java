package org.example;


public class Board {

    private final char[][] board = new char[3][3];
    private final int[] winningCombination = new int[3];

    public Board(){
        resetBoard();
    }

    public void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < 3; i++){
            winningCombination[i] = -1;
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
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' '){
                winningCombination[0] = 3 * i;
                winningCombination[1] = 3 * i + 1;
                winningCombination[2] = 3 * i + 2;
                return board[i][0];
            }
        }
        for (int i = 0; i < 3; i++){
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' '){
                winningCombination[0] = i;
                winningCombination[1] = i + 3;
                winningCombination[2] = i + 6;
                return board[0][i];
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' '){
            winningCombination[0] = 0;
            winningCombination[1] = 4;
            winningCombination[2] = 8;
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' '){
            winningCombination[0] = 2;
            winningCombination[1] = 4;
            winningCombination[2] = 6;
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

    public int[] getWinningCombination(){
        return winningCombination;
    }

    public char getCell(int row, int column){
        return board[row][column];
    }

    public void setCell(int row, int column, char sign){
        board[row][column] = sign;
    }
}
