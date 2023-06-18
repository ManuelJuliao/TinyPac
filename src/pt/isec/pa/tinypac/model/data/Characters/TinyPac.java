package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.LEFT;
/**
 * This class represents the TinyPac
 */
public class TinyPac implements IMazeElement {
    public static final char SYMBOL = 'T';
    int count = 0; // debug
    Board.Position myPos = null;
    protected Board board;
    protected IMazeElement previous = null;
    private Generic.Direction direction;
    private boolean warpStatus = false;

    /**
     * Constructs a TinyPac with the given board
     * @param board the game map
     */
    public TinyPac(Board board) {
        this.board = board;
        this.direction = LEFT;
        //this.direction = randomDirection();
        System.out.println(direction);
    }
    /**
     * Method responsible for the TinyPac movement
     * @return true
     */
    public boolean evolve() {
        myPos = board.getPacPos(this);
        if(myPos == null)
            return false;
        if(previous instanceof Warp && !warpStatus){
            toggleWarpStatus();
            Board.Position auxPos = board.getPositionOfWarp(Warp.class);
            board.add(previous, myPos.y(), myPos.x());
            board.add(this, auxPos.y(), auxPos.x());
        }
        else{
            if(previous instanceof Warp){
                toggleWarpStatus();
            }
            switch(direction){
                case UP:
                    if(board.get(myPos.y()-1, myPos.x()) instanceof Wall)
                        break;
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof PowerBall){
                        board.addPoints(10);
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        board.die();
                        break;
                    }
                    else if(board.get(myPos.y()-1,myPos.x()) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y()-1,myPos.x()); //save warp
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y()-1,myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                case DOWN:
                    if(board.get(myPos.y()+1, myPos.x()) instanceof Wall)
                        break;
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof PowerBall){
                        board.addPoints(10);
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()+1, myPos.x());
                        board.die();
                        break;
                    }
                    else if(board.get(myPos.y()+1,myPos.x()) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y()+1,myPos.x()); //save warp
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y()+1,myPos.x()); //save warp
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                case LEFT:
                    if(board.get(myPos.y(), myPos.x()-1) instanceof Wall)
                        break;
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof PowerBall){
                        board.addPoints(10);
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()-1);
                        board.die();
                        break;
                    }
                    else if(board.get(myPos.y(),myPos.x()-1) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y(),myPos.x()-1); //save warp
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y(),myPos.x()-1); //save warp
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                case RIGHT:
                    if(board.get(myPos.y(), myPos.x()+1) instanceof Wall)
                        break;
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof PowerBall){
                        board.addPoints(10);
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()+1);
                        board.die();
                        break;
                    }
                    else if(board.get(myPos.y(),myPos.x()+1) instanceof Warp){
                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y(),myPos.x()+1); //save warp
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y(),myPos.x()+1); //save warp
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
            }
        }
        return true;
    }

    /**
     * Toggles warpStatus variable
     */
    private void toggleWarpStatus() {
        this.warpStatus = !warpStatus;
    }
    /**
     * Method responsible for the movement when the POWER UP is active
     * @return true
     */
    public boolean evolveInvert(){
        myPos = board.getPacPos(this);
        if(myPos == null)
            return false;
        if(previous instanceof Warp && !warpStatus){
            toggleWarpStatus();
            Board.Position auxPos = board.getPositionOfWarp(Warp.class);
            board.add(previous, myPos.y(), myPos.x());
            board.add(this, auxPos.y(), auxPos.x());
        }
        else{
            if(previous instanceof Warp){
                //board.add(previous, myPos.y(), myPos.x());
                //previous = null;
                toggleWarpStatus();
            }
            switch(direction){
                case UP:
                    if(board.get(myPos.y()-1, myPos.x()) instanceof Wall)
                        break;
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof PowerBall){
                        board.addPoints(10);
                        board.countPowerBall();
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()-1, myPos.x()) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        board.eatGhost();
                        break;
                    }
                    else if(board.get(myPos.y()-1,myPos.x()) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y()-1,myPos.x()); //save warp
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y()-1,myPos.x());
                        board.add(this, myPos.y()-1, myPos.x());
                        break;
                    }
                case DOWN:
                    if(board.get(myPos.y()+1, myPos.x()) instanceof Wall)
                        break;
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof PowerBall){
                        board.addPoints(10);
                        board.countPowerBall();
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else if(board.get(myPos.y()+1, myPos.x()) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y()+1, myPos.x());
                        board.eatGhost();
                        break;
                    }
                    else if(board.get(myPos.y()+1,myPos.x()) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y()+1,myPos.x()); //save warp
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y()+1,myPos.x()); //save warp
                        board.add(this, myPos.y()+1, myPos.x());
                        break;
                    }
                case LEFT:
                    if(board.get(myPos.y(), myPos.x()-1) instanceof Wall)
                        break;
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof PowerBall){
                        board.addPoints(10);
                        board.countPowerBall();
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()-1) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()-1);
                        board.eatGhost();
                        break;
                    }
                    else if(board.get(myPos.y(),myPos.x()-1) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y(),myPos.x()-1); //save warp
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y(),myPos.x()-1); //save warp
                        board.add(this, myPos.y(), myPos.x()-1);
                        break;
                    }
                case RIGHT:
                    if(board.get(myPos.y(), myPos.x()+1) instanceof Wall)
                        break;
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof Ball){
                        board.countBall();
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof PowerBall){
                        board.addPoints(10);
                        board.countPowerBall();
                        board.POWER_UP = true;
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof Fruit){
                        board.addFruitPoints();
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else if(board.get(myPos.y(), myPos.x()+1) instanceof Generic){
                        board.add(previous, myPos.y(), myPos.x());
                        board.add(this, myPos.y(), myPos.x()+1);
                        board.eatGhost();
                        break;
                    }
                    else if(board.get(myPos.y(),myPos.x()+1) instanceof Warp){

                        board.add(previous, myPos.y(), myPos.x());
                        previous = board.get(myPos.y(),myPos.x()+1); //save warp
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
                    else{
                        board.add(previous, myPos.y(), myPos.x());
                        previous = null;
                        //previous = board.get(myPos.y(),myPos.x()+1); //save warp
                        board.add(this, myPos.y(), myPos.x()+1);
                        break;
                    }
            }
        }
        return true;
    }
    /**
     * Method responsible for assigning a direction
     * @param newDirection new direction
     */
    public void setDirection(Generic.Direction newDirection) {
        direction = newDirection;
        System.out.println("New Direction: " + direction);
    }
    /**
     * Returns SYMBOL
     * @return SYMBOL
     */
    @Override
    public char getSymbol() { return SYMBOL; }

}