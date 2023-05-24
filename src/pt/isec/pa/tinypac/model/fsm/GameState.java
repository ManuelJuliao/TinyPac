package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.states.*;

public enum GameState {

    GAME, GAME_GHOSTS, PAUSE, INVERT_GHOSTS, WIN, LOSE;

    static IGameState createState(GameState type,GameContext context, Maze maze) {
        return switch (type) {
            case GAME -> new Game(context,maze);
            case GAME_GHOSTS -> new GameGhosts(context,maze);
            case PAUSE -> new Pause(context, maze);
            case INVERT_GHOSTS -> new InvertGhosts(context, maze);
            case WIN -> new Win(context, maze);
            case LOSE -> new Lose(context, maze);
        };
    }
}
