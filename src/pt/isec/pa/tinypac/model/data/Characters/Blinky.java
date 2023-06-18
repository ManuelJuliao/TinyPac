package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.*;
import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.randomDirection;

/**
 * This class represents the ghost Blinky, derived from Generic
 */
public class Blinky extends Generic {
    public static final char SYMBOL = 'B';
    private Direction direction;
    private List<Board.Position> lst; // saves path
    boolean first = true;
    Board.Position myPos = null;
    int counter=0;
    /**
     * Constructs a new Blinky object with the given board and spawn
     * @param board the game map
     * @param spawn ghost portal
     */
    public Blinky( Board board, IMazeElement spawn){
        super(board, spawn);
        this.direction = randomDirection();
        lst = new ArrayList<>();
    }
    /**
     * Method responsible for the ghost movement
     */
    @Override
    public void evolve() {
        Board.Position newPos;
        if(first){
            Board.Position portal = board.getPosition(spawnPos);
            if(portal != null && board.get(portal.y(), portal.x()) instanceof PhantomPortal ) {
                previous = spawnPos;
                addToList(portal);
                myPos = board.getPositionOf(this);
                board.add(new PhantomCave(), myPos.y(),myPos.x());
                board.addCharacter(this, portal.y(), portal.x());
                first = false;
            }
        }
        else{
            myPos = board.getPositionOf(this);
            IMazeElement aux;
            switch(direction){
                case UP:
                    aux = board.get(myPos.y()-1, myPos.x());
                    if(aux instanceof Wall || aux instanceof PhantomCave || aux instanceof Generic){
                        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                        if(emptyCells.size()==0)
                            break;
                        newPos = move(emptyCells);
                        newDirection(myPos,newPos);
                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        newPos = new Board.Position(myPos.y()-1, myPos.x());
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                case DOWN:
                    aux = board.get(myPos.y()+1, myPos.x());
                    if(aux instanceof Wall || aux instanceof PhantomCave || aux instanceof Generic){
                        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                        if(emptyCells.size()==0)
                            break;
                        newPos = move(emptyCells);
                        newDirection(myPos,newPos);
                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        newPos = new Board.Position(myPos.y()+1, myPos.x());
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                case LEFT:
                    aux = board.get(myPos.y(), myPos.x()-1);
                    if(aux instanceof Wall || aux instanceof PhantomCave || aux instanceof Generic){
                        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                        if(emptyCells.size()==0)
                            break;
                        newPos = move(emptyCells);
                        newDirection(myPos,newPos);
                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        newPos = new Board.Position(myPos.y(), myPos.x()-1);
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                case RIGHT:
                    aux = board.get(myPos.y(), myPos.x()+1);
                    if(aux instanceof Wall || aux instanceof PhantomCave || aux instanceof Generic){
                        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                        if(emptyCells.size()==0)
                            break;
                        newPos = move(emptyCells);
                        newDirection(myPos,newPos);
                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        newPos = new Board.Position(myPos.y(), myPos.x()+1);
                        previous = board.get(newPos.y(), newPos.x()); // save element of new pos#
                        board.add(this, newPos.y(), newPos.x());
                        addToList(newPos);
                        break;
                    }
            }
        }
    }

    /**
     * Method responsible for adding the new position to the list of positions for this ghost
     * @param pos current position
     */
    private void addToList(Board.Position pos){
        if(lst.size() == 0)
            lst.add(pos);
        else if(pos.x() != lst.get(lst.size()-1).x() || pos.y() != lst.get(lst.size()-1).y())
            lst.add(pos);
    }

    /**
     * Method responsible for choosing one of the available cells
     * @param emptyCells a list of available empty cells
     * @return chosen position
     */
    private Board.Position move(List<Board.Position> emptyCells){
        Board.Position newPos = null;
        if(emptyCells.size()>1) {
            if(lst.size()>= 2){
                do {
                    int aux = Utils.getNumber(0, emptyCells.size());
                    newPos = emptyCells.get(aux);
                } while (newPos.equals(lst.get(lst.size()-2))); // size -1 = current pos
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
    /**
     * Method responsible for assigning a new direction given the current and new position
     * @param myPos current position
     * @param newPos new position
     */
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
    /**
     * Method responsible for assigning a direction
     * @param newDirection new direction
     */
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }
    /**
     * Method responsible for the movement when the POWER UP is active
     */
    @Override
    public void evolveInvert(){
        myPos = board.getPositionOf(this);
        if(myPos.y() == lst.get(0).y() && myPos.x() == lst.get(0).x())
            evolve();
        else{
            if(lst.size()>1){
                Board.Position newPos = lst.get(lst.size()-2);
                if(!(board.get(newPos.y(), newPos.x()) instanceof Generic)){
                    board.add(previous,myPos.y(), myPos.x());
                    lst.remove(lst.size()-1);
                    board.addCharacter(this, newPos.y(), newPos.x());
                }
            }
        }
    }

    /**
     * Returns SYMBOL
     * @return SYMBOL
     */
    @Override
    public char getSymbol() {return SYMBOL;}
}
