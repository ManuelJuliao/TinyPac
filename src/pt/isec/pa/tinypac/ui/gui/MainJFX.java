package pt.isec.pa.tinypac.ui.gui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.BoardManager;
import pt.isec.pa.tinypac.Main;

public class MainJFX extends Application {
    BoardManager gameManager;

    @Override
    public void init() throws Exception {
        super.init();
        gameManager = Main.gameManager;
    }

    @Override
    public void start(Stage stage) throws Exception {
        newStageForTesting(stage,"TinyPac");
    }

    private void newStageForTesting(Stage stage, String title) {
        RootPane root = new RootPane(gameManager);
        Scene scene = new Scene(root,1000,1000);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(1000);
        stage.setMinHeight(1000);
        stage.show();
    }
}



