package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.PhantomCave;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.utils.Utils;
import pt.isec.pa.tinypac.model.data.Elements.*;

import java.util.ArrayList;
import java.util.List;

import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.*;

public class Clyde extends Generic {

    public static final char SYMBOL = 'C';
    private Direction direction;
    List<Board.Position> lst; // saves path

    boolean first = true;

    Board.Position myPos = null;

    public Clyde(Board board, IMazeElement spawn){

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
            List<Board.Position> emptyCellsAux = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
            newPos = findPac(emptyCellsAux,myPos);
            if(newPos == null){
                switch(direction){
                    case UP:
                        if(board.get(myPos.y()-1, myPos.x()) instanceof Wall){
                            List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                            if(emptyCells.size()==0)
                                break;
                            newPos = move(emptyCells);
                            newDirection(myPos,newPos);
                            board.add(previous, myPos.y(), myPos.x());
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }
                        else{
                            board.add(previous, myPos.y(), myPos.x());
                            newPos = new Board.Position(myPos.y()-1, myPos.x());
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }

                    case DOWN:
                        if(board.get(myPos.y()+1, myPos.x()) instanceof Wall){
                            List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                            if(emptyCells.size()==0)
                                break;
                            newPos = move(emptyCells);
                            newDirection(myPos,newPos);
                            board.add(previous, myPos.y(), myPos.x());
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }
                        else{
                            board.add(previous, myPos.y(), myPos.x());
                            newPos = new Board.Position(myPos.y()+1, myPos.x());
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }


                    case LEFT:
                        if(board.get(myPos.y(), myPos.x()-1) instanceof Wall){
                            List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                            if(emptyCells.size()==0)
                                break;
                            newPos = move(emptyCells);
                            newDirection(myPos,newPos);
                            board.add(previous, myPos.y(), myPos.x());
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }
                        else{
                            board.add(previous, myPos.y(), myPos.x());
                            newPos = new Board.Position(myPos.y(), myPos.x()-1);
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }


                    case RIGHT:
                        if(board.get(myPos.y(), myPos.x()+1) instanceof Wall){
                            List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                            if(emptyCells.size()==0)
                                break;
                            newPos = move(emptyCells);
                            newDirection(myPos,newPos);
                            board.add(previous, myPos.y(), myPos.x());
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }
                        else{
                            board.add(previous, myPos.y(), myPos.x());
                            newPos = new Board.Position(myPos.y(), myPos.x()+1);
                            previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                            board.add(this, newPos.y(), newPos.x());
                            lst.add(newPos);
                            break;
                        }


                }
            }
            else{
                board.add(previous, myPos.y(), myPos.x());
                previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                board.add(this, newPos.y(), newPos.x());
                lst.add(newPos);
            }

        }

    }

    private Board.Position move(List<Board.Position> emptyCells){
        Board.Position newPos = null;


        if(emptyCells.size()>1) {
            if(lst.size()>= 2){
                do {
                    int aux = Utils.getNumber(0, emptyCells.size());
                    newPos = emptyCells.get(aux);
                } while (newPos == lst.get(lst.size()-2)); // size -1 = current pos
            }
            else{
                int aux = Utils.getNumber(0, emptyCells.size());
                newPos = emptyCells.get(aux);
            }
        }
        else if(emptyCells.size()==1){
            newPos = emptyCells.get(0); // only 1 available

        }
        return newPos;

    }

    private void newDirection(Board.Position myPos, Board.Position newPos){
        if(myPos.x() == newPos.x() && myPos.y() > newPos.y())
            setDirection(UP);
        else if(myPos.x() == newPos.x() && myPos.y() < newPos.y())
            setDirection(DOWN);
        else if(myPos.x() > newPos.x() && myPos.y() == newPos.y())
            setDirection(LEFT);
        else if(myPos.x() < newPos.x() && myPos.y() == newPos.y())
            setDirection(RIGHT);

    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    private Board.Position findPac(List<Board.Position> emptyCells, Board.Position myPos){
        IMazeElement aux;
        Board.Position auxPos = null;
        for(int i = 0; i < emptyCells.size(); i++){
            if(myPos.y() == emptyCells.get(i).y() && emptyCells.get(i).x() < myPos.x()){ // up check
                for (int j = emptyCells.get(i).x(); j>=0; j--){
                    aux = board.get(myPos.y(), j);
                    if (aux instanceof Wall)
                        break;
                    if (aux instanceof TinyPac)
                        return emptyCells.get(i);
                }
            }
            else if(myPos.y() == emptyCells.get(i).y() && emptyCells.get(i).x() > myPos.x()){ // down check
                for (int j = emptyCells.get(i).x(); j<=board.getHeight(); j++){
                    aux = board.get(myPos.y(), j);
                    if (aux instanceof Wall)
                        break;
                    if (aux instanceof TinyPac)
                        return emptyCells.get(i);
                }
            }
            else if(myPos.x() == emptyCells.get(i).x() && emptyCells.get(i).y() < myPos.y()){ // left check
                for (int j = emptyCells.get(i).y(); j>=0; j--){
                    aux = board.get(j, myPos.x());
                    if (aux instanceof Wall)
                        break;
                    if (aux instanceof TinyPac)
                        return emptyCells.get(i);
                }
            }
            else if(myPos.x() == emptyCells.get(i).x() && emptyCells.get(i).y() > myPos.y()){ // right check
                for (int j = emptyCells.get(i).y(); j<=board.getWidth(); j++){
                    aux = board.get(j, myPos.x());
                    if (aux instanceof Wall)
                        break;
                    if (aux instanceof TinyPac)
                        return emptyCells.get(i);
                }
            }
            else{
                int rand = Utils.getNumber(0, emptyCells.size());
                auxPos = emptyCells.get(rand);

            }
        }
        return auxPos;
    }

    @Override
    public void evolveInvert() {

    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }

}
