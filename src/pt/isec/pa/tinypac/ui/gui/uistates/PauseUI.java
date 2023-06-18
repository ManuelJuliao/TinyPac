package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.fsm.GameState;
/**
 * This class represents the PauseUI
 */
public class PauseUI extends BorderPane {
    BoardManager gameManager;
    Button btnResume, btnExit;
    /**
     * Constructs a PauseUI object with the given game manager
     * @param gameManager game manager
     */
    public PauseUI(BoardManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }
    /**
     * Creates the views
     */
    private void createViews() {
        btnResume = new Button("Resume");
        btnResume.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        HBox hBox = new HBox(btnResume,btnExit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        this.setCenter(hBox);
    }
    /**
     * Registers handlers
     */
    private void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> { update(); });
        btnResume.setOnAction( event -> {
            gameManager.resume();
        });
        btnExit.setOnAction( event -> {
            Platform.exit();
        });
    }
    /**
     * Updates the view
     */
    private void update() {
        if (gameManager.getState() != GameState.PAUSE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }
}
