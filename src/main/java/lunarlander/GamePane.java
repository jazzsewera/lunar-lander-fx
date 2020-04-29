package lunarlander;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
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
  public GamePane() {
    Configuration configuration = new Configuration();

    /*
     * for (int i = 1; i <= 3; i++) {
     *   configuration.generateLevel(i);
     * }
     * configuration.toFile();
     */

    configuration.fromFile("src/main/resources/lunarlander/configuration.json");

    Moon moon = configuration.getMoonMap(3);
    Polygon moonSurface = new Polygon();
    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());

    Group group = new Group();
    group.getChildren().addAll(moonSurface, landerModel.landerGroup);

    this.gamePane = new Pane();
    gamePane.getChildren().add(group);

    moonSurface.setFill(Color.LIGHTGRAY);
    this.gamePane.setStyle("-fx-background-color: black;");

    this.gamePane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
      moon.recalculateWidth(newSceneWidth.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });
    this.gamePane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
      moon.recalculateHeight(newSceneHeight.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });


    TranslateTransition vertical = new TranslateTransition(Duration.millis(32), landerModel.landerGroup);
    vertical.setInterpolator(Interpolator.LINEAR);

    RotateTransition leftRotate = new RotateTransition(Duration.millis(32), landerModel.landerGroup);
    RotateTransition rightRotate = new RotateTransition(Duration.millis(32), landerModel.landerGroup);

    KeyFrame keyframe = new KeyFrame(Duration.millis(32), event -> {

      if (isLeftRotate() && !isRightRotate() &&landerModel.getAngle() >= -150) {
        leftRotate.setAxis(Rotate.Z_AXIS);
        leftRotate.setByAngle(-4);
        landerModel.setAngle(landerModel.getAngle()-4);
        leftRotate.setAutoReverse(false);
        leftRotate.play();
      }
      if (isRightRotate() && !isLeftRotate() &&landerModel.getAngle() <= 150) {
        rightRotate.setAxis(Rotate.Z_AXIS);
        rightRotate.setByAngle(4);
        landerModel.setAngle(landerModel.getAngle()+4);
        rightRotate.setAutoReverse(false);
        rightRotate.play();
      }

      if(isThrustON() && landerModel.getFuel() > 0) {
        landerModel.setAx(Math.sin(landerModel.getAngle() * (Math.PI / 180)) * 0.1);
        landerModel.setAy(Math.cos(landerModel.getAngle() * (Math.PI / 180)) * 0.2);
        landerModel.setVy(landerModel.getVy() + g - landerModel.getAy());
        landerModel.setVx(landerModel.getVx() + landerModel.getAx());
        landerModel.setFuel(landerModel.getFuel()-0.25);
        landerModel.setFlameImage(Lander.FlameImageType.FLAME);
        System.out.println(landerModel.getFuel());
      } else {
        landerModel.setVy(landerModel.getVy() + g);
        landerModel.setFlameImage(Lander.FlameImageType.NO_FLAME);
      }

      if(!isThrustON() || landerModel.getFuel() == 0) {
        landerModel.setFlameImage(Lander.FlameImageType.NO_FLAME);
      }

      vertical.setByY(landerModel.getVy());
      vertical.setByX(landerModel.getVx());
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

  private Pane gamePane;
  private Lander landerModel = new Lander(250, 250, 0.5, 0, 0, 100);
  private Timeline timeline = new Timeline();

  private boolean isLeftRotate = false;
  private boolean isRightRotate = false;
  private boolean isThrustON = false;
  private double g = 0.1;

  //TODO DUŻY NIEBIESKI SNOP
}
