package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class Inky extends Client{

    public Inky(Direction direction){
        super(direction, 'I');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        // goes bottom right corner
        // goes bottom left corner
        // goes top left corner
        // goes top right corner
        // threshold 10-15%, if lower changes corner
    }
}
