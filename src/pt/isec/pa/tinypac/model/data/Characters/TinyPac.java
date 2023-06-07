package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;

import java.util.Random;

import static pt.isec.pa.tinypac.model.data.Characters.TinyPac.Direction.randomDirection;

public class TinyPac extends Generic {
    public static final char SYMBOL = 'T';

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        private static final Random rd = new Random();

        public static Direction randomDirection()  {
            Direction[] directions = values();
            return directions[rd.nextInt(directions.length)];
        }
    }

    private Direction direction;



    public TinyPac(Board board) {
        super(board);
        this.direction = randomDirection();
        System.out.println(direction);
    }


    @Override
    public void evolve() {

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    @Override
    public char getSymbol() { return SYMBOL; }

}