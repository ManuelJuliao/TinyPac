package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class InvertGhosts extends GameStateAdapter {

    public InvertGhosts(GameContext context,  Board board) {
        super(context, board);
    }

    @Override
    public GameState getState() {
        return GameState.INVERT_GHOSTS;
    }
}
