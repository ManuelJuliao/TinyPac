package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;

public class Inky extends Generic {

    public static final char SYMBOL = 'I';

    public Inky(Board board){

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
