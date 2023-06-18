package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;
/**
 * This class represents the InvertGhosts state
 */
public class InvertGhosts extends GameStateAdapter {
    /**
     * Constructs a InvertGhosts object with the given context and board
     * @param context game context
     * @param board game map
     */
    public InvertGhosts(GameContext context,  Board board) {
        super(context, board);
    }
    /**
     * Returns state
     * @return state
     */
    @Override
    public GameState getState() {
        return GameState.INVERT_GHOSTS;
    }
    /**
     * Changes state to GAME_GHOSTS when the POWER UP goes down
     */
    @Override
    public void powerDown(){
        changeState(GameState.GAME_GHOSTS);
    }
    /**
     * Changes state to WIN when win condition is met
     */
    @Override
    public void win() {
        changeState(GameState.WIN);
    }
    /**
     * Changes state to GAME when the game ends
     */
    @Override
    public void end() {
        changeState(GameState.GAME);
    }
    /**
     * Changes state to LOSE when lose condition is met
     */
    @Override
    public void die() {
        changeState(GameState.LOSE);
    }
    /**
     * Changes state to PAUSE when the game is paused
     */
    @Override
    public void pause() {
        changeState(GameState.PAUSE);
    }

}
