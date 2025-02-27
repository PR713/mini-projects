package org.example.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class GamePresenter {

    private char playerSign;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button start;

    @FXML
    private TextField inputField;


    @FXML
    private void onGameStartClicked(ActionEvent actionEvent) {
        if (checkIfInputIsCorrect(inputField.getText())){
            System.out.println("Game started");//..
            //TODO implement
        }

    }

    private boolean checkIfInputIsCorrect(String input) {

        char inputFirstChar = Objects.equals(input, "") ? ' ' : input.charAt(0);

        boolean isReadyToStart = false;

        if (inputFirstChar == 'X' || inputFirstChar == 'O') {
            playerSign = inputFirstChar;
            System.out.println("Player sign is: " + playerSign);
            inputField.setVisible(false);
            start.setVisible(false);
            isReadyToStart = true;
            return true;
        } else {
            System.out.println("Invalid sign. ");
            inputField.clear();
            return false;
        }
    }
}
