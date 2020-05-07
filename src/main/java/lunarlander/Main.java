package lunarlander;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  public static class ChangeToGameEvent extends Event {
    ChangeToGameEvent() {
      super(CHANGE_TO_GAME);
    }

    public static final EventType<ChangeToGameEvent> CHANGE_TO_GAME = new EventType<>("CHANGE_TO_GAME");

    public static final long serialVersionUID = 1234;
  }

  @Override
  public void start(Stage stage) {
    MainMenuWindow _mainMenuWindow = new MainMenuWindow();
    GameWindow _gameWindow = new GameWindow();
    this.mainMenuScene = _mainMenuWindow.getMainMenuScene();
    this.gameWindowScene = _gameWindow.getMainGameScene();

    this.mainScene = this.mainMenuScene;
    // setMainScene(mainMenuScene);
    // setMainScene(gameWindowScene);

    this.mainScene.addEventHandler(ChangeToGameEvent.CHANGE_TO_GAME, event -> {
      stage.setScene(this.gameWindowScene);
    });

    stage.setScene(this.mainMenuScene);
    stage.setTitle("Lunar Lander");
    stage.setWidth(1080);
    stage.setHeight(630);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private Scene mainScene;
  private Scene mainMenuScene;
  private Scene gameWindowScene;
}
