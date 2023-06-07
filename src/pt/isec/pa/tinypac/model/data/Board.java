package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.Characters.Generic;
import pt.isec.pa.tinypac.model.data.Characters.TinyPac;
import pt.isec.pa.tinypac.model.data.Elements.Element;
import pt.isec.pa.tinypac.model.data.Elements.Wall;


import java.util.ArrayList;
import java.util.List;

public class Board {
    public record Position(int y, int x) {};

    int points = 0;

    int height, width;

    Maze maze;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Maze(height,width);
    }

    public void addPoints (int count){
        points += count;
    }

    public void add(IMazeElement aux, int y, int x){maze.set(y,x,aux);}

    public void addCharacter(Generic character, int y, int x) {
        maze.set(y,x,character);
    }

    public void addElement(Element element, int y, int x){maze.set(y,x,element);}


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
        if(!(maze.get(yo,xo-1) instanceof Wall))
            lst.add(new Position(yo,xo-1));
        if(!(maze.get(yo,xo+1) instanceof Wall))
            lst.add(new Position(yo,xo+1));
        if(!(maze.get(yo-1,xo) instanceof Wall))
            lst.add(new Position(yo-1,xo));
        if(!(maze.get(yo+1,xo) instanceof Wall))
            lst.add(new Position(yo+1,xo));
        return lst;
    }

    public boolean evolve(){
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
            character.evolve();

        return aux;
    }

    public char[][] getBoard() {
        return maze.getMaze();
    }



}
