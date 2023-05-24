package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Blinky extends Client {

    public Blinky(Direction direction){
        super(direction, 'B');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        // go straight
        // random direction when it finds wall
    }
}
