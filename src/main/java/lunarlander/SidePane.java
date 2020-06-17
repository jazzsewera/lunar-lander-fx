package lunarlander;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


/**
 * Gui component - a pane with game state gauges.
 *
 * <p>
 *  Sample usage:
 *  <pre>
    SidePane sp = new SidePane();
    BorderPane sideBorderPane = sp.getSideBorderPane();
 *  </pre>
 *
 * @author Błażej Sewera
 * @author Mateusz Winnicki
 * @see BorderPane
 */
public class SidePane {

  public static class UpdateLanderInfoEvent extends Event {
    UpdateLanderInfoEvent(double fuelState, double currentVelocity, double altitude, int shipsLeft, int level, int score) {
      super(UPDATE_INFO);
      this.fuelState = fuelState;
      this.currentVelocity = currentVelocity;
      this.altitude = altitude;
      this.shipsLeft = shipsLeft;
      this.level = level;
      this.score = score;
    }

    public double fuelState;
    public double currentVelocity;
    public double altitude;
    public int shipsLeft;
    public int level;
    public int score;

    public static final EventType<UpdateLanderInfoEvent> UPDATE_INFO = new EventType<>("UPDATE_INFO");

    public static final long serialVersionUID = 12345;
  }

  /**
   * Constructor creating all the Labels displaying
   * current parameters of the Lander.
   *
   * @param configuration set of config files and methods working on them: Configuration
   * @param lander representation of our Lander with all its parameters : Lander
   */
  public SidePane(Configuration configuration, Lander lander) {

    this.levelLabel = new Label("Level 1");
    this.levelLabel.getStyleClass().add("ctrl-label-primary");
    this.optionalLevelCaption = new Label("");
    this.optionalLevelCaption.getStyleClass().add("ctrl-label-caption");

    VBox levelVbox = new VBox();
    levelVbox.getChildren().addAll(this.levelLabel, this.optionalLevelCaption);
    levelVbox.getStyleClass().add("ctrl-gauge-vbox");

    // There will be images of Landers indicating number of lives
    this.landerImage = new Image("lunarlander/assets/lander.png");
    this.landerDeadImage = new Image("lunarlander/assets/lander_dead.png");
    this.livesStateLabels = new Label[] {
        new Label("", new ImageView(this.landerImage)),
        new Label("", new ImageView(this.landerImage)),
        new Label("", new ImageView(this.landerImage))
    };
    for (Label label : this.livesStateLabels) {
      label.getStyleClass().add("ctrl-label-primary");
    }

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

    this.fuelStateLabel = new Label(Integer.toString((int) lander.fuel));
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


    this.velocityStateLabel = new Label(Double.toString(lander.v));
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

    Label heightCaptionLabel = new Label("Altitude");
    heightCaptionLabel.getStyleClass().add("ctrl-label-caption");

    VBox heightVbox = new VBox();
    heightVbox.getChildren().addAll(heightHbox, heightCaptionLabel);
    heightVbox.getStyleClass().add("ctrl-gauge-vbox");


    this.scoreStateLabel = new Label("0");
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
    if(configuration.isConfigDownloaded()) {
      this.networkStatusLabel = new Label("enabled");
    } else {
      this.networkStatusLabel = new Label("disabled");
    }
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
    this.sideBorderPane.setMinWidth(280);
    this.sideBorderPane.setPrefWidth(280);
    this.sideBorderPane.setMaxWidth(280);
  }



  /**
   * Method setting current level number on the Level indicator
   * on the top of the Pane.
   *
   * @param lvl a level number to set, integer
   */
  public void setLevel(int lvl) {
    this.levelLabel.setText("Level " + lvl);
  }

  /**
   * Method setting head Label of sidePane to display Pause
   * text. What is more Go-to-main-menu button appears which
   * allows us to leave paused game.
   */
  public void headLabelPaused() {
    this.levelLabel.setText("Paused");
    this.optionalLevelCaption.setText("Go to main menu");

    this.optionalLevelCaption.setOnMouseEntered((event) -> {
      this.optionalLevelCaption.getStyleClass().add("ctrl-label-caption-hover-effect");
    });

    this.optionalLevelCaption.setOnMouseExited((event) -> {
      this.optionalLevelCaption.getStyleClass().clear();
      this.optionalLevelCaption.getStyleClass().add("ctrl-label-caption");
    });

    this.optionalLevelCaption.setOnMouseClicked((event) -> {
      optionalLevelCaption.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
    });

  }

  public void headLabelLost() {
    this.levelLabel.setText("Game Over");
    this.optionalLevelCaption.setText("Save score");

    this.optionalLevelCaption.setOnMouseEntered((event) -> {
      this.optionalLevelCaption.getStyleClass().add("ctrl-label-caption-hover-effect");
    });

    this.optionalLevelCaption.setOnMouseExited((event) -> {
      this.optionalLevelCaption.getStyleClass().clear();
      this.optionalLevelCaption.getStyleClass().add("ctrl-label-caption");
    });

    this.optionalLevelCaption.setOnMouseClicked((event) -> {
      optionalLevelCaption.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
    });

  }

  /**
   * Method setting head Label of sidePane back to displaying
   * current level we are in when we unpause game.
   *
   * @param lvl a level number to set, integer
   */
  public void headLabelUnpaused(int lvl) {
    this.levelLabel.setText("Level " + lvl);
    this.optionalLevelCaption.setText("");
  }

  /**
   * Method setting 'up' keyboard indicator in the control strip
   * on the bottom of the Pane to active (brighter color).
   *
   * <p>
   *  Intended use in an event handler, when the 'up' key is
   *  pressed on a keyboard. For example:
      <pre>
      this.mainGameScene.setOnKeyPressed(event -&gt; {
        if (event.getCode() == KeyCode.UP) sidePane.setKbdUpPressed();
      </pre>
   */
  public void setKbdUpPressed() {
    this.upKbdIndicatorLabel.setTextFill(Color.web("#92B2FF"));
  }

  /**
   * Method setting 'up' keyboard indicator in the control strip
   * on the bottom of the Pane to inactive (dimmer color).
   *
   * <p>
   *  Intended use in an event handler, when the 'up' key is
   *  released on a keyboard. For example:
      <pre>
      this.mainGameScene.setOnKeyReleased(event -&gt; {
        if (event.getCode() == KeyCode.UP) sidePane.setKbdUpReleased();
      </pre>
   */
  public void setKbdUpReleased() {
    this.upKbdIndicatorLabel.setTextFill(Color.web("#4B73D5"));
  }

  /**
   * Method setting 'left' keyboard indicator in the control strip
   * on the bottom of the Pane to active (brighter color).
   *
   * <p>
   *  Intended use in an event handler, when the 'left' key is
   *  pressed on a keyboard. For example:
      <pre>
      this.mainGameScene.setOnKeyPressed(event -&gt; {
        if (event.getCode() == KeyCode.LEFT) sidePane.setKbdLeftPressed();
      </pre>
   */
  public void setKbdLeftPressed() {
    this.leftKbdIndicatorLabel.setTextFill(Color.web("#92B2FF"));
  }

  /**
   * Method setting 'left' keyboard indicator in the control strip
   * on the bottom of the Pane to inactive (dimmer color).
   *
   * <p>
   *  Intended use in an event handler, when the 'left' key is
   *  released on a keyboard. For example:
      <pre>
      this.mainGameScene.setOnKeyReleased(event -&gt; {
        if (event.getCode() == KeyCode.LEFT) sidePane.setKbdLeftReleased();
      </pre>
   */
  public void setKbdLeftReleased() {
    this.leftKbdIndicatorLabel.setTextFill(Color.web("#4B73D5"));
  }

  /**
   * Method setting 'right' keyboard indicator in the control strip
   * on the bottom of the Pane to active (brighter color).
   *
   * <p>
   *  Intended use in an event handler, when the 'right' key is
   *  pressed on a keyboard. For example:
      <pre>
      this.mainGameScene.setOnKeyPressed(event -&gt; {
        if (event.getCode() == KeyCode.RIGHT) sidePane.setKbdRightPressed();
      </pre>
   */
  public void setKbdRightPressed() {
    this.rightKbdIndicatorLabel.setTextFill(Color.web("#92B2FF"));
  }

  /**
   * Method setting 'right' keyboard indicator in the control strip
   * on the bottom of the Pane to inactive (dimmer color).
   *
   * <p>
   *  Intended use in an event handler, when the 'right' key is
   *  released on a keyboard. For example:
      <pre>
      this.mainGameScene.setOnKeyReleased(event -&gt; {
        if (event.getCode() == KeyCode.RIGHT) sidePane.setKbdRightReleased();
      </pre>
   */
  public void setKbdRightReleased() {
    this.rightKbdIndicatorLabel.setTextFill(Color.web("#4B73D5"));
  }

  public void updateSidePane(double fuelState, double currentVelocity, double currentAltitude, int shipsLeft, int level, int currentScore) {
    double velocity = Math.round(currentVelocity * 10.0) / 10.0;
    double altitude;
    if (currentAltitude >= 0.0) altitude = Math.round(currentAltitude * 100.0) / 100.0;
    else altitude = 0.0;

    this.levelLabel.setText("Level " + level);
    this.fuelStateLabel.setText(Integer.toString((int) fuelState));
    this.velocityStateLabel.setText(Double.toString(velocity));
    this.heightStateLabel.setText(Double.toString(altitude));
    this.scoreStateLabel.setText(Integer.toString(currentScore));

    switch (shipsLeft) {
      case 3:
        break;
      case 2:
        livesStateLabels[2].setGraphic(new ImageView(this.landerDeadImage));
        break;
      case 1:
        livesStateLabels[1].setGraphic(new ImageView(this.landerDeadImage));
        break;
      case 0:
        livesStateLabels[0].setGraphic(new ImageView(this.landerDeadImage));
        headLabelLost();
        break;
    }
  }

  /**
   * Method to get an actual pane that can be rendered in the window.
   *
   * @return sideBorderPane, {@link BorderPane}
   */
  public BorderPane getSideBorderPane() {
    return sideBorderPane;
  }


  private Label levelLabel;
  private Label optionalLevelCaption;
  private final Image landerImage;
  private final Image landerDeadImage;
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
