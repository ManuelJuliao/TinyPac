package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.states.Game;


public class GameContext{

     IGameState state;
     Board board;

    public GameContext() {
        state = GameState.createState(GameState.GAME, this, board);
    }


    public void startGame(){

        state.startGame();
    }

    public GameState getState() {
        if (state == null)
            return null;
        return state.getState();
    }



    void changeState(IGameState newState) {
        state = newState;
    }
}
