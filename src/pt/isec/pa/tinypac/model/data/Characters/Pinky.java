package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.PhantomCave;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.randomDirection;

public class Pinky extends Generic {

    public static final char SYMBOL = 'P';
    private Direction direction;

    List<Board.Position> lst; // saves path
    public enum Corner {
        UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT;
    }

    private Corner corner = Corner.UP_RIGHT;

    boolean first = true;

    Board.Position myPos = null;

    public Pinky(Board board, IMazeElement spawn){

        super(board, spawn);
        lst = new ArrayList<>();
        this.direction = randomDirection();
    }



    @Override
    public void evolve() {
        Board.Position newPos;
        if(first) {
            previous = spawnPos;
            Board.Position portal = board.getPosition(spawnPos);
            if (portal != null) {
                lst.add(portal);
                myPos = board.getPositionOf(this);
                board.add(new PhantomCave(), myPos.y(), myPos.x());
                board.addCharacter(this, portal.y(), portal.x());
                first = false;
            }
        }
        else{
            myPos = board.getPositionOf(this);
            //lst.add(myPos);
            List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
            // conditional movement
            if (emptyCells.size() != 0){
                newPos = move(corner, myPos, emptyCells);
                board.add(previous, myPos.y(), myPos.x());
                previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                board.add(this, newPos.y(), newPos.x());
                lst.add(newPos);
            }

        }

    }

    private Board.Position move(Corner corner, Board.Position myPos, List<Board.Position> emptyCells){ // conditional movement
        Board.Position aux = emptyCells.get(0);
        switch (corner){
            case UP_RIGHT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Corner.DOWN_RIGHT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) && emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() == aux.y() || emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() < aux.y() || emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() < aux.y())// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }
            case UP_LEFT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Corner.DOWN_LEFT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) && emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() == aux.y() || emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() < aux.y() || emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() < aux.y())// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }
            case DOWN_RIGHT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Corner.UP_LEFT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) && emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() == aux.y() || emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() > aux.y() || emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() > aux.y())// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }
            case DOWN_LEFT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Corner.UP_RIGHT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) && emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() == aux.y() || emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() > aux.y() || emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() > aux.y())// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }

        }
        return aux;
    }

    @Override
    public void evolveInvert() {

    }

    @Override
    public char getSymbol() { return SYMBOL;}
}
