package lunarlander;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

    this.menu = new HBox();
    this.menu.getStyleClass().add("menu-root");
    this.menu.getChildren().add(mainMenuVBox);
    this.menu.setAlignment(Pos.CENTER);
    // this.menu.setMinWidth(280);
    // this.menu.setPrefWidth(280);
    // this.menu.setMaxWidth(280);

    this.mainMenuScene = new Scene(menu);
    this.mainMenuScene.getStylesheets().add("lunarlander/css/style.css");
  }


  public Scene getMainMenuScene() {
    return mainMenuScene;
  }

  private Scene mainMenuScene;

  private Label menuLabel;
  private Label logoLabel;
  private Label newGameLabel;
  private Label scoreboardLabel;
  private Label rulesLabel;
  private Label creditsLabel;
  private Label exitLabel;
  private Image logoImage;
  private HBox menu;
}
