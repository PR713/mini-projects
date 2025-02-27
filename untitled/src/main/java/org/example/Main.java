package org.example;


import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Application.launch(GameApp.class, args);
        //^ analogia do private void newGame(){
        //        GameApp gameApp = new GameApp();
        //        try {
        //            gameApp.start(new Stage());
        //        } catch (Exception e) {
        //            e.printStackTrace(); }}

        System.out.println("Welcome to Tic Tac Toe!");
        char playerSign;
        do {
            System.out.print("Choose your sign: X or O : ");
            playerSign = System.console().readLine().charAt(0);
            if (playerSign != 'X' && playerSign != 'O') {
                System.out.print("Invalid sign. ");
            }
        } while (playerSign != 'X' && playerSign != 'O');

        Game game = new Game(playerSign);
        game.startGame();

    }
}