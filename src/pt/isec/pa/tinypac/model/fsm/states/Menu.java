package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.Characters.*;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

import java.io.*;

import static pt.isec.pa.tinypac.model.data.Characters.Client.Direction.*;

public class Menu extends GameStateAdapter {

    public Menu(GameContext context, Maze maze) {
        super(context,maze);
    }

    @Override
    public void start() {
        fillMaze(maze);
        char[][] board = maze.getMaze();
        printMaze(board);
        changeState(GameState.GAME);
    }

    @Override
    public GameState getState(){
        return GameState.MENU;
    }



    private void fillMaze(Maze maze) {
        File file = new File("Level01.txt");
        int k=0;
        //String text = null;
        char[] text = new char[31*29];
        IMazeElement aux;

        try {

            /* Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                text = sc.next().toCharArray();
                System.out.println(text.length);
                //System.out.println(text);
            }
            sc.close();*/

            FileReader fr=new FileReader(file);   //Creation of File Reader object
            BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
            int c = 0;
            int j = 0;
            while((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
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

        for (int y = 0; y < 31; y++){
            for (int x = 0; x < 29; x++){
                aux = selectElement(text[k]);
                maze.set(y,x,aux);
                k++;
            }


        }

    }

    void printMaze(char[][] board){ //test
        for (int y = 0; y < board.length; y++){
            for (int x = 0; x < board[y].length; x++){
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    IMazeElement selectElement(char c){
        return switch(c){
            case 'x' -> new Wall();
            case 'W' -> new Warp();
            case 'o' -> new Ball();
            case 'F' -> new Fruit();
            case 'Y' -> new PhantomPortal();
            case 'y' -> new PhantomCave();
            case 'M' -> new PacSpawn();
            case 'O' -> new PowerBall();
            default -> new Wall();
        };
    }
}
