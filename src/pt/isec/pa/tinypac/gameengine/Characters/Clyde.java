package pt.isec.pa.tinypac.gameengine.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Clyde extends Client{

    Clyde(Direction direction){
        super(direction, 'C');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {}
}
