package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
// unused
// import javafx.scene.control.Label;
// import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.Group;
import java.util.Random;

public class Main extends Application {



  @Override
  public void start(Stage stage) {

    Random rand = new Random();
    double heightLimit = 200D;

    // Creating new object of Polygon class
    Polygon moonSurface = new Polygon();

    // 480.0 needs to be changed to some sort of
    // pane.height and [0.0, 50.0, 75.0] need to be
    // changed to [0.0*pane.width, 0.05*pane.width, ...]

    double height = 480;
    double width = 640;

    // we need those values to set how favorable created
    // moon surface will be. those 2 are only to test how it works
    // later it will be more complicated system
    double highVarianceWidth = 0.008 +  (0.08 - 0.08); // this one will give us
                                                        // wider spaces
    double lowVarianceWidth = 0.008 + (0.03 - 0.08); // and this one will give us
                                                      // tighter spaces

    double highVarianceHeight = 0.02 + (0.3125 - 0.02); // higher obstacles
    double lowVarianceHeight = 0.02 + (0.1 - 0.02); // lower obstacles


    // Coords of points creating our shape
    // todo: how can i bind NEXT coord with PREVIOUS coord?
    moonSurface.getPoints().addAll(new Double[]{
      0.0, height,
      rand.nextDouble() * lowVarianceWidth, 480.0 - rand.nextDouble() * highVarianceHeight,
      rand.nextDouble() * lowVarianceWidth, 480.0 - 25.0,
      rand.nextDouble() * highVarianceWidth, 480.0 - 75.0,
      rand.nextDouble() * highVarianceWidth, 480.0 - 50.0,
      rand.nextDouble() * highVarianceWidth, 480.0 - 50.0,
      rand.nextDouble() * highVarianceWidth, 480.0 - 150.0,
      rand.nextDouble() * lowVarianceWidth, 480.0 - 25.0,
      rand.nextDouble() * lowVarianceWidth, 480.0 - rand.nextDouble()*heightLimit,
      rand.nextDouble() * lowVarianceWidth, 480.0 - rand.nextDouble()*heightLimit,
      rand.nextDouble() * lowVarianceWidth, 480.0 - 100.0,
      rand.nextDouble() * lowVarianceWidth, 480.0 - 100.0,
      rand.nextDouble() * lowVarianceWidth, 480.0 - rand.nextDouble()*heightLimit,
      rand.nextDouble() * lowVarianceWidth, 480.0 - rand.nextDouble()*heightLimit,
      440.0, 480.0 - rand.nextDouble()*heightLimit,
      490.0, 480.0 - rand.nextDouble()*heightLimit,
      510.0, 480.0 - 150.0,
      570.0, 480.0 - 150.0,
      610.0, 480.0 - rand.nextDouble()*heightLimit,
      640.0, 480.0 - rand.nextDouble()*heightLimit,
      width, height,
    });

    // create a group
    Group root = new Group(moonSurface);

    // setting color of our shape
    moonSurface.setFill(Color.DARKBLUE);

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args) {
    launch();
  }

}
