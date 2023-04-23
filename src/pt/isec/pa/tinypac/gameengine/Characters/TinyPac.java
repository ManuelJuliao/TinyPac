package pt.isec.pa.tinypac.gameengine.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;

public class TinyPac extends Client {

    public TinyPac(Direction direction) {
        super(direction, 'T');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime){

    }
}