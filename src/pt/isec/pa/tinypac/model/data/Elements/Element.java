package pt.isec.pa.tinypac.model.data.Elements;

import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.IMazeElement;
/**
 * This class represents an Element
 */
public abstract class Element implements IMazeElement {
    char symbol;
    /**
     * Constructs a new Element
     * @param symbol element symbol
     */
    public Element(char symbol){
        this.symbol = symbol;
    }
    /**
     * Returns SYMBOL
     * @return SYMBOL
     */
    @Override
    public char getSymbol() {
        return symbol;
    }
}
