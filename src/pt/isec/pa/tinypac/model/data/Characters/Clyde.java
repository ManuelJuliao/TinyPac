package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Clyde extends Client{

    public Clyde(Direction direction){
        super(direction, 'C');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        // go straight
        // random direction when it finds wall
        // hunts pac if in line of sight
    }
}
