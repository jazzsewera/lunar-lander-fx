package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.scene.Group;
import java.util.Random;

public class Main extends Application {



  @Override
  public void start(Stage stage) {

    Random rand = new Random();
    double heightLimit = 200D;
    Polyline polyline = new Polyline();

    polyline.getPoints().addAll(new Double[]{
      0.0, 480.0 - 25.0,
      50.0, 480.0 - 25.0,
      75.0, 480.0 - 75.0,
      100.0, 480.0 - 50.0,
      150.0, 480.0 - 50.0,
      175.0, 480.0 - 150.0,
      200.0, 480.0 - 25.0,
      225.0, 480.0 - rand.nextDouble()*heightLimit,
      250.0, 480.0 - rand.nextDouble()*heightLimit,
      300.0, 480.0 - 100.0,
      325.0, 480.0 - 100.0,
      350.0, 480.0 - rand.nextDouble()*heightLimit,
      380.0, 480.0 - rand.nextDouble()*heightLimit,
      440.0, 480.0 - rand.nextDouble()*heightLimit,
      490.0, 480.0 - rand.nextDouble()*heightLimit,
      510.0, 480.0 - 150.0,
      570.0, 480.0 - 150.0,
      610.0, 480.0 - rand.nextDouble()*heightLimit,
      640.0, 480.0 - rand.nextDouble()*heightLimit,
    });
    Group root = new Group(polyline);

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args) {
    launch();
  }

}
