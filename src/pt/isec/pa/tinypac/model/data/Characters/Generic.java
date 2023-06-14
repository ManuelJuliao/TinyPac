package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.Element;
import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Generic implements IMazeElement {

    protected Board board;


    protected IMazeElement previous = null;

    protected IMazeElement spawnPos = null;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        private static final Random rd = new Random();

        public static Direction randomDirection()  {
            Direction[] directions = values();
            return directions[rd.nextInt(directions.length)];
        }

    }




    Generic(Board board, IMazeElement spawn) {

        this.board = board;
        this.spawnPos = spawn;

    }

    Generic(Board board) {

        this.board = board;

    }

    public abstract void evolve();


    public abstract void evolveInvert();
}
