package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.*;
import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.randomDirection;

public class Blinky extends Generic {

    public static final char SYMBOL = 'B';
    private Direction direction;

    List<Board.Position> lst; // saves path

    boolean first = true;

    Board.Position myPos = null;

    int counter=0;

    public Blinky( Board board, IMazeElement spawn){

        super(board, spawn);
        this.direction = randomDirection();
        lst = new ArrayList<>();

    }

    @Override
    public void evolve() {
        Board.Position newPos;

        if(first){
            previous = spawnPos;
            Board.Position portal = board.getPosition(spawnPos);
            if(portal != null){
                lst.add(portal);
                myPos = board.getPositionOf(this);
                board.add(new PhantomCave(), myPos.y(),myPos.x());
                board.addCharacter(this, portal.y(), portal.x());
                first = false;
            }

        }
        else{
            myPos = board.getPositionOf(this);
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
            //lst.add(myPos);

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

    @Override
    public void evolveInvert(){
        counter++; // game engine interval is .5 sec -> 10 sec powerup = 20 iterations

        //TODO movement -> move to lst.size -1 and remove that position


        if (counter >= 20 || board.getPositionOf(this) == lst.get(0)) // if times up or it is already in the cave
            board.POWER_UP = false;
    }

    @Override
    public char getSymbol() {return SYMBOL;}
}
