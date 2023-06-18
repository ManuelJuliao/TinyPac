package pt.isec.pa.tinypac.model.fsm;


import pt.isec.pa.tinypac.model.data.Board;

/**
 * This class represents the GameContext
 */
public class GameContext{
     IGameState state;
     public IGameState pauseState;
     Board board;
    /**
     * Constructs a GameContext object
     */
    public GameContext() {
        state = GameState.createState(GameState.GAME, this, board);
    }

    /**
     * Starts the game
     */
    public void startGame(){
        state.startGame();
    }

    /**
     * Ends the game
     */
    public void end() {
        state.end();
    }

    /**
     * Activate power up
     */
    public void activatePower(){
        state.powerUp();
    }

    /**
     * Deactivate power up
     */
    public void deactivatePower(){
        state.powerDown();
    }

    /**
     * Returns state
     * @return state
     */
    public GameState getState() {
        if (state == null)
            return null;
        return state.getState();
    }
    /**
     * Changes state given the new state
     * @param newState new state
     */
    void changeState(IGameState newState) {
        state = newState;
    }

    /**
     * Pauses game
     */
    public void pause() {
        state.pause();
    }

    /**
     * Resumes game
     */
    public void resume(){
        state.resume();
    }

    /**
     * Saves state
     */
    public void saveState() {
        this.pauseState = state; // save current state for unpause
    }

    /**
     * Kills TinyPac
     */
    public void die() {
        state.die();
    }

    /**
     * Win game
     */
    public void win() {
        state.win();
    }
}
