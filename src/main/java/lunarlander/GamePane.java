package lunarlander;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.AnchorPane;
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
    setLanderOFF(lander.lander);

    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());

    Group group = new Group();
    group.getChildren().addAll(moonSurface, landerOFF);

    this.gamePane = new Pane();
    gamePane.getChildren().add(group);

    moonSurface.setFill(Color.LIGHTGRAY);
    //here i would do it with getter not with public object
    lander.lander.setFill(new ImagePattern(lander.getLanderOFFImage()));
    this.gamePane.setStyle("-fx-background-color: black;");

    this.gamePane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
      moon.recalculateWidth(newSceneWidth.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });
    this.gamePane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
      moon.recalculateHeight(newSceneHeight.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });

    RotateTransition leftRotate = new RotateTransition(Duration.millis(32), getLanderOFF());
    RotateTransition rightRotate = new RotateTransition(Duration.millis(32), getLanderOFF());

    KeyFrame keyframe = new KeyFrame(Duration.millis(32), event -> {
      if (isLeftRotate() && lander.getAngle() >= -180) {
        leftRotate.setAxis(Rotate.Z_AXIS);
        leftRotate.setByAngle(-4);
        lander.setAngle(lander.getAngle()-4);
        leftRotate.setAutoReverse(false);
        leftRotate.play();
      }
      if (isRightRotate() && lander.getAngle() <= 180) {
        rightRotate.setAxis(Rotate.Z_AXIS);
        rightRotate.setByAngle(4);
        lander.setAngle(lander.getAngle()+4);
        rightRotate.setAutoReverse(false);
        rightRotate.play();
      }
    });
    timeline.getKeyFrames().add(keyframe);

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  /*
   * Event handling in methods below
   * Event triggering in GamePane setOnKeyPressed and setOnKeyReleased.
   */
  public void startLanderThrustOn() {
    System.out.println("lander thrust on");
  }
  public void stopLanderThrustOn() {
    System.out.println("lander thrust off");
  }

  public void startRotateLanderClockwise() {
    this.isRightRotate = true;
  }

  public void stopRotateLanderClockwise() {
    isRightRotate = false;
  }

  public void startRotateLanderAnticlockwise() {
    this.isLeftRotate = true;
  }

  public void stopRotateLanderAnticlockwise() {
    isLeftRotate = false;
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

  public Polygon getLanderOFF() { return landerOFF; }

  public Polygon getLanderON() { return landerON; }

  public void setLanderOFF(Polygon landerOFF) { this.landerOFF = landerOFF; }

  public void setLanderON(Polygon landerON) { this.landerON = landerON; }


  private Pane gamePane;

  private Polygon landerOFF;
  private Polygon landerON;

  Lander lander = new Lander(250, 250, 0, 100);

  RotateTransition leftRotate = new RotateTransition(Duration.millis(32), getLanderOFF());
  RotateTransition rightRotate = new RotateTransition(Duration.millis(32), getLanderOFF());
  private boolean isLeftRotate = false;
  private boolean isRightRotate = false;
  private boolean freeFall = false;

  private Timeline timeline = new Timeline();
}
