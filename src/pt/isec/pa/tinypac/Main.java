package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.gameengine.Characters.Client;
import pt.isec.pa.tinypac.gameengine.Characters.TinyPac;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.data.Maze;

import static pt.isec.pa.tinypac.gameengine.Characters.Client.Direction.*;


public class Main {
    public static void main(String[] args) {

        Maze maze;
        TinyPac tinyPac = new TinyPac(UP);
        Client.Direction peido = tinyPac.getDirection();
        tinyPac.setDirection(peido);
        System.out.println(tinyPac.getDirection() + " " + tinyPac.getSymbol());
        GameEngine gameEngine = new GameEngine();

    }
}