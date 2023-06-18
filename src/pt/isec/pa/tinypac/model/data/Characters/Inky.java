package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.PhantomCave;
import pt.isec.pa.tinypac.model.data.Elements.PhantomPortal;
import pt.isec.pa.tinypac.model.data.Elements.Wall;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import java.util.ArrayList;
import java.util.List;
import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.*;
/**
 * This class represents the ghost Inky, derived from Generic
 */
public class Inky extends Generic {
    public static final char SYMBOL = 'I';
    private Direction direction;
    List<Board.Position> lst; // saves path
    /**
     * This enum represents the 4 corners of the board
     */
    public enum Corner {
        UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT;
    }
    private Pinky.Corner corner = Pinky.Corner.DOWN_RIGHT;
    boolean first = true;
    Board.Position myPos = null;
    /**
     * Constructs a new Inky object with the given board and spawn
     * @param board the game map
     * @param spawn ghost portal
     */
    public Inky(Board board, IMazeElement spawn){
        super(board, spawn);
        lst = new ArrayList<>();
        this.direction = randomDirection();
    }
    /**
     * Method responsible for the ghost movement
     */
    @Override
    public  void evolve() {
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
                        newPos = move(corner, myPos, emptyCells);
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
                    if(aux instanceof Wall ||aux instanceof PhantomCave || aux instanceof Generic){
                        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                        if(emptyCells.size()==0)
                            break;
                        newPos = move(corner, myPos, emptyCells);
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
                    if(aux instanceof Wall ||aux  instanceof PhantomCave || aux instanceof Generic){
                        List<Board.Position> emptyCells = board.getAdjacentEmptyCells(myPos.y(), myPos.x());
                        if(emptyCells.size()==0)
                            break;
                        newPos = move(corner, myPos, emptyCells);
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
                        newPos = move(corner, myPos, emptyCells);
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
     * Method responsible for giving a new direction to the ghost considering the corner it is moving towards and its distance to it
     * @param corner current corner ghost is moving toward
     * @param myPos current position
     * @param emptyCells available cells
     * @return new position
     */
    private Board.Position move(Pinky.Corner corner, Board.Position myPos, List<Board.Position> emptyCells){ // conditional movement
        Board.Position aux = emptyCells.get(0);
        switch (corner){
            case UP_RIGHT:
                if(emptyCells.size()>1) {
                    if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                        corner = Pinky.Corner.UP_LEFT;
                    else{
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) &&
                                    (emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() == aux.y()) ||
                                    (emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() < aux.y()) ||
                                    (emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() < aux.y()))// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    }
                }
                else{
                    aux = emptyCells.get(0);
                }
                break;
            case UP_LEFT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Pinky.Corner.DOWN_RIGHT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) &&
                                    (emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() == aux.y() )||
                                    (emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() < aux.y()) ||
                                    (emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() < aux.y()))// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }
                break;
            case DOWN_RIGHT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Pinky.Corner.DOWN_LEFT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) &&
                                    (emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() == aux.y()) ||
                                    (emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() > aux.y()) ||
                                    (emptyCells.get(i).x() > aux.x() && emptyCells.get(i).y() > aux.y()))// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }
                break;
            case DOWN_LEFT:
                if(emptyCells.size()>1 && lst.size()>2) {
                    do {
                        if(myPos.y() <= 0.1*board.getHeight() && board.getWidth() - myPos.x() <= 0.1*board.getWidth())
                            corner = Pinky.Corner.UP_RIGHT;
                        for(int i = 1; i < emptyCells.size(); i++){
                            if(emptyCells.get(i) != lst.get(lst.size()-2) &&
                                    (emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() == aux.y() )||
                                    (emptyCells.get(i).x() == aux.x() && emptyCells.get(i).y() > aux.y()) ||
                                    (emptyCells.get(i).x() < aux.x() && emptyCells.get(i).y() > aux.y()))// higher x and lower y
                                aux = emptyCells.get(i);
                        }
                    } while (aux == lst.get(lst.size()-2)); // size -1 = current pos
                }
                break;
        }
        return aux;
    }
    /**
     * Method responsible for the movement when the POWER UP is active
     */
    @Override
    public void evolveInvert(){
        myPos = board.getPositionOf(this);
        if(myPos == lst.get(0))
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
    public char getSymbol() {
        return SYMBOL;
    }

}
