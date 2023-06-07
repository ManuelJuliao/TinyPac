package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;

public class Clyde extends Generic {

    public static final char SYMBOL = 'C';

    public Clyde(Board board){
        super(board);
    }

    @Override
    public void evolve() {

    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }

}
