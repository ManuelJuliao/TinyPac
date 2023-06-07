package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class GameGhosts extends GameStateAdapter {

    public GameGhosts(GameContext context, Board board) {
        super(context,board);
    }

    @Override
    public GameState getState() {
        return GameState.GAME_GHOSTS;
    }
}
