package lunarlander;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  public static enum SceneType {
    MAIN_MENU,
    GAME,
    SCORES,
    RULES
  }

  public static class ChangeSceneEvent extends Event {
    ChangeSceneEvent(SceneType sceneType) {
      super(CHANGE_SCENE);
      this.sceneType = sceneType;
    }

    public SceneType getSceneType() { return this.sceneType; }

    private SceneType sceneType;

    public static final EventType<ChangeSceneEvent> CHANGE_SCENE = new EventType<>("CHANGE_SCENE");

    public static final long serialVersionUID = 1234;
  }

  @Override
  public void start(Stage stage) {
    MainMenuWindow _mainMenuWindow = new MainMenuWindow();
    GameWindow _gameWindow = new GameWindow();
    RulesWindow _rulesWindow = new RulesWindow();
    this.mainMenuScene = _mainMenuWindow.getMainMenuScene();
    this.gameWindowScene = _gameWindow.getMainGameScene();
    this.rulesScene = _rulesWindow.getRulesScene();

    this.mainScene = this.mainMenuScene;
    // setMainScene(mainMenuScene);
    // setMainScene(gameWindowScene);

    this.mainScene.addEventHandler(ChangeSceneEvent.CHANGE_SCENE, event -> {
      switch (event.getSceneType()) {
        case MAIN_MENU:
          stage.setScene(this.mainMenuScene);
          break;
        case GAME:
          stage.setScene(this.gameWindowScene);
          break;
       case RULES:
          stage.setScene(this.rulesScene);
          break;
        default:
          stage.setScene(this.mainMenuScene);
          break;
      }
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
  private Scene rulesScene;
}
