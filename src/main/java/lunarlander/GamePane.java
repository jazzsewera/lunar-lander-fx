package lunarlander;

import javafx.scene.paint.Color;
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
    Polygon moonSurface = new Polygon();

    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());

    this.moonSurfacePane = new Pane(moonSurface);
    moonSurface.setFill(Color.LIGHTGRAY);
    moonSurfacePane.setStyle("-fx-background-color: black;");
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

  private final Pane moonSurfacePane;
}
