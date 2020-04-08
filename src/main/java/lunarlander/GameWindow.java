package lunarlander;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Main game window class gathering gamePane and sidePane into
 * a single root pane that can be rendered in the stage.
 *
 * @author Błażej Sewera
 * @author Mateusz Winnicki
 */
public class GameWindow {

  /**
   * Constructor setting up all the necessary components of
   * the Game Window.
   */
  public GameWindow() {
    GamePane gamePane = new GamePane();
    SidePane sidePane = new SidePane();

    Pane moonSurfacePane = gamePane.getMoonSurfacePane();
    Pane sideBorderPane = sidePane.getSideBorderPane();

    HBox applicationWindowHbox = new HBox();
    applicationWindowHbox.getChildren().addAll(
      moonSurfacePane,
      sideBorderPane
    );

    HBox.setHgrow(moonSurfacePane, Priority.ALWAYS);
    HBox.setHgrow(sideBorderPane, Priority.NEVER);

    this.mainGameScene = new Scene(applicationWindowHbox);

    this.mainGameScene.getStylesheets().add("lunarlander/css/style.css");

    final KeyCombination ctrlQuit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    this.mainGameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    this.mainGameScene.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.UP) sidePane.setKbdUpPressed();
      if (event.getCode() == KeyCode.LEFT) sidePane.setKbdLeftPressed();
      if (event.getCode() == KeyCode.RIGHT) sidePane.setKbdRightPressed();
      // Only for presentation purposes
      if (event.getCode() == KeyCode.L) sidePane.setLevel(5);
    });
    this.mainGameScene.setOnKeyReleased(event -> {
      if (event.getCode() == KeyCode.UP) sidePane.setKbdUpReleased();
      if (event.getCode() == KeyCode.LEFT) sidePane.setKbdLeftReleased();
      if (event.getCode() == KeyCode.RIGHT) sidePane.setKbdRightReleased();
      // Only for presentation purposes
      if (event.getCode() == KeyCode.L) sidePane.setLevel(2);
    });
  }

  /**
   * Method to get an actual scene that contains the root pane
   * and can be rendered in the window.
   *
   * @return mainGameScene, {@link Scene}
   */
  public Scene getMainGameScene() {
    return mainGameScene;
  }

  private final Scene mainGameScene;
}
