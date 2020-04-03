package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) {

    GameWindow window = new GameWindow();
    Scene scene = new Scene(window.getMoonSurfacePane(), 640, 480);
    stage.setScene(scene);
    stage.setTitle("Mooooooooooooooooooooooooon");
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
