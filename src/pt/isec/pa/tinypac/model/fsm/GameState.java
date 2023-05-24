package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.states.Menu;
import pt.isec.pa.tinypac.model.fsm.states.Game;
import pt.isec.pa.tinypac.model.fsm.states.Top5;

public enum GameState {

    MENU, GAME, TOP_5;

    static IGameState createState(GameState type,GameContext context, Maze maze) {
        return switch (type) {
            case MENU -> new Menu(context,maze);
            case GAME -> new Game(context,maze);
            case TOP_5 -> new Top5(context,maze);
        };
    }
}
