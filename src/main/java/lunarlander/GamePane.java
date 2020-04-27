package lunarlander;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
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
    Lander lander = new Lander(250, 250, 0, 100);

    Polygon moonSurface = new Polygon();

    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());

    Group group = new Group();
    group.getChildren().addAll(moonSurface, lander.lander);

    this.gamePane = new Pane();
    gamePane.getChildren().add(group);

    this.moonSurfacePane = new Pane();
    moonSurface.setFill(Color.LIGHTGRAY);
    //here i would do it with getter not with public object
    lander.lander.setFill(new ImagePattern(lander.getLanderOFFImage()));
    this.gamePane.setStyle("-fx-background-color: black;");

/*
 *     this.landerPane = new Pane(landerON);
 *     landerON.setFill(new ImagePattern(lander.getLanderImage()));
 *
 *
 *     this.moonSurfacePane = new Pane(moonSurface);
 *     moonSurface.setFill(Color.LIGHTGRAY);
 *     moonSurfacePane.setStyle("-fx-background-color: black;");
 *
 *     this.gamePane = new AnchorPane();
 *     gamePane.getChildren().addAll(moonSurfacePane, landerON);
 *     AnchorPane.setTopAnchor(moonSurfacePane, 0.0);
 *     AnchorPane.setLeftAnchor(moonSurfacePane, 0.0);
 *     AnchorPane.setBottomAnchor(moonSurfacePane, 0.0);
 *     AnchorPane.setRightAnchor(moonSurfacePane, 0.0);
 *
 *     AnchorPane.setLeftAnchor(landerON, 300.0); // Distance from the left border of a window to lander
 *     AnchorPane.setTopAnchor(landerON, 150.0);  // Distance from the top border of a window to lander
 */

    this.gamePane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
      moon.recalculateWidth(newSceneWidth.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });
    this.gamePane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
      moon.recalculateHeight(newSceneHeight.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });

    RotateTransition leftRotate = new RotateTransition(Duration.millis(32), lander.lander);
    leftRotate.setAxis(Rotate.Z_AXIS);
    leftRotate.setByAngle(-45);
    leftRotate.setAutoReverse(false);
    leftRotate.stop();

    RotateTransition rightRotate = new RotateTransition(Duration.millis(32), lander.lander);
    rightRotate.setAxis(Rotate.Z_AXIS);
    rightRotate.setByAngle(45);
    rightRotate.setAutoReverse(false);
    rightRotate.stop();

  }
  private EventHandler<KeyEvent> inputReleased = new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent event) {
      if (event.getCode() == KeyCode.LEFT) {


      } else if (event.getCode() == KeyCode.RIGHT) {

      }
    }
  };

  /**
   * Getter used in ApplicationWindow class in order to put
   * prepared Pane in a Scene.
   *
   * @return moonSurfacePane - Pane presenting a surface of chosen Moon.
   */
  public Pane getMoonSurfacePane() {
    return moonSurfacePane;
  }
  public Pane getLanderPane() {
    return landerPane;
  }
  public Pane getGamePane() {
    return gamePane;
  }


  private final Pane moonSurfacePane;
  private Pane landerPane;
  private Pane gamePane;

  private boolean isLeftRotate = false;
  private boolean isRightRotate = false;
  private boolean freeFall = false;

}
