package lunarlander;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
// unused
// import javafx.scene.control.Label;
// import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.util.Arrays;
import java.util.Random;
import javafx.beans.value.ObservableValue;

public class Main extends Application {

  @Override
  public void start(Stage stage) {

    Moon moon = new Moon();

    // Creating new object of Polygon class
    Polygon moonSurface = new Polygon();

    moonSurface.getPoints().addAll(moon.getMoonSurfacePoints());

    // create a pane (it is resizable, group is not!)
    Pane root = new Pane(moonSurface);

    // setting color of our shape
    moonSurface.setFill(Color.DARKBLUE);

    Scene scene = new Scene(root, 640, 480);

    /*
    root.widthProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double moonSurfaceStartX = (newValue.doubleValue()/2)-(width);
        root.setLayoutX(moonSurfaceStartX);
      }
    });

    root.heightProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double moonSurfaceStartY = (newValue.doubleValue()/2)-(height);
        root.setLayoutY(moonSurfaceStartY);
      }
    });*/

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
