package pt.isec.pa.tinypac.model.fsm;

/**
 * This class represents the interface IGameState
 */
public interface IGameState {
    /**
     * Starts the game
     */
    void startGame();
    /**
     * Returns state
     */
    GameState getState();
    /**
     * Activate power up
     */
    void powerUp();
    /**
     * Deactivate power up
     */
    void powerDown();
    /**
     * End game
     */
    void end();
    /**
     * Win game
     */
    void win();
    /**
     * Lose game
     */
    void lose();
    /**
     * Pause game
     */
    void pause();
    /**
     * Resume game
     */
    void resume();
    /**
     * Kill TinyPac
     */
    void die();
}
