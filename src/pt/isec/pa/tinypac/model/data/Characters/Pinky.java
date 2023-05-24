package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Pinky extends Client{

    public Pinky(Direction direction){
        super(direction, 'P');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        // goes top right corner
        // goes bottom right corner
        // goes bottom left corner
        // goes top left corner
        // threshold 10-15%, if lower changes corner
    }
}
