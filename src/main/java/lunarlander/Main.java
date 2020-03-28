package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage stage) {

    Label levelLabel = new Label("Level 2");
    levelLabel.getStyleClass().add("ctrl-label-primary");
    Label optionalLevelCaption = new Label("");
    optionalLevelCaption.getStyleClass().add("ctrl-label-caption");

    VBox levelVbox = new VBox();
    levelVbox.getChildren().addAll(levelLabel, optionalLevelCaption);
    levelVbox.getStyleClass().add("ctrl-gauge-vbox");


    // There will be images of Landers indicating number of lives
    Label[] livesStateLabels = {new Label("*"), new Label("*"), new Label("*")};
    for (Label label : livesStateLabels) {
      label.getStyleClass().add("ctrl-label-primary");
    }

    HBox livesStateHbox = new HBox();
    livesStateHbox.getChildren().addAll(livesStateLabels[0], livesStateLabels[1], livesStateLabels[2]);

    Label livesCaptionLabel = new Label("Lives");
    livesCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox livesVbox = new VBox();
    livesVbox.getChildren().addAll(livesStateHbox, livesCaptionLabel);
    livesVbox.getStyleClass().add("ctrl-gauge-vbox");


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
    fuelVbox.getStyleClass().add("ctrl-gauge-vbox");


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
    velocityVbox.getStyleClass().add("ctrl-gauge-vbox");


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
    heightVbox.getStyleClass().add("ctrl-gauge-vbox");


    Label scoreStateLabel = new Label("69420");
    scoreStateLabel.getStyleClass().add("ctrl-label-primary");

    Label scoreCaptionLabel = new Label("Score");
    scoreCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox scoreVbox = new VBox();
    scoreVbox.getChildren().addAll(scoreStateLabel, scoreCaptionLabel);
    scoreVbox.getStyleClass().add("ctrl-gauge-vbox");


    VBox verticalLayoutVbox = new VBox();
    verticalLayoutVbox.getStyleClass().add("ctrl-main-vertical-layout");
    verticalLayoutVbox.getChildren().addAll(levelVbox,
                                            livesVbox,
                                            fuelVbox,
                                            velocityVbox,
                                            heightVbox,
                                            scoreVbox);
    // Variable margins between elements so that they stay evenly distributed
    for (Node child : verticalLayoutVbox.getChildren()) {
      VBox.setVgrow(child, Priority.ALWAYS);
    }


    Label networkCaptionLabel = new Label("Network sync: ");
    networkCaptionLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    Label networkStatusLabel = new Label("disabled");
    networkStatusLabel.getStyleClass().add("ctrl-label-status-bar-primary");

    HBox networkStatusHbox = new HBox();
    networkStatusHbox.getChildren().addAll(networkCaptionLabel, networkStatusLabel);

    Label leftKbdIndicatorLabel = new Label("left");
    leftKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    Label upKbdIndicatorLabel = new Label("up");
    upKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    Label rightKbdIndicatorLabel = new Label("right");
    rightKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-secondary");

    HBox kbdIndicatorHbox = new HBox();
    kbdIndicatorHbox.getChildren().addAll(leftKbdIndicatorLabel, upKbdIndicatorLabel, rightKbdIndicatorLabel);
    kbdIndicatorHbox.getStyleClass().add("ctrl-kbd-indicator-hbox");

    HBox bottomStatusBarHbox = new HBox();
    bottomStatusBarHbox.getStyleClass().add("ctrl-bottom-status-bar");
    bottomStatusBarHbox.getChildren().addAll(networkStatusHbox, kbdIndicatorHbox);
    // Variable margin between network and kbd labels, so that kbd stays on the right
    HBox.setHgrow(networkStatusHbox, Priority.ALWAYS);


    BorderPane root = new BorderPane();
    root.getStyleClass().add("ctrl-root");
    root.setCenter(verticalLayoutVbox);
    root.setBottom(bottomStatusBarHbox);

    Scene scene = new Scene(root);

    scene.getStylesheets().add("lunarlander/css/style.css");

    stage.setScene(scene);
    stage.setTitle("Lunar Lander");
    stage.setWidth(280);
    stage.setHeight(600);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
