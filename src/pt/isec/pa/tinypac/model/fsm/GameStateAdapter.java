package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Board;

/**
 * This abstract class represents the GameStateAdapter
 */
public abstract class GameStateAdapter implements IGameState {
    protected GameContext context;
    protected Board board;

    /**
     * Constructs a GameStateAdapter object with the given context and board
     * @param context game context
     * @param board game map
     */
    protected GameStateAdapter(GameContext context, Board board){
        this.context = context;
        this.board = board;
    }
    /**
     * Starts the game
     */
    @Override
    public void startGame() {}
    /**
     * Activate power up
     */
    @Override
    public void powerUp() {}
    /**
     * Deactivate power up
     */
    @Override
    public void powerDown() {}
    /**
     * Win game
     */
    @Override
    public void win() {}
    /**
     * Lose game
     */
    @Override
    public void lose() {}
    /**
     * Pause game
     */
    @Override
    public void pause() {}
    /**
     * End game
     */
    @Override
    public void end() {}
    /**
     * Resume game
     */
    public void resume(){}
    /**
     * Kill TinyPac
     */
    public void die(){}
    /**
     * Changes state given the new state
     * @param newState new state
     */
    protected void changeState(GameState newState) {
        context.changeState(GameState.createState(newState,context,board));
    }
}
