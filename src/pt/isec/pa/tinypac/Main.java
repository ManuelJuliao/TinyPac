package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.text.UI;


public class Main {
    public static void main(String[] args) {

        GameContext gameContext = new GameContext();
        UI ui = new UI(gameContext);
        ui.start();

    }
}