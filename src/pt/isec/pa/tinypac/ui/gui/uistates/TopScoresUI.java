package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.PlayerScore;
/**
 * This class represents the TopScoresUI
 */
public class TopScoresUI extends BorderPane {
    private BoardManager gameManager;
    private VBox scoreList;
    private Button btnBack;
    private GameUI gameUI;

    /**
     * Constructs a TopScoresUI object with the given game manager
     * @param gameManager game manager
     */
    public TopScoresUI(BoardManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }
    /**
     * Creates the views
     */
    private void createViews() {
        btnBack = new Button("Back");
        scoreList = new VBox();
        scoreList.setAlignment(Pos.CENTER);
        this.setCenter(scoreList);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox bottomContainer = new VBox(btnBack, spacer);
        bottomContainer.setAlignment(Pos.CENTER);
        int bottomPadding = 20;
        this.setBottom(bottomContainer);
        this.setPadding(new Insets(0, 0, bottomPadding, 0));
    }

    private void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> {
            update();
        });
        btnBack.setOnAction(event -> {
            this.setVisible(false);
            gameUI.setVisible(true);
        });
    }
    /**
     * Registers handlers
     */
    private void update() {

        Platform.runLater(() -> {
            scoreList.getChildren().clear();
        });

        Platform.runLater(() -> {
            int rank = 1;
            for (PlayerScore playerScore : gameManager.getScoreList()) {
                Label scoreLabel = new Label(rank + ". " + playerScore.getName() + ": " + playerScore.getScore());
                scoreList.getChildren().add(scoreLabel);
                rank++;
            }
        });

        this.setVisible(false);
    }
    /**
     * Saves a reference to the GameUI
     * @param gameUI gameUI instance
     */
    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }
}