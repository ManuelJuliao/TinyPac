package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Characters.*;
import pt.isec.pa.tinypac.model.data.PlayerScore;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * This class represents the BoardManager
 */
public class BoardManager implements IGameEngineEvolve {
    private Board board;
    private GameContext fsm;
    PropertyChangeSupport pcs;
    public static GameEngine gameEngine;
    int counter = 0;
    boolean flag = false;

    /**
     * Constructs a BoardManager object
     */
    public BoardManager(){
        gameEngine = new GameEngine();
        gameEngine.registerClient(this);
        fsm = new GameContext();
        pcs = new PropertyChangeSupport(this);
        innitGame();
    }

    /**
     * Adds PropertyChangeListener given the listener
     * @param listener listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Initializes the board
     */
    private void innitGame(){
        board = new Board();
    }

    /**
     * Initializes the game
     */
    public void startGame(){
        board.innit();
        fsm.startGame();
        startEngine();
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Starts the game engine
     */
    private void startEngine() {
        gameEngine.start(500);
        //gameEngine.waitForTheEnd();
    }

    /**
     * Ends game and stops the game engine
     */
    public void end() {
        fsm.end();
        gameEngine.stop();
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Method responsible for the evolution of the BoardManager
     * @param gameEngine is the game engine
     * @param currentTime is the machine current time
     */
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        if(board.getHP()==0)
            die();
        else if(board.getMapBalls() == board.getBallCountTotal())
            win();
        else{
            if(board.POWER_UP && !flag){
                fsm.activatePower();
                flag = true;
            }
            if(flag && counter++ >= 20) {
                board.POWER_UP = false;
                fsm.deactivatePower();
                counter = 0;
                flag = false;
            }
            if (fsm.getState() == GameState.GAME_GHOSTS){
                if (!board.evolve())
                    gameEngine.stop();
            }
            else if (fsm.getState() == GameState.INVERT_GHOSTS){
                if (!board.evolveInvert())
                    gameEngine.stop();
            }
        }
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Prints the board to the console
     * @param board game map
     */
    public void printBoard(char[][] board){ //test
        for (int y0 = 0; y0 < board.length; y0++){
            for (int x0 = 0; x0 < board[y0].length; x0++){
                System.out.print(board[y0][x0]);
            }
            System.out.println();
        }
    }

    /**
     * Change TinyPac direction given the new direction
     * @param dir new direction
     */
    public void changeDirection(Generic.Direction dir){
        board.tinyPac.setDirection(dir);
    }

    /**
     * Returns board in char[][] format
     * @return board
     */
    public char[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Returns state
     * @return state
     */
    public GameState getState() {
        return fsm.getState();
    }

    /**
     * Returns points
     * @return points
     */
    public int getPoints(){
        return board.getPoints();
    }

    /**
     * Returns hp
     * @return hp
     */
    public int getHP() { return board.getHP(); }

    /**
     * Pauses game and pauses game engine
     */
    public void pause() {
        fsm.saveState();
        fsm.pause();
        gameEngine.pause();
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Resumes game and resumes game engine
     */
    public void resume(){
        fsm.resume();
        gameEngine.resume();
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Kills TinyPac and stops game engine
     */
    public void die(){
        fsm.die();
        gameEngine.stop();
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Win game and stops game engine
     */
    private void win() {
        fsm.win();
        gameEngine.stop();
        pcs.firePropertyChange(null,null,null);
    }

    /**
     * Check if points enter the top 5
     * @return true or false
     */
    public boolean top5(){
        if(board.checkTop5())
            return true;
        else
            return false;
    }

    /**
     * Saves score given the player name
     * @param playerName name of the player
     */
    public void saveScore(String playerName) {
        board.saveScore(playerName);
    }

    /**
     * Returns top score player list
     * @return player list
     */
    public List<PlayerScore> getScoreList() {
        return board.getPlayerList();
    }
}
