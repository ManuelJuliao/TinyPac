package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.uistates.*;

/**
 * This class represents the RootPane
 */
public class RootPane extends BorderPane {
    BoardManager gameManager;

    /**
     * Constructs a RootPane object given the BoardManager
     * @param boardManager game manager
     */
    public RootPane(BoardManager boardManager) {
        this.gameManager = boardManager;
        createViews();
        registerHandlers();
        update();
    }

    /**
     * Creates the views
     */
    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        TopScoresUI topScoresUI = new TopScoresUI(gameManager);
        GameUI gameUI = new GameUI(gameManager,topScoresUI);
        topScoresUI.setGameUI(gameUI);
        StackPane stackPane = new StackPane(
                topScoresUI,
                gameUI,
                new GameGhostsUI(gameManager),
                new PauseUI(gameManager),
                new EndGameUI(gameManager)
        );
        stackPane.setBackground(
                new Background(
                        new BackgroundImage(
                                ImageManager.getImage("background.jpg"),
                                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(1,1,true,true,false,false)
                        )
                )
        );
        this.setCenter(stackPane);
    }

    /**
     * Registers handlers
     */
    private void registerHandlers() {
    }
    /**
     * Updates the view
     */
    private void update() {

    }

}
