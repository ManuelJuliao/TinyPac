package pt.isec.pa.tinypac.model.data.Elements;

import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.IMazeElement;

public abstract class Element implements IMazeElement {

    char symbol;

    public Element(char symbol){
        this.symbol = symbol;
    }
    @Override
    public char getSymbol() {
        return symbol;
    }
}
