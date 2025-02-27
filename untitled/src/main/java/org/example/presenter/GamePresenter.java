package org.example.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.Game;

import java.util.Objects;

public class GamePresenter {

    private char playerSign;
    private Game game;
    private boolean isStillPlaying = false;
    private int[] winningCombination;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button start;

    @FXML
    private TextField inputField;

    @FXML
    private Label message;

    @FXML
    private void onGameStartClicked(ActionEvent actionEvent) {
        if (checkIfInputIsCorrect(inputField.getText())) {
//            System.out.println("Game started");
            Game game = new Game(playerSign, this);
            this.game = game;
            isStillPlaying = true;
            drawBoard(game.getBoard());
        }
    }


    public void drawBoard(char[][] board) {
        gridPane.getChildren().clear();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button(String.valueOf(board[row][col]));
                button.setMinSize(100, 100);
                button.setFocusTraversable(false);
                button.setStyle("-fx-font-size: 20");

                if (isStillPlaying) {
                    if (board[row][col] == ' ') {
                        int finalRow = row;
                        int finalCol = col;
                        button.setOnAction(event -> handlePlayerMove(finalRow, finalCol));
                    }
                } else { //game ended

                    if (winningCombination[0] != -1) {
                        for (int i = 0; i < 3; i++) {
                            if (winningCombination[i] == row * 3 + col) {
                                System.out.println(winningCombination[i]);
                                button.setStyle(button.getStyle() + "; -fx-background-color: yellow");
                            }
                        }
                    }
                }

                    gridPane.add(button, col, row);
                }
            }
        }


        private void handlePlayerMove ( int row, int col){
            if (game != null) {
                game.play(row, col);
            }
        }


        public void endGame ( char winner){
            isStillPlaying = false;
            this.winningCombination = game.getGameBoard().getWinningCombination();
            drawBoard(game.getBoard());
            message.setVisible(true);
            message.setText("");
            if (winner == playerSign) {
                message.setText("You won!");
            } else if (winner == game.getComputerSign()) {
                message.setText("Computer won!");
            } else {
                message.setText("It's a draw!");
            }
        }


        private boolean checkIfInputIsCorrect (String input){

            char inputFirstChar = Objects.equals(input, "") ? ' ' : input.charAt(0);

            if (inputFirstChar == 'X' || inputFirstChar == 'O') {
                playerSign = inputFirstChar;
//                System.out.println("Player sign is: " + playerSign);
                inputField.setVisible(false);
                inputField.setManaged(false);

                start.setVisible(false);
                start.setManaged(false);

                message.setVisible(false);

                gridPane.setVisible(true);
                gridPane.setManaged(true);
                return true;
            } else {
                inputField.clear();
                inputField.setPromptText("Please enter 'X' or 'O'");
                return false;
            }
        }
    }
