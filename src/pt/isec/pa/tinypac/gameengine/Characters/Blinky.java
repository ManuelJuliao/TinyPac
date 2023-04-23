package pt.isec.pa.tinypac.gameengine.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;

public class Blinky extends Client {

    Blinky(Direction direction){
        super(direction, 'B');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {}
}
