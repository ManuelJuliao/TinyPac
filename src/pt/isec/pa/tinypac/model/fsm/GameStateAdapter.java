package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Maze;

public abstract class GameStateAdapter implements IGameState {

    protected GameContext context;
    protected Board board;


    protected GameStateAdapter(GameContext context, Board board){
        this.context = context;
        this.board = board;
    }


    @Override
    public void startGame() {

    }

    protected void changeState(GameState newState) {
        context.changeState(GameState.createState(newState,context,board));
    }


}
