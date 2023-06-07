package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.Element;
import pt.isec.pa.tinypac.model.data.Elements.PhantomCave;
import pt.isec.pa.tinypac.model.data.Elements.PhantomPortal;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Blinky extends Generic {

    public static final char SYMBOL = 'B';

    List<Board.Position> lst; // saves path

    boolean first = true;

    Board.Position myPos = null;

    public Blinky( Board board, IMazeElement element){

        super(board, element);
        lst = new ArrayList<>();

    }

    @Override
    public void evolve() {
        if(first){
            previous = spawnPos;
            Board.Position portal = board.getPosition(spawnPos);
            myPos = board.getPositionOf(this);
            board.add(new PhantomCave(), myPos.y(),myPos.x());
            board.addCharacter(this, portal.y(), portal.x());
            first = false;
        }
        myPos = board.getPositionOf(this);
        //lst.add(myPos);
        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
        int aux = Utils.getNumber(0,emptyCells.size());
        Board.Position newPos = emptyCells.get(aux);
        board.add(previous, myPos.y(), myPos.x());
        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
        board.add(this, newPos.y(), newPos.x());
        lst.add(newPos);


        //board.addElement((Element) previous, myPos.y(), myPos.x());
        //board.addCharacter(this, myPos.y()+1, myPos.x()+1);

    }

    @Override
    public char getSymbol() {return SYMBOL;}
}
