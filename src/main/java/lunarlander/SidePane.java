package lunarlander;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class SidePane {
  public SidePane() {
    this.levelLabel = new Label("Level 2");
    this.levelLabel.getStyleClass().add("ctrl-label-primary");
    this.optionalLevelCaption = new Label("");
    this.optionalLevelCaption.getStyleClass().add("ctrl-label-caption");

    VBox levelVbox = new VBox();
    levelVbox.getChildren().addAll(this.levelLabel, this.optionalLevelCaption);
    levelVbox.getStyleClass().add("ctrl-gauge-vbox");


    // There will be images of Landers indicating number of lives
    this.landerImage = new Image("lunarlander/assets/lander.png");
    this.livesStateLabels = new Label[] {
        new Label("", new ImageView(this.landerImage)),
        new Label("", new ImageView(this.landerImage)),
        new Label("", new ImageView(this.landerImage))
    };
    for (Label label : this.livesStateLabels) {
      label.getStyleClass().add("ctrl-label-primary");
    }
    // You can quickly remove one lander with this statement:
    // livesStateLabels[2].setGraphic(null);

    HBox livesStateHbox = new HBox();
    livesStateHbox.getChildren().addAll(
        this.livesStateLabels[0],
        this.livesStateLabels[1],
        this.livesStateLabels[2]
    );
    livesStateHbox.getStyleClass().add("ctrl-lives-state-hbox");

    Label livesCaptionLabel = new Label("Lives");
    livesCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox livesVbox = new VBox();
    livesVbox.getChildren().addAll(livesStateHbox, livesCaptionLabel);
    livesVbox.getStyleClass().add("ctrl-gauge-vbox");


    this.fuelStateLabel = new Label("350");
    this.fuelStateLabel.getStyleClass().add("ctrl-label-primary");
    this.fuelMaxCapacityLabel = new Label("/400");
    this.fuelMaxCapacityLabel.getStyleClass().add("ctrl-label-secondary");
    HBox fuelHbox = new HBox();
    fuelHbox.getChildren().addAll(this.fuelStateLabel, this.fuelMaxCapacityLabel);
    fuelHbox.getStyleClass().add("ctrl-label-hbox");

    Label fuelCaptionLabel = new Label("Fuel");
    fuelCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox fuelVbox = new VBox();
    fuelVbox.getChildren().addAll(fuelHbox, fuelCaptionLabel);
    fuelVbox.getStyleClass().add("ctrl-gauge-vbox");


    this.velocityStateLabel = new Label("2.5");
    this.velocityStateLabel.getStyleClass().add("ctrl-label-primary");
    Label velocityUnitLabel = new Label(" m/s");
    velocityUnitLabel.getStyleClass().add("ctrl-label-secondary");
    HBox velocityHbox = new HBox();
    velocityHbox.getChildren().addAll(this.velocityStateLabel, velocityUnitLabel);
    velocityHbox.getStyleClass().add("ctrl-label-hbox");

    Label velocityCaptionLabel = new Label("Velocity");
    velocityCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox velocityVbox = new VBox();
    velocityVbox.getChildren().addAll(velocityHbox, velocityCaptionLabel);
    velocityVbox.getStyleClass().add("ctrl-gauge-vbox");


    this.heightStateLabel = new Label("15.0");
    this.heightStateLabel.getStyleClass().add("ctrl-label-primary");
    Label heightUnitLabel = new Label(" m");
    heightUnitLabel.getStyleClass().add("ctrl-label-secondary");
    HBox heightHbox = new HBox();
    heightHbox.getChildren().addAll(this.heightStateLabel, heightUnitLabel);
    heightHbox.getStyleClass().add("ctrl-label-hbox");

    Label heightCaptionLabel = new Label("Height");
    heightCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox heightVbox = new VBox();
    heightVbox.getChildren().addAll(heightHbox, heightCaptionLabel);
    heightVbox.getStyleClass().add("ctrl-gauge-vbox");


    this.scoreStateLabel = new Label("69420");
    this.scoreStateLabel.getStyleClass().add("ctrl-label-primary");

    Label scoreCaptionLabel = new Label("Score");
    scoreCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox scoreVbox = new VBox();
    scoreVbox.getChildren().addAll(this.scoreStateLabel, scoreCaptionLabel);
    scoreVbox.getStyleClass().add("ctrl-gauge-vbox");


    VBox verticalLayoutVbox = new VBox();
    verticalLayoutVbox.getStyleClass().add("ctrl-main-vertical-layout");
    verticalLayoutVbox.getChildren().addAll(
      levelVbox,
      livesVbox,
      fuelVbox,
      velocityVbox,
      heightVbox,
      scoreVbox
    );
    // Variable margins between elements so that they stay evenly distributed
    for (Node child : verticalLayoutVbox.getChildren()) {
      VBox.setVgrow(child, Priority.ALWAYS);
    }


    Label networkCaptionLabel = new Label("Network sync: ");
    networkCaptionLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    this.networkStatusLabel = new Label("disabled");
    this.networkStatusLabel.getStyleClass().add("ctrl-label-status-bar-primary");

    HBox networkStatusHbox = new HBox();
    networkStatusHbox.getChildren().addAll(networkCaptionLabel, networkStatusLabel);

    this.leftKbdIndicatorLabel = new Label("left");
    this.leftKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    this.upKbdIndicatorLabel = new Label("up");
    this.upKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    this.rightKbdIndicatorLabel = new Label("right");
    this.rightKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-secondary");
    // You can switch an indicator to "on" with those statements:
    // leftKbdIndicatorLabel.getStyleClass().clear();
    // leftKbdIndicatorLabel.getStyleClass().add("ctrl-label-status-bar-primary");

    HBox kbdIndicatorHbox = new HBox();
    kbdIndicatorHbox.getChildren().addAll(
        this.leftKbdIndicatorLabel,
        this.upKbdIndicatorLabel,
        this.rightKbdIndicatorLabel
    );
    kbdIndicatorHbox.getStyleClass().add("ctrl-kbd-indicator-hbox");

    HBox bottomStatusBarHbox = new HBox();
    bottomStatusBarHbox.getStyleClass().add("ctrl-bottom-status-bar");
    bottomStatusBarHbox.getChildren().addAll(networkStatusHbox, kbdIndicatorHbox);
    // Variable margin between network and kbd labels, so that kbd stays on the right
    HBox.setHgrow(networkStatusHbox, Priority.ALWAYS);


    this.sideBorderPane = new BorderPane();
    this.sideBorderPane.getStyleClass().add("ctrl-root");
    this.sideBorderPane.setCenter(verticalLayoutVbox);
    this.sideBorderPane.setBottom(bottomStatusBarHbox);
  }

  public void setLevel(int lvl) {
    this.levelLabel.setText("Level " + lvl);
  }

  public void setKbdUpPressed() {
    this.upKbdIndicatorLabel.setTextFill(Color.web("#92B2FF"));
  }
  public void setKbdUpReleased() {
    this.upKbdIndicatorLabel.setTextFill(Color.web("#4B73D5"));
  }
  public void setKbdLeftPressed() {
    this.leftKbdIndicatorLabel.setTextFill(Color.web("#92B2FF"));
  }
  public void setKbdLeftReleased() {
    this.leftKbdIndicatorLabel.setTextFill(Color.web("#4B73D5"));
  }
  public void setKbdRightPressed() {
    this.rightKbdIndicatorLabel.setTextFill(Color.web("#92B2FF"));
  }
  public void setKbdRightReleased() {
    this.rightKbdIndicatorLabel.setTextFill(Color.web("#4B73D5"));
  }

  public BorderPane getSideBorderPane() {
    return sideBorderPane;
  }

  private Label levelLabel;
  private Label optionalLevelCaption;
  private final Image landerImage;
  private Label[] livesStateLabels;
  private Label fuelStateLabel;
  private Label fuelMaxCapacityLabel;
  private Label velocityStateLabel;
  private Label heightStateLabel;
  private Label scoreStateLabel;
  private Label networkStatusLabel;
  private Label leftKbdIndicatorLabel;
  private Label upKbdIndicatorLabel;
  private Label rightKbdIndicatorLabel;
  private final BorderPane sideBorderPane;
}
