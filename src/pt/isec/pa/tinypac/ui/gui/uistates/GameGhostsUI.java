package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.model.data.Characters.Generic;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

/**
 * This class represents the GameGhostsUI
 */
public class GameGhostsUI extends BorderPane {
    BoardManager gameManager;
    GridPane gridPane;
    Button btnEnd, btnPause;
    Label scoreLabel, lvlLabel, hpLabel;
    private final int tileSize = 25;
    /**
     * Constructs a GameGhostsUI object with the given game manager
     * @param gameManager game manager
     */
    public GameGhostsUI(BoardManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }
    /**
     * Creates the views
     */
    private void createViews() {
        char[][] board = gameManager.getBoard();

        gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(tileSize);
                imageView.setFitHeight(tileSize);
                Image aux = getImageForElement(board[i][j]);
                imageView.setImage(aux);
                // Assign image based on board state
                gridPane.add(imageView, j, i); // Add the imageView to the gridPane
            }
        }

        btnEnd = new Button("End game");
        btnPause = new Button("Pause");
        VBox infoBox = new VBox();
        lvlLabel = new Label("Level: 1" ); // Example label for level information
        scoreLabel = new Label("Score: " + gameManager.getPoints()); // Example label for score information
        hpLabel = new Label("HP: " + gameManager.getHP());
        infoBox.getChildren().addAll(lvlLabel, scoreLabel, hpLabel);
        infoBox.setAlignment(Pos.CENTER_RIGHT);

        HBox container = new HBox(gridPane, infoBox);
        container.setAlignment(Pos.CENTER); // Center the container vertically

        VBox vbox = new VBox(container, btnPause,btnEnd);
        vbox.setAlignment(Pos.BOTTOM_CENTER); // Align the button at the bottom and center
        VBox.setMargin(btnEnd, new Insets(10)); // Add some margin around the button

        this.setCenter(vbox);
    }
    /**
     * Gets the image depending on the element given
     * @param element element given
     * @return desired image
     */
    private Image getImageForElement(char element) {
        Image image;
        switch (element) {
            case 'T':
                image = ImageManager.getImage("pacman.png");
                break;
            case 'P':
                if(gameManager.getState()==GameState.GAME_GHOSTS)
                    image = ImageManager.getImage("pinky.jpg");
                else
                    image = ImageManager.getImage("phantom2.png");
                break;
            case 'I':
                if(gameManager.getState()==GameState.GAME_GHOSTS)
                    image = ImageManager.getImage("inky.png");
                else
                    image = ImageManager.getImage("phantom2.png");
                break;
            case 'C':
                if(gameManager.getState()==GameState.GAME_GHOSTS)
                    image = ImageManager.getImage("clyde.png");
                else
                    image = ImageManager.getImage("phantom2.png");
                break;
            case 'B':
                if(gameManager.getState()==GameState.GAME_GHOSTS)
                    image = ImageManager.getImage("blinky.png");
                else
                    image = ImageManager.getImage("phantom2.png");
                break;
            case 'x':
                image = ImageManager.getImage("black.jpg");
                break;
            case 'W':
                image = ImageManager.getImage("warp.png");
                break;
            case 'o':
                image = ImageManager.getImage("ball.jpg");
                break;
            case 'O':
                image = ImageManager.getImage("powerball.jpg");
                break;
            case 'F':
                image = ImageManager.getImage("fruit.png");
                break;
            default:
                image = ImageManager.getImage("wall.jpg");
                break;
        }
        return image;
    }
    /**
     * Registers handlers
     */
    private void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> { update(); });

        btnEnd.setOnAction(event -> {
            gameManager.end();
        });

        btnPause.setOnAction(event -> {
            gameManager.pause();
        });

        this.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            // Handle Pacman movement based on the pressed arrow keys
            if (keyCode == KeyCode.W) {
                gameManager.changeDirection(Generic.Direction.UP);
            } else if (keyCode == KeyCode.S) {
                gameManager.changeDirection(Generic.Direction.DOWN);
            } else if (keyCode == KeyCode.A) {
                gameManager.changeDirection(Generic.Direction.LEFT);
            } else if (keyCode == KeyCode.D) {
                gameManager.changeDirection(Generic.Direction.RIGHT);
            }
        });

        this.requestFocus();

    }
    /**
     * Updates the view
     */
    private void update() {
        if (gameManager.getState() != GameState.GAME_GHOSTS && gameManager.getState() != GameState.INVERT_GHOSTS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        updateBoardUI();


    }
    /**
     * Updates the board
     */
    private void updateBoardUI() {
        char[][] board = gameManager.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ImageView imageView = (ImageView) gridPane.getChildren().get(i * board[i].length + j);
                Image newImage = getImageForElement(board[i][j]);
                imageView.setImage(newImage);
            }
        }
        //scoreLabel.setText("Score: " + gameManager.getPoints()); // error "not on FX application thread"
        Platform.runLater(() -> {
            scoreLabel.setText("Score: " + gameManager.getPoints());
            hpLabel.setText("HP: " + gameManager.getHP());
        });
    }
}
