package lunarlander;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage stage) {

    BorderPane sidePane = SidePane.createSidePane();

    Scene scene = new Scene(sidePane);

    scene.getStylesheets().add("lunarlander/css/style.css");

    final KeyCombination ctrlQuit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    stage.setScene(scene);
    stage.setTitle("Lunar Lander");
    stage.setWidth(280);
    stage.setHeight(600);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
