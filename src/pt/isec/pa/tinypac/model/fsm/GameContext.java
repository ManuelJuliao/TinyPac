package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Maze;


public class GameContext {

    private IGameState state;
    private Maze maze;

    public GameContext() {
        maze = new Maze(31,29);

        state = GameState.createState(GameState.MENU,this,maze);
    }

    public void start(){
        state.start();
    }

    public void startGame(){
        state.startGame();
    }

    public GameState getState() {
        if (state == null)
            return null;
        return state.getState();
    }



    void changeState(IGameState newState) {
        state = newState;
    }
}
