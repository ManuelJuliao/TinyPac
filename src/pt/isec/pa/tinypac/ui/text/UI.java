package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;

public class UI {
    GameContext fsm;

    public UI(GameContext fsm) {
        this.fsm = fsm;
    }

    private boolean finish = false;
    public void start() {
        while(!finish) {
            if (fsm.getState() == null) System.exit(-1); // TODO: remove!
            switch (fsm.getState()) {
                case MENU -> menu();
                case GAME -> game();
                case TOP_5 -> top5();
                default -> finish = true;
            }
        }
    }

    private void top5() {
    }

    private void game() {
    }

    private void menu() {
        switch (PAInput.chooseOption("TinyPac DEIS-ISEC-IPC",
                "Start Game", "Top 5" , "Sair")) {
            case 1:
                fsm.start();
                System.out.println("Game ready, press to continue");
                PAInput.anyInput();
                fsm.startGame();
            case 2, 3:
                finish = true;
        }
    }
}