package unsw.automata;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {
	static final int SIZE  = 10;
    @FXML
    private GridPane squares;
    @FXML
    private Button play;
    @FXML
    private Button tick;
    
    private GameOfLife gol = new GameOfLife();


    @FXML
    public void handlePlay() {
    	
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!gol.lose()) {
                        gol.tick();
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                makeDia();
                            }
                        });
                        timeline.stop();
                    }
                    timeline.setOnFinished(event1 -> {
                        if (play.getText().compareTo("Stop") == 0) {
                            timeline.play();
                        }
                    });
                }
            })
        );
        switch (play.getText()) {
            case "Play":
                timeline.play();
                play.setText("Stop");
                break;
            case "Stop":
                timeline.stop();
                play.setText("Play");
                break;
        }
    }

    @FXML
    public void handelTick() {
        if (!gol.lose()) {
            gol.tick();
        } else {
            makeDia();
        }
    }

    @FXML
    public void initialize() {
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE;j ++) {
                CheckBox checkBox = new CheckBox();
                checkBox.setPadding(new Insets(0, 2, 2, 0));
                
                gol.setCells(i,j,checkBox.selectedProperty());
                GridPane.setRowIndex(checkBox, i);
                GridPane.setColumnIndex(checkBox, j);
                squares.getChildren().add(checkBox);
            }
        }
    }

    public void makeDia() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Lose");
        alert.setContentText("Sorry no more cells");

        ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeCancel){
            alert.close();
            System.exit(0);
        }

    }


}
