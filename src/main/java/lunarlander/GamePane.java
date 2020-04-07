package lunarlander;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;

public class GamePane {

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

  public Pane getMoonSurfacePane() {
    return moonSurfacePane;
  }

  private final Pane moonSurfacePane;
}
