package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * This class represents the Game state
 */
public class Game extends GameStateAdapter {
    /**
     * Constructs a Game object with the given context and board
     * @param context game context
     * @param board game map
     */
    public Game(GameContext context, Board board) {
        super(context,board);
    }

    /**
     * Returns state
     * @return state
     */
    @Override
    public GameState getState(){
        return GameState.GAME;
    }

    /**
     * Changes state to GAME_GHOSTS when the game starts
     */
    @Override
    public void startGame(){
        changeState(GameState.GAME_GHOSTS);
    }

}
