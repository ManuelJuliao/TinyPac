package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

/**
 * This class represents the EndGameUI
 */
public class EndGameUI extends BorderPane {
    private BoardManager gameManager;
    private Label scoreLabel,msgLabel;
    private TextField nameField;
    private Button btnSave, btnExit;
    /**
     * Constructs a EndGameUI object with the given game manager
     * @param gameManager game manager
     */
    public EndGameUI(BoardManager gameManager) {
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }
    /**
     * Creates the views
     */
    private void createViews() {
        scoreLabel = new Label();
        msgLabel = new Label();
        nameField = new TextField();
        btnSave = new Button("Save");
        btnExit = new Button("Exit");

        HBox hBox = new HBox(scoreLabel, nameField, btnSave, btnExit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        VBox vBox = new VBox(hBox);
        vBox.setAlignment(Pos.CENTER);

        this.setCenter(hBox);
    }
    /**
     * Registers handlers
     */
    private void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> {
            update();
        });

        btnSave.setOnAction(event -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                // Save the score with the player name
                gameManager.saveScore(playerName);
                Platform.exit();
            }
        });

        btnExit.setOnAction(event -> {
            Platform.exit();
        });
    }
    /**
     * Updates the view
     */
    private void update() {
        if (gameManager.getState() != GameState.WIN && gameManager.getState() != GameState.LOSE) {
            this.setVisible(false);
            return;
        }

        Platform.runLater(() -> {
            scoreLabel.setText("Score: " + gameManager.getPoints());
        });
        nameField.clear();

        if (gameManager.getState() == GameState.WIN) {
            msgLabel.setText("You Won!");
        } else {
            msgLabel.setText("You Lost!");
        }

        Platform.runLater(() -> {
            if (gameManager.top5()) {
                HBox hBox = new HBox(scoreLabel, nameField, btnSave, btnExit);
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);

                VBox vBox = new VBox(msgLabel, hBox);
                vBox.setAlignment(Pos.CENTER);

                this.setCenter(vBox);
            } else {
                VBox vBox = new VBox(msgLabel, scoreLabel, btnSave, btnExit);
                vBox.setAlignment(Pos.CENTER);

                this.setCenter(vBox);
            }
        });

        this.setVisible(true);
    }
}