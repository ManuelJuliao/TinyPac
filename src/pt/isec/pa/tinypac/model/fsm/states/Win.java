package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class Win extends GameStateAdapter {
    public Win(GameContext context, Maze maze) {
        super(context, maze);
    }

    @Override
    public GameState getState() {
        return GameState.WIN;
    }
}
