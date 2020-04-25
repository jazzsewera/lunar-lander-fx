package lunarlander;

import javafx.application.Application;
// import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage stage) {

    GameWindow window = new GameWindow();
    stage.setScene(window.getMainGameScene());
    stage.setTitle("Lunar Lander");
    stage.setWidth(1080);
    stage.setHeight(630);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
