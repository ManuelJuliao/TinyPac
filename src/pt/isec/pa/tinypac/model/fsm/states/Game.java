package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class Game extends GameStateAdapter {
    System.Logger logger;
    public Game(GameContext context, Board board) {
        super(context,board);
    }

    @Override
    public GameState getState(){
        return GameState.GAME;
    }

    @Override
    public void startGame(){
        changeState(GameState.GAME_GHOSTS);
    }




}
