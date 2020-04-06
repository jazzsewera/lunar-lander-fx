package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage stage) {

    ApplicationWindow window = new ApplicationWindow();
    stage.setScene(window.getMainGameScene());
    stage.setTitle("Lunar Lander");
    stage.setWidth(280);
    stage.setHeight(600);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);

    Configuration conf = new Configuration();
    Double[] coords = conf.calculateSurfacePoints(640,480, 2);

    conf.toFile(coords);

    Double[] coords2 = conf.fromFile("src/main/resources/lunarlander/configuration.json");
  }

}
