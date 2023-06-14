package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.UI;


public class Main {

    public static BoardManager gameManager;



    public static void main(String[] args) {

        //GameContext gameContext = new GameContext();
        GameEngine gameEngine = new GameEngine();
        gameManager = new BoardManager();

        UI ui = new UI();
        gameEngine.registerClient(gameManager);
        gameEngine.registerClient(ui);

        gameEngine.start(500);

        gameEngine.waitForTheEnd();


    }
}