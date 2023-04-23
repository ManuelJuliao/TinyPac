package pt.isec.pa.tinypac.gameengine.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Inky extends Client{

    Inky(Direction direction){
        super(direction, 'I');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {}
}
