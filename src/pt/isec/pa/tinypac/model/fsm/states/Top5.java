package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class Top5 extends GameStateAdapter {
    public Top5(GameContext context, Maze maze) {
        super(context,maze);
    }

    @Override
    public GameState getState(){
        return GameState.TOP_5;
    }
}
