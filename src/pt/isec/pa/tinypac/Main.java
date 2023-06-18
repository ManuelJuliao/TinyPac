package pt.isec.pa.tinypac;

import javafx.application.Application;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.gui.MainJFX;
import pt.isec.pa.tinypac.ui.text.UI;


public class Main {

    public static BoardManager gameManager;



    public static void main(String[] args) {

        gameManager = new BoardManager();
//        GameEngine gameEngine = new GameEngine();
//        gameEngine.registerClient(gameManager);
//        gameEngine.start(500);
//        gameEngine.waitForTheEnd();
        Application.launch(MainJFX.class,args);




    }
}