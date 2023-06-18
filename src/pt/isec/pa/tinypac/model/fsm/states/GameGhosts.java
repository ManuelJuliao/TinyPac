package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * This class represents the GameGhosts state
 */
public class GameGhosts extends GameStateAdapter {
    /**
     * Constructs a GameGhosts object with the given context and board
     * @param context game context
     * @param board game map
     */
    public GameGhosts(GameContext context, Board board) {
        super(context,board);
    }
    /**
     * Returns state
     * @return state
     */
    @Override
    public GameState getState() {
        return GameState.GAME_GHOSTS;
    }

    /**
     * Changes state to INVERT_GHOSTS when power up is activated
     */
    @Override
    public void powerUp(){
        changeState(GameState.INVERT_GHOSTS);
    }

    /**
     * Changes state to WIN when win condition is met
     */
    @Override
    public void win() {
        changeState(GameState.WIN);
    }
    /**
     * Changes state to END when the game ends
     */
    @Override
    public void end() {
        changeState(GameState.GAME);
    }
    /**
     * Changes state to LOSE when lose condition is met
     */
    @Override
    public void lose() {
        changeState(GameState.LOSE);
    }
    /**
     * Changes state to PAUSE when the game is paused
     */
    @Override
    public void pause() {
        changeState(GameState.PAUSE);
    }
    /**
     * Changes state to LOSE when TinyPac dies
     */
    @Override
    public void die() {
        changeState(GameState.LOSE);
    }


}
