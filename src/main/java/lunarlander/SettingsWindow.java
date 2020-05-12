package lunarlander;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Credits window class rendering VBox of Labeles
 * containing credits of the game. Class contains
 * event handlers in order to support window operating.
 *
 * @author Błażej Sewera
 * @author Mateusz Winnicki
 */
public class SettingsWindow {

  /**
   * Constructor creating bunch of Labels and
   * gathering them in Vbox. Contains onMouseEntered and
   * onMouseExited event handlers in order to bring visual
   * effects to Back button and onMouseClicked event that is
   * fired to Main class for changing scenes.
   */
  public SettingsWindow(Configuration configuration) {
    this.settingsHeader = new Label("Settings");
    this.settingsHeader.getStyleClass().add("settings-label-primary");

    this.configSettingsHeader = new Label("Map configuration");
    this.configSettingsHeader.getStyleClass().add("settings-label-secondary");

    this.appConfigSettings = new Label("Load local maps");
    this.appConfigSettings.getStyleClass().add("settings-label-tertiary");

    this.serverConfigSettings = new Label("Load maps from a server");
    this.serverConfigSettings.getStyleClass().add("settings-label-tertiary");

    this.backButton = new Label("Back");
    this.backButton.getStyleClass().add("back-button");

    VBox mapConfigurationVbox = new VBox();
    mapConfigurationVbox.getChildren().addAll(
        configSettingsHeader,
        appConfigSettings,
        serverConfigSettings
    );
    mapConfigurationVbox.getStyleClass().add("settings-subsection-layout");

    VBox settingsVBox = new VBox();
    settingsVBox.getChildren().addAll(
      settingsHeader,
      mapConfigurationVbox
    );

    settingsVBox.getStyleClass().addAll("settings-vertical-layout", "menu-root");
    settingsVBox.setAlignment(Pos.CENTER_LEFT);
    settingsVBox.setMinWidth(400);
    settingsVBox.setPrefWidth(750);
    settingsVBox.setMaxWidth(1200);

    this.settingsRoot = new BorderPane();
    this.settingsRoot.getStyleClass().add("menu-root");

    this.settingsRoot.setCenter(settingsVBox);
    BorderPane.setAlignment(settingsVBox, Pos.CENTER);

    this.settingsRoot.setBottom(backButton);
    BorderPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(backButton, new Insets(0, 50, 50, 0));

    this.settingsScene = new Scene(settingsRoot);
    this.settingsScene.getStylesheets().add("lunarlander/css/style.css");

    this.appConfigSettings.setOnMousePressed((event) -> {
      this.appConfigSettings.getStyleClass().add("settings-click-effect");
      configuration.setConfigDownloaded(false);
    });

    this.appConfigSettings.setOnMouseReleased((event) -> {
      this.appConfigSettings.getStyleClass().add("settings-label-tertiary");
    });

    this.appConfigSettings.setOnMouseEntered((event) -> {
      this.appConfigSettings.getStyleClass().add("settings-hover-effect");
    });

    this.appConfigSettings.setOnMouseExited((event) -> {
      this.appConfigSettings.getStyleClass().clear();
      this.appConfigSettings.getStyleClass().add("settings-label-tertiary");
    });

    this.serverConfigSettings.setOnMousePressed((event) -> {
      this.serverConfigSettings.getStyleClass().add("settings-click-effect");
      configuration.lunarClient();
      configuration.setConfigDownloaded(true);
    });

    this.serverConfigSettings.setOnMouseReleased((event) -> {
      this.serverConfigSettings.getStyleClass().add("settings-label-tertiary");
    });

    this.serverConfigSettings.setOnMouseEntered((event) -> {
      this.serverConfigSettings.getStyleClass().add("settings-hover-effect");
    });

    this.serverConfigSettings.setOnMouseExited((event) -> {
      this.serverConfigSettings.getStyleClass().clear();
      this.serverConfigSettings.getStyleClass().add("settings-label-tertiary");
    });

    this.backButton.setOnMouseClicked((event) -> {
      backButton.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
    });

    this.backButton.setOnMouseEntered((event) -> {
      this.backButton.getStyleClass().add("back-button-hover-effect");
    });

    this.backButton.setOnMouseExited((event) -> {
      this.backButton.getStyleClass().clear();
      this.backButton.getStyleClass().add("back-button");
    });

    final KeyCombination ctrlQuit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
    this.settingsScene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    this.settingsScene.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        this.backButton.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
      }
    });
  }

  /**
   * Method to get an actual scene that contains the root pane
   * and can be rendered in the window.
   *
   * @return creditsScene, {@link Scene}
   */
  public Scene getSettingsScene() {
    return settingsScene;
  }


  private Scene settingsScene;
  private Label settingsHeader;
  private Label configSettingsHeader;
  private Label appConfigSettings;
  private Label serverConfigSettings;
  private Label backButton;
  private BorderPane settingsRoot;
}
