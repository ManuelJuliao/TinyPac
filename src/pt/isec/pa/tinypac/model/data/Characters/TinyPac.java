package pt.isec.pa.tinypac.model.data.Characters;

import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Elements.*;

import java.util.Random;

import static pt.isec.pa.tinypac.model.data.Characters.Generic.Direction.randomDirection;


public class TinyPac extends Generic {
    public static final char SYMBOL = 'T';

    int count = 0; // debug

    Board.Position myPos = null;



    private Direction direction;



    public TinyPac(Board board) {
        super(board);
        this.direction = randomDirection();
        System.out.println(direction);
    }


    @Override
    public void evolve() {
        myPos = board.getPositionOf(this);
        switch(direction){
            case UP:
               if(board.get(myPos.y()-1, myPos.x()) instanceof Wall)
                   break;
               else if(board.get(myPos.y()-1, myPos.x()) instanceof Ball){
                  board.addPoints(1);
                  board.add(null, myPos.y(), myPos.x());
                  board.add(this, myPos.y()-1, myPos.x());
                   break;
               }
               else if(board.get(myPos.y()-1, myPos.x()) instanceof PowerBall){
                   board.POWER_UP = true;
                   board.add(null, myPos.y(), myPos.x());
                   board.add(this, myPos.y()-1, myPos.x());
                   break;
               }
               else if(board.get(myPos.y()-1, myPos.x()) instanceof Fruit){
                   board.addFruitPoints();
                   board.add(null, myPos.y(), myPos.x());
                   board.add(this, myPos.y()-1, myPos.x());
                   break;
               }
               else if(board.get(myPos.y()-1, myPos.x()) instanceof Generic){
                   board.add(null, myPos.y(), myPos.x());
                   board.add(this, myPos.y()-1, myPos.x());
                   board.die();
                   break;
               }
            case DOWN:
                if(board.get(myPos.y()+1, myPos.x()) instanceof Wall)
                    break;
                else if(board.get(myPos.y()-1, myPos.x()) instanceof Ball){
                    board.addPoints(1);
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y()+1, myPos.x());
                    break;
                }
                else if(board.get(myPos.y()+1, myPos.x()) instanceof PowerBall){
                    board.POWER_UP = true;
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y()+1, myPos.x());
                    break;
                }
                else if(board.get(myPos.y()+1, myPos.x()) instanceof Fruit){
                    board.addFruitPoints();
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y()+1, myPos.x());
                    break;
                }
                else if(board.get(myPos.y()+1, myPos.x()) instanceof Generic){
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y()+1, myPos.x());
                    board.die();
                    break;
                }
            case LEFT:
                if(board.get(myPos.y(), myPos.x()-1) instanceof Wall)
                    break;
                else if(board.get(myPos.y(), myPos.x()-1) instanceof Ball){
                    board.addPoints(1);
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()-1);
                    break;
                }
                else if(board.get(myPos.y(), myPos.x()-1) instanceof PowerBall){
                    board.POWER_UP = true;
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()-1);
                    break;
                }
                else if(board.get(myPos.y(), myPos.x()-1) instanceof Fruit){
                    board.addFruitPoints();
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()-1);
                    break;
                }
                else if(board.get(myPos.y(), myPos.x()-1) instanceof Generic){
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()-1);
                    board.die();
                    break;
                }
            case RIGHT:
                if(board.get(myPos.y(), myPos.x()+1) instanceof Wall)
                    break;
                else if(board.get(myPos.y(), myPos.x()+1) instanceof Ball){
                    board.addPoints(1);
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()+1);
                    break;
                }
                else if(board.get(myPos.y(), myPos.x()+1) instanceof PowerBall){
                    board.POWER_UP = true;
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()+1);
                    break;
                }
                else if(board.get(myPos.y(), myPos.x()+1) instanceof Fruit){
                    board.addFruitPoints();
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()+1);
                    break;
                }
                else if(board.get(myPos.y(), myPos.x()+1) instanceof Generic){
                    board.add(null, myPos.y(), myPos.x());
                    board.add(this, myPos.y(), myPos.x()+1);
                    board.die();
                    break;
                }
        }
    }

    @Override
    public void evolveInvert(){
        evolve();
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    @Override
    public char getSymbol() { return SYMBOL; }

}