package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.fsm.states.*;

public enum GameState {

    GAME, GAME_GHOSTS, PAUSE, INVERT_GHOSTS, WIN, LOSE;



    static IGameState createState(GameState type, GameContext context, Board board) {
        return switch (type) {
            case GAME -> new Game(context,board);
            case GAME_GHOSTS -> new GameGhosts(context,board);
            case PAUSE -> new Pause(context, board);
            case INVERT_GHOSTS -> new InvertGhosts(context, board);
            case WIN -> new Win(context, board);
            case LOSE -> new Lose(context, board);

        };
    }




}
