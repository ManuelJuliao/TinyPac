package pt.isec.pa.tinypac.ui;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Maze;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UIController implements KeyListener {
    private final IGameEngine gameEngine;

    public UIController(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                //TODO gameEngine.moveCharacterLeft();
                break;
            case KeyEvent.VK_RIGHT:
                //TODO gameEngine.moveCharacterRight();
                break;
            case KeyEvent.VK_UP:
                //TODO gameEngine.moveCharacterLeft();
                break;
            case KeyEvent.VK_DOWN:
                //TODO gameEngine.moveCharacterRight();
                break;
            // handle other key events as needed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used in this example
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used in this example
    }
}
