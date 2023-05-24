package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Maze;

public abstract class GameStateAdapter implements IGameState {

    protected GameContext context;
    protected Maze maze;

    protected GameStateAdapter(GameContext context, Maze maze){
        this.context = context;
        this.maze = maze;
    }

    public void start(){

    }

    @Override
    public void startGame() {

    }

    protected void changeState(GameState newState) {
        context.changeState(GameState.createState(newState,context,maze));
    }
}
