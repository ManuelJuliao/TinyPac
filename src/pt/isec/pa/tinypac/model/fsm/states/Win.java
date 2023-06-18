package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;
/**
 * This class represents the Win state
 */
public class Win extends GameStateAdapter {
    /**
     * Constructs a Win object with the given context and board
     * @param context game context
     * @param board game map
     */
    public Win(GameContext context, Board board) {
        super(context, board);
    }
    /**
     * Returns state
     * @return state
     */
    @Override
    public GameState getState() {
        return GameState.WIN;
    }
}
