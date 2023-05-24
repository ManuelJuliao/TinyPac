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
        registerClients(gameEngine);
        gameEngine.start(500);

    }

    public void registerClients(GameEngine gameEngine){      // instance clients and register
        TinyPac pac = new TinyPac(UP);
        gameEngine.registerClient(pac);
        Blinky blinky = new Blinky(LEFT);
        gameEngine.registerClient(blinky);
        Clyde clyde = new Clyde(RIGHT);
        gameEngine.registerClient(clyde);
        Inky inky = new Inky(DOWN);
        gameEngine.registerClient(inky);
        Pinky pinky = new Pinky(UP);
        gameEngine.registerClient(pinky);

    }
}
