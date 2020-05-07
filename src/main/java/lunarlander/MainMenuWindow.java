package lunarlander;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuWindow {

  public MainMenuWindow() {
    this.menuLabel = new Label("Lunar Lander");
    this.menuLabel.getStyleClass().add("menu-label-primary");

    this.logoImage = new Image("lunarlander/assets/lander_on.png");
    ImageView logoImageView = new ImageView(logoImage);
    logoImageView.setRotate(logoImageView.getRotate() + 22);
    this.logoLabel = new Label("", logoImageView);

    HBox menuHBox = new HBox();
    menuHBox.getChildren().addAll(menuLabel, logoLabel);
    menuHBox.setSpacing(25);

    this.newGameLabel = new Label("Play");
    this.newGameLabel.getStyleClass().add("menu-label-secondary");

    this.scoreboardLabel = new Label("Scores");
    this.scoreboardLabel.getStyleClass().add("menu-label-secondary");

    this.rulesLabel = new Label("Rules");
    this.rulesLabel.getStyleClass().add("menu-label-secondary");

    this.creditsLabel = new Label("Credits");
    this.creditsLabel.getStyleClass().add("menu-label-secondary");

    this.exitLabel = new Label("Exit");
    this.exitLabel.getStyleClass().add("menu-label-secondary");


    VBox mainMenuVBox = new VBox();
    mainMenuVBox.getChildren().addAll(
      menuHBox,
      newGameLabel,
      scoreboardLabel,
      rulesLabel,
      creditsLabel,
      exitLabel
    );

    mainMenuVBox.getStyleClass().addAll("menu-main-vertical-layout", "menu-root");
    mainMenuVBox.setAlignment(Pos.CENTER_LEFT);
    mainMenuVBox.setMinWidth(400);
    mainMenuVBox.setPrefWidth(750);
    mainMenuVBox.setMaxWidth(1200);

    this.menuRoot = new HBox();
    this.menuRoot.getStyleClass().add("menu-root");
    this.menuRoot.getChildren().add(mainMenuVBox);
    this.menuRoot.setAlignment(Pos.CENTER);
    // this.menuRoot.setMinWidth(280);
    // this.menuRoot.setPrefWidth(280);
    // this.menuRoot.setMaxWidth(280);

    this.mainMenuScene = new Scene(menuRoot);
    this.mainMenuScene.getStylesheets().add("lunarlander/css/style.css");

    this.newGameLabel.setOnMouseClicked((event) -> {
      newGameLabel.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.GAME));
    });
    this.scoreboardLabel.setOnMouseClicked((event) -> {
      newGameLabel.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.SCORES));
    });
    this.rulesLabel.setOnMouseClicked((event) -> {
      newGameLabel.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.RULES));
    });
    this.creditsLabel.setOnMouseClicked((event) -> {
      newGameLabel.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.CREDITS));
    });
    this.exitLabel.setOnMouseClicked((event) -> {
      Platform.exit();
    });

    for(Node child : mainMenuVBox.getChildren()) {
      child.setOnMouseEntered((event) -> {
        child.getStyleClass().addAll("menu-label-hover-effect");
      });

      child.setOnMouseExited((event) -> {
        child.getStyleClass().clear();
        child.getStyleClass().addAll("menu-label-secondary");
      });
    }
  }

  public Scene getMainMenuScene() {
    return mainMenuScene;
  }

  public void setMainMenuScene(Scene newScene) { this.mainMenuScene = newScene; }


  private Scene mainMenuScene;
  //GameWindow gameWindow = new GameWindow();

  private Label menuLabel;
  private Label logoLabel;
  private Label newGameLabel;
  private Label scoreboardLabel;
  private Label rulesLabel;
  private Label creditsLabel;
  private Label exitLabel;
  private Image logoImage;
  private HBox menuRoot;
}
