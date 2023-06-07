package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.Main;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;

public class UI implements IGameEngineEvolve {

    BoardManager manager;

    public UI() {

        this.manager = Main.gameManager;
        menu();
    }

    private boolean finish = false;




    private void menu() {
        char[][] board1 = manager.getBoard();
        manager.printBoard(board1);
        System.out.println();
        switch (PAInput.chooseOption("TinyPac DEIS-ISEC-IPC",
                "Start Game", "Top 5" , "Sair")) {
            case 1:
                manager.startGame();
                //game();
            case 2, 3:
                finish = true;
        }
    }

    private void game() {
        // todo
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
            char[][] board1 = manager.getBoard();
            manager.printBoard(board1);
    }
}