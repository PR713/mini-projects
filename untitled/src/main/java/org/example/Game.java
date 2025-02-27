package org.example;

public class Game {

    private final Board board;
    private char winner = ' ';
    private final char playerSign;
    private final char computerSign;

    public Game(char playerSign) {
        this.board = new Board();
        this.playerSign = playerSign;
        if (playerSign == 'X') {
            computerSign = 'O';
        } else {
            computerSign = 'X';
        }
    }


    public void startGame() {
        board.resetBoard();

        while (winner == ' ' && board.checkFreeSpaces() > 0) {
            board.printBoard();

            playerMove();
            winner = board.checkWinner();

            if (winner != ' ' || board.checkFreeSpaces() == 0) {
                break;
            }

            computerMove();
            winner = board.checkWinner(); //even move, so there will be always one more space
            //so we don't need to check as above if(...) statement
        }

        board.printBoard();
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

            if (board.getCell(row, column) != ' ') {
                System.out.println("This space is already taken");
            } else {
                board.setCell(row, column, playerSign);
                break;
            }
        } while (true);
    }


    public void computerMove(){
        int[] emptyCells = board.getEmptyCells();
        int randomIndex = (int) (Math.random() * emptyCells.length); //like Math.floor
        int cell = emptyCells[randomIndex];
        board.setCell(cell / 3, cell % 3, computerSign);
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

