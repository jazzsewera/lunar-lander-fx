package lunarlander;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;


/**
 * Class preparing a Pane of a game screen we are
 * displaying in ApplicationWindow, {@link GameWindow}.
 */
public class GamePane {

  /**
   * Constructor reading configuration.json file, {@link Configuration},
   * and creating a Pane of moonSurface of chosen Moon, {@link Moon}.
   */
  public GamePane(Configuration configuration, Lander landerModel, int currentLevel) {
    this.configuration = configuration;

    // for (int i = 1; i <= 3; i++) {
    //   this.configuration.generateLevel(i);
    // }
    // this.configuration.toFile();

    if(!this.configuration.isConfigDownloaded()) {
      this.configuration.fromFile("src/main/resources/lunarlander/configuration.json");
    } else {
      this.configuration.fromFile("src/main/resources/lunarlander/configuration_fromserver.json");
    }

    try {
      Moon moon = this.configuration.getMoonMap(currentLevel);
      this.landingHeight = moon.getScaledLandingHeight();
      this.moonSurface = new Polygon();
      moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());
    } catch (IndexOutOfBoundsException e) {
      this.configuration.generateLevel(currentLevel);
      this.configuration.toFile();

      Moon moon = this.configuration.getMoonMap(currentLevel);
      this.landingHeight = moon.getScaledLandingHeight();
      this.moonSurface = new Polygon();
      moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());
    }

    Group group = new Group();
    group.getChildren().addAll(moonSurface, landerModel.landerGroup);

    this.gamePane = new Pane();
    gamePane.getChildren().add(group);

    Translate trPaneCenter = new Translate();

    Scale scale = new Scale();
    scale.setPivotX(0);
    scale.setPivotY(0);
    double aspectRatio = 800.0/650.0;

    moonSurface.setFill(Color.LIGHTGRAY);
    this.gamePane.setStyle("-fx-background-color: black;");

    this.gamePane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
      double _width = newSceneWidth.doubleValue();
      double _height = this.gamePane.heightProperty().doubleValue();
      if (_width/_height < aspectRatio) {
        scale.setX(newSceneWidth.doubleValue()/800);
        scale.setY(newSceneWidth.doubleValue()/800);
        double _trOffset = (_height - _width/aspectRatio)/2.0;
        if (_trOffset < 0) _trOffset = 0;
        trPaneCenter.setY(_trOffset);
      }
    });
    this.gamePane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
      double _width = this.gamePane.widthProperty().doubleValue();
      double _height = newSceneHeight.doubleValue();
      if (_width/_height >= aspectRatio) {
        scale.setX(newSceneHeight.doubleValue()/650);
        scale.setY(newSceneHeight.doubleValue()/650);
        double _trOffset = (_width - _height*aspectRatio)/2.0;
        if (_trOffset < 0) _trOffset = 0;
        trPaneCenter.setX(_trOffset);
      }
    });

    group.getTransforms().addAll(scale, trPaneCenter);

    TranslateTransition vertical = new TranslateTransition(Duration.millis(32), landerModel.landerGroup);
    vertical.setInterpolator(Interpolator.LINEAR);

    RotateTransition leftRotate = new RotateTransition(Duration.millis(32), landerModel.landerGroup);
    RotateTransition rightRotate = new RotateTransition(Duration.millis(32), landerModel.landerGroup);

    KeyFrame keyframe = new KeyFrame(Duration.millis(32), event -> {

      landerModel.xCoord = landerModel.landerGroup.getLayoutX() + landerModel.landerGroup.getTranslateX();
      landerModel.yCoord = landerModel.landerGroup.getLayoutY() + landerModel.landerGroup.getTranslateY();

      if (isLeftRotate() && !isRightRotate() && landerModel.angle >= -150) {
        leftRotate.setAxis(Rotate.Z_AXIS);
        leftRotate.setByAngle(-4);
        landerModel.angle = landerModel.angle-4;
        leftRotate.setAutoReverse(false);
        leftRotate.play();
      }
      if (isRightRotate() && !isLeftRotate() && landerModel.angle <= 150) {
        rightRotate.setAxis(Rotate.Z_AXIS);
        rightRotate.setByAngle(4);
        landerModel.angle = landerModel.angle+4;
        rightRotate.setAutoReverse(false);
        rightRotate.play();
      }

      if(isThrustON() && landerModel.fuel > 0) {
        landerModel.ax = Math.sin(landerModel.angle * (Math.PI / 180)) * 0.1;
        landerModel.ay = Math.cos(landerModel.angle * (Math.PI / 180)) * 0.2;
        landerModel.vy = landerModel.vy + g - landerModel.ay;
        landerModel.vx = landerModel.vx + landerModel.ax;
        landerModel.fuel = landerModel.fuel-0.25;
        landerModel.setFlameImage(Lander.FlameImageType.FLAME);
      } else {
        landerModel.vy = landerModel.vy + g;
        landerModel.setFlameImage(Lander.FlameImageType.NO_FLAME);
      }

      if(!isThrustON() || landerModel.fuel == 0) {
        landerModel.setFlameImage(Lander.FlameImageType.NO_FLAME);
      }

      landerModel.v = Math.sqrt(Math.pow(landerModel.vx, 2)+Math.pow(landerModel.vy, 2));

      this.gamePane.fireEvent(new SidePane.UpdateLanderInfoEvent(
            landerModel.fuel,
            landerModel.v,
            this.landingHeight - landerModel.getBottomCoord(),
            landerModel.ships,
            currentLevel));
      // TODO: DISPLAYING SCORE

      if (((Path)Shape.intersect(landerModel.lander, this.moonSurface)).getElements().size() > 0) {
        if (landerModel.v >= 1.5) {
          landerModel.ships-= 1;

          this.gamePane.fireEvent(new SidePane.UpdateLanderInfoEvent(
            landerModel.fuel,
            landerModel.v,
            this.landingHeight - landerModel.getBottomCoord(),
            landerModel.ships,
            currentLevel));

          landerModel.vx = 3;
          landerModel.vy = 0;

          landerModel.xCoord = 0.0;
          landerModel.yCoord = 0.0;
          landerModel.landerGroup.setTranslateX(0.0);
          landerModel.landerGroup.setTranslateY(0.0);

          if (landerModel.ships == 0) {
            landerModel.landerGroup.setVisible(false);
            loseGame();
            timeline.stop();
          }

        } else {

          landerModel.vx = 3;
          landerModel.vy = 0;

          landerModel.xCoord = 0.0;
          landerModel.yCoord = 0.0;
          landerModel.landerGroup.setTranslateX(0.0);
          landerModel.landerGroup.setTranslateY(0.0);
          this.gamePane.fireEvent(new GameWindow.ChangeLevelEvent());
          //timeline.stop();
        }
      }

      vertical.setByY(landerModel.vy);
      vertical.setByX(landerModel.vx);
      vertical.stop();
      vertical.play();
    });

    timeline.getKeyFrames().add(keyframe);
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  /*
   * Event handling in methods below
   * Event triggering in GamePane setOnKeyPressed and setOnKeyReleased.
   */
  public void startLanderThrustOn() { this.isThrustON = true; }

  public void stopLanderThrustOn() { this.isThrustON = false; }

  public void startRotateLanderClockwise() { this.isRightRotate = true; }

  public void stopRotateLanderClockwise() { this.isRightRotate = false; }

  public void startRotateLanderAnticlockwise() { this.isLeftRotate = true; }

  public void stopRotateLanderAnticlockwise() { this.isLeftRotate = false; }

  public void pauseGame() {
    this.isPaused = true;
    this.timeline.pause();
  }

  public void unpauseGame() {
    this.isPaused = false;
    this.timeline.play();
  }

  public void loseGame() {
    this.isLost = true;
  }

  /**
   * Getter used in ApplicationWindow class in order to put
   * prepared Pane in a Scene.
   *
   * @return gamePane - Pane presenting game view.
   */
  public Pane getGamePane() { return gamePane; }

  public boolean isLeftRotate() { return isLeftRotate; }

  public boolean isRightRotate() { return isRightRotate; }

  public boolean isThrustON() { return isThrustON; }

  public boolean isPaused() { return isPaused; }

  public boolean isLost() { return isLost; }


  private Pane gamePane;
  private Timeline timeline = new Timeline();
  private Polygon moonSurface;
  private double landingHeight;

  private boolean isLeftRotate = false;
  private boolean isRightRotate = false;
  private boolean isThrustON = false;
  private boolean isPaused = false;
  private boolean isLost = false;

  private double g = 0.1;

  private Configuration configuration;

  //TODO: Nitro flame
}
