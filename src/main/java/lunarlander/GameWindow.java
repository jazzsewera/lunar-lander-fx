package lunarlander;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
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

  public static class ChangeLevelEvent extends Event {
    ChangeLevelEvent() {
      super(CHANGE_LEVEL);
    }

    public static final EventType<GameWindow.ChangeLevelEvent> CHANGE_LEVEL = new EventType<>("CHANGE_LEVEL");

    public static final long serialVersionUID = 12345;
  }

  /**
   * Constructor setting up all the necessary components of
   * the Game Window.
   *
   * @param configuration set of config files and methods working on them: Configuration
   * @param width initial window width : double
   * @param height initial window height : double
   */
  public GameWindow(Configuration configuration, double width, double height) {
    this.lander = new Lander(0, 0, 3, 0, 0, 400, 3, 0);

    this.currentLevel = 1;

    this._gamePane = new GamePane(configuration, lander, currentLevel, width-280.0, height);
    this._sidePane = new SidePane(configuration, lander);

    // Pane moonSurfacePane = gamePane.getMoonSurfacePane();
    // Pane landerPane = gamePane.getLanderPane();
    this.sideBorderPane = _sidePane.getSideBorderPane();
    this.gamePane = _gamePane.getGamePane();

    this.applicationWindowHbox = new HBox();
    this.applicationWindowHbox.getChildren().addAll(
      this.gamePane,
      this.sideBorderPane
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
        if(!_gamePane.isPaused() && !_gamePane.isLost()) {
          _gamePane.pauseGame();
          _sidePane.headLabelPaused();
        } else if (!_gamePane.isLost()) {
          _gamePane.unpauseGame();
          _sidePane.headLabelUnpaused(5);
        }
      }
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
    });

    this.mainGameScene.addEventHandler(SidePane.UpdateLanderInfoEvent.UPDATE_INFO, (event) -> {
      _sidePane.updateSidePane(event.fuelState, event.currentVelocity, event.altitude, event.shipsLeft, event.level, event.score);
    });

    this.mainGameScene.addEventHandler(ChangeLevelEvent.CHANGE_LEVEL, (event) -> {
      this.currentLevel++;
      this.fuelLeft = this.lander.fuel;
      this.shipsLeft = this.lander.ships;
      this.currentScore = this.lander.score;
      this.lander = new Lander(0, 0, 3.0, 0, 0, fuelLeft, shipsLeft, currentScore);

      double _gamePaneWidth = this.mainGameScene.getWidth() - 280;
      double _gamePaneHeight = this.mainGameScene.getHeight();

      this._gamePane = new GamePane(configuration, lander, currentLevel, _gamePaneWidth, _gamePaneHeight);
      this.gamePane = this._gamePane.getGamePane();

      this.applicationWindowHbox.getChildren().clear();

      this.applicationWindowHbox.getChildren().addAll(
          this.gamePane,
          this.sideBorderPane);
      HBox.setHgrow(this.gamePane, Priority.ALWAYS);
      HBox.setHgrow(this.sideBorderPane, Priority.NEVER);

      this.mainGameScene.setRoot(this.applicationWindowHbox);
      this.applicationWindowHbox.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.GAME));
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

  private Scene mainGameScene;
  Pane gamePane;
  Pane sideBorderPane;

  HBox applicationWindowHbox;

  GamePane _gamePane;
  SidePane _sidePane;
  Lander lander;

  int currentLevel;
  double fuelLeft;
  int shipsLeft;
  int currentScore;
}
