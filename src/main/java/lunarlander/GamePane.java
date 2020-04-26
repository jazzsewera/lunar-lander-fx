package lunarlander;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    Polygon landerON = new Polygon();

    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());
    landerON.getPoints().addAll(
      0.0, 0.0,
      0.0, 25.0,
      25.0, 25.0,
      25.0, 0.0);

    this.landerPane = new Pane(landerON);
    landerON.setFill(new ImagePattern(lander.getLanderImage()));


    this.moonSurfacePane = new Pane(moonSurface);
    moonSurface.setFill(Color.LIGHTGRAY);
    moonSurfacePane.setStyle("-fx-background-color: black;");

    this.gamePane = new AnchorPane();
    gamePane.getChildren().addAll(moonSurfacePane, landerPane);
    AnchorPane.setTopAnchor(moonSurfacePane, 0.0);
    AnchorPane.setLeftAnchor(moonSurfacePane, 0.0);
    AnchorPane.setBottomAnchor(moonSurfacePane, 0.0);
    AnchorPane.setRightAnchor(moonSurfacePane, 0.0);

    AnchorPane.setLeftAnchor(landerPane, 300.0); // Distance from the left border of a window to lander
    AnchorPane.setTopAnchor(landerPane, 150.0);  // Distance from the top border of a window to lander

    this.moonSurfacePane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
      System.out.println("Width: " + newSceneWidth);
      moon.recalculateWidth(newSceneWidth.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });
    this.moonSurfacePane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
      System.out.println("Height: " + newSceneHeight);
      moon.recalculateHeight(newSceneHeight.doubleValue());
      moonSurface.getPoints().setAll(moon.getMoonSurfacePoints());
    });

  }

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
  public AnchorPane getGamePane() {
    return gamePane;
  }


  private final Pane moonSurfacePane;
  private Pane landerPane;
  private AnchorPane gamePane;
}
