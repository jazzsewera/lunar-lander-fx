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
    setLander(landerModel.lander);
    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());

    Group group = new Group();
    group.getChildren().addAll(moonSurface, lander);

    this.gamePane = new Pane();
    gamePane.getChildren().add(group);

    moonSurface.setFill(Color.LIGHTGRAY);
    lander.setFill(new ImagePattern(landerModel.getLanderImage()));
    this.gamePane.setStyle("-fx-background-color: black;");

    this.gamePane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
      moon.recalculateWidth(newSceneWidth.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });
    this.gamePane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
      moon.recalculateHeight(newSceneHeight.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });


    TranslateTransition vertical = new TranslateTransition(Duration.millis(32), getLander());
    vertical.setInterpolator(Interpolator.LINEAR);

    RotateTransition leftRotate = new RotateTransition(Duration.millis(32), getLander());
    RotateTransition rightRotate = new RotateTransition(Duration.millis(32), getLander());

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
        setLander(landerModel.lander);
        lander.setFill(new ImagePattern(landerModel.getLanderImage()));
        landerModel.setAx(Math.sin(landerModel.getAngle() * (Math.PI / 180)) * 0.1);
        landerModel.setAy(Math.cos(landerModel.getAngle() * (Math.PI / 180)) * 0.2);
        landerModel.setVy(landerModel.getVy() + g - landerModel.getAy());
        landerModel.setVx(landerModel.getVx() + landerModel.getAx());
        landerModel.setFuel(landerModel.getFuel()-0.25);
        System.out.println(landerModel.getFuel());
      } else {
        landerModel.setVy(landerModel.getVy() + g);
      }

      if(!isThrustON() || landerModel.getFuel() == 0) {
        setLander(landerModel.lander);
        lander.setFill(new ImagePattern(landerModel.getLanderImage()));
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

  public Polygon getLander() { return lander; }

  public void setLander(Polygon lander) { this.lander = lander; }


  private Pane gamePane;
  private Polygon lander;
  Lander landerModel = new Lander(250, 250, 0.5, 0, 0, 100);
  private Timeline timeline = new Timeline();

  private boolean isLeftRotate = false;
  private boolean isRightRotate = false;
  private boolean isThrustON = false;
  private double g = 0.1;

  //TODO DUŻY NIEBIESKI SNOP
}
