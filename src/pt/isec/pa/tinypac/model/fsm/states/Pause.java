package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * This class represents the Pause state
 */
public class Pause extends GameStateAdapter {
    /**
     * Constructs a Pause object with the given context and board
     * @param context game context
     * @param board game map
     */
    public Pause(GameContext context, Board board) {
        super(context, board);
    }
    /**
     * Returns state
     * @return state
     */
    @Override
    public GameState getState() {
        return GameState.PAUSE;
    }
    /**
     * Changes state to PAUSE when the game is paused
     */
    @Override
    public void pause() {
        changeState(GameState.PAUSE);
    }
    /**
     * Changes state to the previous state when the game is resumed
     */
    @Override
    public void resume(){
        if(context.pauseState.getState() == GameState.GAME_GHOSTS)
            changeState(GameState.GAME_GHOSTS);
        else
            changeState(GameState.INVERT_GHOSTS);
    }


}
