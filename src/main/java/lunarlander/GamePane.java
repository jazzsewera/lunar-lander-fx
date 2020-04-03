package lunarlander;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;

public class GamePane {

  public GamePane() {
    Moon moon = new Moon();
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
