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
    RULES,
    SETTINGS
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
    this.configuration = new Configuration();
    this.stage = stage;
    this.mainMenuWindow = new MainMenuWindow();
    this.mainMenuScene = this.mainMenuWindow.getMainMenuScene();
    this.mainMenuScene.addEventHandler(ChangeSceneEvent.CHANGE_SCENE, (event) -> {
      switch (event.getSceneType()) {
        case MAIN_MENU:
          this.stage.setScene(this.mainMenuScene);
          break;
        case GAME:
          this.gameWindow = new GameWindow(this.configuration);
          this.gameWindowScene = this.gameWindow.getMainGameScene();
          this.stage.setScene(this.gameWindowScene);
          break;
        case RULES:
          this.initRulesWindow();
          this.stage.setScene(this.rulesScene);
          break;
        case SETTINGS:
          this.initSettingsWindow();
          this.stage.setScene(this.settingsScene);
          break;
        case SCORES:
          Configuration c = new Configuration();
          c.lunarClient();
          break;
        default:
          this.stage.setScene(this.mainMenuScene);
          break;
      }
    });

    this.stage.setScene(this.mainMenuScene);
    this.stage.setTitle("Lunar Lander");
    this.stage.setWidth(1080);
    this.stage.setHeight(630);
    this.stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }


  private void initRulesWindow() {
    this.rulesWindow = new RulesWindow();
    this.rulesScene = this.rulesWindow.getRulesScene();

    this.rulesScene.addEventHandler(ChangeSceneEvent.CHANGE_SCENE, (event) -> {
      if(event.getSceneType() == SceneType.MAIN_MENU) {
        this.stage.setScene(this.mainMenuScene);
      }
    });
  }

  private void initSettingsWindow() {
    this.settingsWindow = new SettingsWindow(this.configuration);
    this.settingsScene = settingsWindow.getSettingsScene();

    this.settingsScene.addEventHandler(ChangeSceneEvent.CHANGE_SCENE, (event) -> {
      if(event.getSceneType() == SceneType.MAIN_MENU) {
        this.stage.setScene(this.mainMenuScene);
      }
    });
  }

  private Stage stage;

  private Scene mainMenuScene;
  private Scene gameWindowScene;
  private Scene scoreboardScene;
  private Scene rulesScene;
  private Scene settingsScene;

  private Configuration configuration;
  private GameWindow gameWindow;
  private RulesWindow rulesWindow;
  private SettingsWindow settingsWindow;
  private MainMenuWindow mainMenuWindow;
}
