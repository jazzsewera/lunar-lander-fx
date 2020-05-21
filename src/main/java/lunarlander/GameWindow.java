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
  public GameWindow(Configuration configuration) {
    Lander lander = new Lander(0, 0, 0.1, 0, 0, 400);

    GamePane _gamePane = new GamePane(configuration, lander);
    SidePane _sidePane = new SidePane(configuration, lander);

    // Pane moonSurfacePane = gamePane.getMoonSurfacePane();
    // Pane landerPane = gamePane.getLanderPane();
    Pane sideBorderPane = _sidePane.getSideBorderPane();
    Pane gamePane = _gamePane.getGamePane();

    HBox applicationWindowHbox = new HBox();
    applicationWindowHbox.getChildren().addAll(
      gamePane,
      sideBorderPane
    );

    HBox.setHgrow(gamePane, Priority.ALWAYS);
    HBox.setHgrow(sideBorderPane, Priority.NEVER);

    this.mainGameScene = new Scene(applicationWindowHbox);

    this.mainGameScene.getStylesheets().add("lunarlander/css/style.css");

    final KeyCombination ctrlQuit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    this.mainGameScene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    this.mainGameScene.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.UP) {
        _sidePane.setKbdUpPressed();
        _gamePane.startLanderThrustOn();
      }
      if (event.getCode() == KeyCode.LEFT) {
        _sidePane.setKbdLeftPressed();
        _gamePane.startRotateLanderAnticlockwise();
      }
      if (event.getCode() == KeyCode.RIGHT) {
        _sidePane.setKbdRightPressed();
        _gamePane.startRotateLanderClockwise();
      }
      if (event.getCode() == KeyCode.ESCAPE) {
        if(!_gamePane.isPaused()) {
          _gamePane.pauseGame();
          _sidePane.headLabelPaused();
          System.out.println("PAUSED");
        } else {
          _gamePane.unpauseGame();
          _sidePane.headLabelUnpaused(5);
          System.out.println("UNPAUSED");
        }
      }
      // Only for presentation purposes
      if (event.getCode() == KeyCode.L) _sidePane.setLevel(5);
    });
    this.mainGameScene.setOnKeyReleased((event) -> {
      if (event.getCode() == KeyCode.UP) {
        _sidePane.setKbdUpReleased();
        _gamePane.stopLanderThrustOn();
      }
      if (event.getCode() == KeyCode.LEFT) {
        _sidePane.setKbdLeftReleased();
        _gamePane.stopRotateLanderAnticlockwise();
      }
      if (event.getCode() == KeyCode.RIGHT) {
        _sidePane.setKbdRightReleased();
        _gamePane.stopRotateLanderClockwise();
      }
      // Only for presentation purposes
      if (event.getCode() == KeyCode.L) _sidePane.setLevel(2);
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
