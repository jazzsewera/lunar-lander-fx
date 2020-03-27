package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage stage) {

    Label levelLabel = new Label("Level 2");
    levelLabel.getStyleClass().add("ctrl-label-primary");


    // There will be images of Landers indicating number of lives
    Label livesStateLabel = new Label("***");
    livesStateLabel.getStyleClass().add("ctrl-label-primary");

    Label livesCaptionLabel = new Label("Lives");
    livesCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox livesVbox = new VBox();
    livesVbox.getChildren().addAll(livesStateLabel, livesCaptionLabel);


    Label fuelStateLabel = new Label("350");
    fuelStateLabel.getStyleClass().add("ctrl-label-primary");
    Label fuelMaxCapacityLabel = new Label("/400");
    fuelMaxCapacityLabel.getStyleClass().add("ctrl-label-secondary");
    HBox fuelHbox = new HBox();
    fuelHbox.getChildren().addAll(fuelStateLabel, fuelMaxCapacityLabel);
    fuelHbox.getStyleClass().add("ctrl-label-hbox");

    Label fuelCaptionLabel = new Label("Fuel");
    fuelCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox fuelVbox = new VBox();
    fuelVbox.getChildren().addAll(fuelHbox, fuelCaptionLabel);


    Label velocityStateLabel = new Label("2.5");
    velocityStateLabel.getStyleClass().add("ctrl-label-primary");
    Label velocityUnitLabel = new Label(" m/s");
    velocityUnitLabel.getStyleClass().add("ctrl-label-secondary");
    HBox velocityHbox = new HBox();
    velocityHbox.getChildren().addAll(velocityStateLabel, velocityUnitLabel);
    velocityHbox.getStyleClass().add("ctrl-label-hbox");

    Label velocityCaptionLabel = new Label("Velocity");
    velocityCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox velocityVbox = new VBox();
    velocityVbox.getChildren().addAll(velocityHbox, velocityCaptionLabel);


    Label heightStateLabel = new Label("15.0");
    heightStateLabel.getStyleClass().add("ctrl-label-primary");
    Label heightUnitLabel = new Label(" m");
    heightUnitLabel.getStyleClass().add("ctrl-label-secondary");
    HBox heightHbox = new HBox();
    heightHbox.getChildren().addAll(heightStateLabel, heightUnitLabel);
    heightHbox.getStyleClass().add("ctrl-label-hbox");

    Label heightCaptionLabel = new Label("Height");
    heightCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox heightVbox = new VBox();
    heightVbox.getChildren().addAll(heightHbox, heightCaptionLabel);


    Label scoreStateLabel = new Label("69420");
    scoreStateLabel.getStyleClass().add("ctrl-label-primary");

    Label scoreCaptionLabel = new Label("Score");
    scoreCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox scoreVbox = new VBox();
    scoreVbox.getChildren().addAll(scoreStateLabel, scoreCaptionLabel);


    VBox verticalLayoutVbox = new VBox();
    verticalLayoutVbox.getStyleClass().add("main-vertical-layout");
    verticalLayoutVbox.getChildren().addAll(levelLabel,
                                            livesVbox,
                                            fuelVbox,
                                            velocityVbox,
                                            heightVbox,
                                            scoreVbox);


    Scene scene = new Scene(verticalLayoutVbox);

    scene.getStylesheets().add("lunarlander/css/style.css");

    stage.setScene(scene);
    stage.setTitle("Lunar Lander");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
