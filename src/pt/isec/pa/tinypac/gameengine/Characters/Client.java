package pt.isec.pa.tinypac.gameengine.Characters;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.IMazeElement;



public abstract class Client implements IGameEngineEvolve, IMazeElement {
    private Direction direction;
    private char symbol;
    public enum Direction {UP, DOWN, LEFT, RIGHT}


    Client(Direction direction, char symbol) {
        this.direction = direction;
        this.symbol = symbol;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
