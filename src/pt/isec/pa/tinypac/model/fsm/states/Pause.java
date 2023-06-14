package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class Pause extends GameStateAdapter {


    public Pause(GameContext context, Board board) {
        super(context, board);
    }

    @Override
    public GameState getState() {
        return GameState.PAUSE;
    }


    @Override
    public void pause() {
        changeState(GameState.PAUSE);
    }


}
