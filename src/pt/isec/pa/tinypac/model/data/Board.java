package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.Characters.*;
import pt.isec.pa.tinypac.model.data.Elements.*;
import pt.isec.pa.tinypac.utils.Utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Board {


    public record Position(int y, int x) {};
    public TinyPac tinyPac;

    private int points;
    private int fruitPoints;

    private int ballCount;
    private int lvl;
    private int hp;

    int height, width;

    Maze maze;

    public boolean POWER_UP = false;

    public Board() {
        checkSize();
        this.maze = new Maze(height,width);
        this.points = 0;
        this.fruitPoints = 25;
        this.hp = 3;
        this.lvl = 1;
        this.ballCount = 0;
        fillBoard(height, width);
        fillCharacters();
    }

    private void checkSize(){
        File file = new File("Level01.txt");
        int rows=0, columns=0;

        try {

            FileReader fr=new FileReader(file);   //Creation of File Reader object
            BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
            int c;
            while((c = br.read()) != -1)         //Read char by Char
            {
                char character = (char) c;          //converting integer to char
                if(character == '\r')
                    continue;
                if (character == '\n') {
                    width = columns;
                    columns = 0;
                    rows++;
                    continue;
                }
                columns++;
            }
            height = rows + 1;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillBoard(int y0, int x0) {
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

        for (int y1 = 0; y1 < height; y1++){
            for (int x1 = 0; x1 < width; x1++){
                aux = selectElement(text[k]);
                addElement(aux,y1,x1);
                k++;
            }


        }

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

    private void fillCharacters() {
        int countCave = 0, countRand = 0, countPhantom = 0;
        IMazeElement spawn = null;
        char[][] aux = getBoard();
        tinyPac = new TinyPac(this);
        for (int y0 = 0; y0 < aux.length; y0++){
            for (int x0 = 0; x0 < aux[y0].length; x0++){
                if(aux[y0][x0] == 'M')
                    addCharacter(tinyPac, y0,x0 );
                if (aux[y0][x0] == 'Y'){
                    spawn = get(y0,x0);
                }
                else if(aux[y0][x0]== 'y'){
                    countCave++;
                    if(21 - countCave >= 4 - countRand){
                        if(countPhantom == 4)
                            break;
                        if(Utils.randBool()){
                            addCharacter(selectPhantom(countPhantom, spawn), y0,x0);
                            countPhantom++;
                        }

                    }
                }

            }
        }
    }

    private Generic selectPhantom(int count, IMazeElement spawn ){
        return switch (count){
            case 0 -> new Blinky(this, spawn);
            case 1 -> new Clyde( this, spawn);
            case 2 -> new Inky(this,spawn);
            case 3 -> new Pinky(this, spawn);
            default -> throw new IllegalStateException("Unexpected value: " + count);
        };
    }


    public void addPoints (int count){
        points += count;
    }

    public void countBall (){

        points += 1;
        ballCount ++;
    }

    public void addFruitPoints() {
        points =+ fruitPoints;
        fruitPoints =+ 25;
    }


    public int getHeight (){return height;}

    public int getWidth (){return width;}

    public void add(IMazeElement aux, int y, int x){maze.set(y,x,aux);}

    public void addCharacter(Generic character, int y, int x) {
        maze.set(y,x,character);
    }

    public void addElement(Element element, int y, int x){maze.set(y,x,element);}

    public void die(){
        hp--;
        //init new maze
    }

    public IMazeElement get(int y,int x) {
       IMazeElement element = maze.get(y,x);
        if (element instanceof Generic character)
            return character;
        return (Element) element;
    }
    public Generic getCharacter(int y,int x) {
        IMazeElement element = maze.get(y,x);
        if (element instanceof Generic character)
            return character;
        return null;
    }

    public Element getElement(int y,int x) {
        IMazeElement aux = maze.get(y,x);
        if (aux instanceof Element element)
            return element;
        return null;
    }

    public Position getPositionOf(Generic character) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == character)
                    return new Position(y,x);
        return null;
    }

    public Position getPosition(IMazeElement element) {
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) == element)
                    return new Position(y,x);
        return null;
    }

//    public List<Position> getAdjacentEmptyCells(int yo, int xo) {
//        List<Position> lst = new ArrayList<>();
//        for (int y = Math.max(0,yo-1); y <= Math.min(height-1,yo+1); y++)
//            for (int x = Math.max(0,xo-1); x <= Math.min(width-1,xo+1); x++)
//                if ((y != yo || x != xo) && !(maze.get(y, x) instanceof Wall))
//                    if(y!=x && !(x>xo && y<yo) && !(x<xo && y>yo))
//                        lst.add(new Position(y , x));
//
//        return lst;
//    }

    public List<Position> getAdjacentEmptyCells(int yo, int xo) {
        List<Position> lst = new ArrayList<>();
        if(!(maze.get(yo,xo-1) instanceof Wall) && !(maze.get(yo,xo-1) instanceof Generic) && !(maze.get(yo,xo-1) instanceof PhantomCave))
            lst.add(new Position(yo,xo-1));
        if(!(maze.get(yo,xo+1) instanceof Wall) && !(maze.get(yo,xo+1) instanceof Generic) && !(maze.get(yo,xo+1) instanceof PhantomCave))
            lst.add(new Position(yo,xo+1));
        if(!(maze.get(yo-1,xo) instanceof Wall) && !(maze.get(yo-1,xo) instanceof Generic) && !(maze.get(yo-1,xo) instanceof PhantomCave))
            lst.add(new Position(yo-1,xo));
        if(!(maze.get(yo+1,xo) instanceof Wall) && !(maze.get(yo+1,xo) instanceof Generic) && !(maze.get(yo+1,xo) instanceof PhantomCave))
            lst.add(new Position(yo+1,xo));
        return lst;
    }

    public boolean evolve(){
        boolean aux = false;
        if(ballCount == 20){
            ballCount = 0;
            addFruit();
        }
        List<Generic> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Generic character) {
                    lst.add(character);
                    if(character instanceof TinyPac)
                        aux = true;
                }
        for(var character : lst)
            character.evolve();

        return aux;
    }

    private void addFruit() {
    }

    public boolean evolveInvert() {
        boolean aux = false;

        List<Generic> lst = new ArrayList<>();
        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Generic character) {
                    lst.add(character);
                    if(character instanceof TinyPac)
                        aux = true;
                }
        for(var character : lst)
            character.evolveInvert();

        return aux;
    }

    public char[][] getBoard() {
        return maze.getMaze();
    }



}
