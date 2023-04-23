package pt.isec.pa.tinypac.gameengine.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Pinky extends Client{

    Pinky(Direction direction){
        super(direction, 'P');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {}
}
