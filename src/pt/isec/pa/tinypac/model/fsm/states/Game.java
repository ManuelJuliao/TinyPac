package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.data.Characters.*;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

import static pt.isec.pa.tinypac.model.data.Characters.Client.Direction.*;

public class Game extends GameStateAdapter {
    public Game(GameContext context, Maze maze) {
        super(context,maze);
    }

    @Override
    public GameState getState(){
        return GameState.GAME;
    }

    @Override
    public void startGame(){
        GameEngine gameEngine = new GameEngine();
        gameEngine.start(500);

    }


}
