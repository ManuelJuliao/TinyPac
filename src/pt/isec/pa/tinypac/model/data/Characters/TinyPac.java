package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;

public class TinyPac extends Client {

    public TinyPac(Direction direction) {
        super(direction, 'T');
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime){

    }
}