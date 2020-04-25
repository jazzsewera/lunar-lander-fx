package lunarlander;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
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
    landerON.getPoints().addAll(50.0, 50.0,
      75.0, 50.0,
      50.0, 75.0,
      75.0, 75.0);

    this.landerPane = new Pane(landerON);
    landerON.setFill(new ImagePattern(lander.getLanderImage()));
    landerPane.setStyle("-fx-background-color: blue;");


    this.moonSurfacePane = new Pane(moonSurface);
    moonSurface.setFill(Color.LIGHTGRAY);
    moonSurfacePane.setStyle("-fx-background-color: black;");

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


  private final Pane moonSurfacePane;
  private Pane landerPane;
}
