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
    moonSurface.setFill(Color.LIGHTGRAY);

    double width = 640;
    double height = 480;
    Scene scene = new Scene(root, width, height);

    scene.setFill(Color.BLACK);

    // listener that checks width and height after window resizing
/*
 *     ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
 *       System.out.println(stage.getWidth() + ", " + stage.getHeight());
 *
 *     stage.widthProperty().addListener(stageSizeListener);
 *     stage.heightProperty().addListener(stageSizeListener);
 */

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
