package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.Element;
import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.*;
import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.RIGHT;
/**
 * This class represents the Ghosts
 * */
public abstract class Generic implements IMazeElement {

    protected Board board;
    protected IMazeElement previous = null;
    protected IMazeElement spawnPos = null;

    /**
     * This enum represents the 4 directions the characters can move
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
        private static final Random rd = new Random();

        /**
         * Returns a random direction
         * @return direction
         */
        public static Direction randomDirection()  {
            Direction[] directions = values();
            return directions[rd.nextInt(directions.length)];
        }

    }

    /**
     * Constructs a new Generic object with the given board and spawn(IMazeElement)
     * @param board
     * @param spawn
     */
    Generic(Board board, IMazeElement spawn) {
        this.board = board;
        this.spawnPos = spawn;
    }

    public abstract void evolve();

    public abstract void evolveInvert();
}
