package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Board;
import pt.isec.pa.tinypac.model.data.Characters.*;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.states.Game;
import pt.isec.pa.tinypac.utils.Utils;

import java.io.*;



public class BoardManager implements IGameEngineEvolve {




    private Board board;
    private GameContext fsm;


    int counter = 0;
    boolean flag = false;

    private int x,y;

    public BoardManager(){

        fsm = new GameContext();
        innitGame();

    }

    private void innitGame(){
        board = new Board();

    }


    public void startGame(){
        fsm.startGame();
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        if(board.POWER_UP && !flag){
            fsm.activatePower();
            flag = true;
        }
        if(flag && counter++ >= 20) {
            board.POWER_UP = false;
            fsm.deactivatePower();
            counter = 0;
            flag = false;
        }

        if (fsm.getState() == GameState.GAME_GHOSTS){
            if (!board.evolve())
                gameEngine.stop();
        }
        else if (fsm.getState() == GameState.INVERT_GHOSTS){
            if (!board.evolveInvert())
                gameEngine.stop();
        }


    }



    private void fillBoard(Board board, int y0, int x0) {
        File file = new File("Level01.txt");
        int k=0;
        //String text = null;
        char[] text = new char[y0*x0];
        Element aux;

        try {

            FileReader fr=new FileReader(file);   //Creation of File Reader object
            BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
            int c = 0;
            int j = 0;
            while((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                if(character == '\r')
                    continue;
                if (character == '\n')
                    continue;
                text[j] = character;
                j++;
            }
            //System.out.println(text);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int y1 = 0; y1 < y; y1++){
            for (int x1 = 0; x1 < x; x1++){
                aux = selectElement(text[k]);
                board.addElement(aux,y1,x1);
                k++;
            }


        }

    }

    private void fillCharacters() {
        int countCave = 0, countRand = 0, countPhantom = 0;
        IMazeElement spawn = null;
        char[][] aux = board.getBoard();
        for (int y0 = 0; y0 < aux.length; y0++){
            for (int x0 = 0; x0 < aux[y0].length; x0++){
                if(aux[y0][x0] == 'M')
                    board.addCharacter(new TinyPac(board), y0,x0 );
                if (aux[y0][x0] == 'Y'){
                    spawn = board.get(y0,x0);
                }
                else if(aux[y0][x0]== 'y'){
                    countCave++;
                    if(21 - countCave >= 4 - countRand){
                        if(countPhantom == 4)
                            break;
                        if(Utils.randBool()){
                            board.addCharacter(selectPhantom(countPhantom, spawn), y0,x0);
                            countPhantom++;
                        }

                    }
                }

            }
        }
    }

    private Generic selectPhantom(int count, IMazeElement spawn ){
        return switch (count){
            case 0 -> new Blinky(board, spawn);
            case 1 -> new Clyde( board, spawn);
            case 2 -> new Inky(board,spawn);
            case 3 -> new Pinky(board, spawn);
            default -> throw new IllegalStateException("Unexpected value: " + count);
        };
    }


    Element selectElement(char c){
        return switch(c){
            case 'x' -> new Wall();
            case 'W' -> new Warp();
            case 'o' -> new Ball();
           // case 'F' -> new Fruit();
            case 'F' -> null;
            case 'Y' -> new PhantomPortal();
            case 'y' -> new PhantomCave();
            case 'M' -> new PacSpawn();
            case 'O' -> new PowerBall();
            default -> null;
        };
    }

    public void printBoard(char[][] board){ //test
        for (int y0 = 0; y0 < board.length; y0++){
            for (int x0 = 0; x0 < board[y0].length; x0++){
                System.out.print(board[y0][x0]);
            }
            System.out.println();
        }
    }

    public void changeDirection(Generic.Direction dir){
        board.tinyPac.setDirection(dir);
    }

    public char[][] getBoard() {
        return board.getBoard();
    }
}
