package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

import java.util.Optional;

import static pt.isec.pa.tinypac.ui.gui.resources.ImageManager.getImage;

/**
 * This class represents the GameUI
 */
public class GameUI extends BorderPane {
    private TopScoresUI topScoresUI;
    BoardManager gameManager;
    Button btnStart,btnExit,btnScore;
    /**
     * Constructs a GameUI object with the given game manager
     * @param gameManager game manager
     */
    public GameUI(BoardManager gameManager, TopScoresUI topScoresUI) {
        this.gameManager = gameManager;
        this.topScoresUI = topScoresUI;
        createViews();
        registerHandlers();
        update();
    }
    /**
     * Creates the views
     */
    private void createViews() {
        Label headerLabel = new Label("Trabalho Prático de Programação Avançada 2022/2023 DEIS-ISEC-IPC");

        ImageView logoImage = new ImageView();
        logoImage.setImage(getImage("isec.jpg"));
        logoImage.setFitHeight(100);
        logoImage.setPreserveRatio(true);

        HBox headerBox = new HBox(headerLabel, logoImage);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(10);

        btnStart = new Button("Start");
        btnStart.setMinWidth(100);
        btnScore = new Button("Top 5");
        btnScore.setMinWidth(100);
        btnExit  = new Button("Exit");
        btnExit.setMinWidth(100);
        HBox hBox = new HBox(btnStart,btnScore,btnExit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Label footerLabel = new Label("Manuel Julião nº2018010620 LEI-PL");
        VBox vbox = new VBox(headerBox, hBox, footerLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        this.setCenter(vbox);
    }
    /**
     * Registers handlers
     */
    private void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> { update(); });
        btnStart.setOnAction( event -> {
            gameManager.startGame();
        });
        btnScore.setOnAction(event -> {
            topScoresUI.setVisible(true); // Show the TopScoresUI
            this.setVisible(false); // Hide the GameUI
        });
        btnExit.setOnAction( event -> {
            exitConfirmation();
            //Platform.exit();
        });
    }
    /**
     * Updates the view
     */
    private void update() {
        if (gameManager.getState() != GameState.GAME) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }
    /**
     * Creates an alert to confirm exit
     */
    private void exitConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit the game?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
