package lunarlander;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainMenuWindow {

  public MainMenuWindow() {
    this.menuLabel = new Label("Lunar Lander");
    this.menuLabel.getStyleClass().add("ctrl-label-primary");

    VBox menuLabelVBox = new VBox();
    menuLabelVBox.getChildren().addAll(menuLabel);
    menuLabelVBox.getStyleClass().add("ctrl-gauge-vbox");

    this.newGameLabel = new Label("Play");
    this.newGameLabel.getStyleClass().add("ctrl-label-caption");

    VBox newGameVBox = new VBox();
    newGameVBox.getChildren().addAll(newGameLabel);
    newGameVBox.getStyleClass().add("ctrl-gauge-vbox");


    VBox mainMenuVBox = new VBox();
    mainMenuVBox.getChildren().addAll(
      menuLabelVBox,
      newGameVBox
    );

    mainMenuVBox.getStyleClass().add("ctrl-main-vertical-layout");


    this.mainMenuScene = new Scene(mainMenuVBox);
    this.mainMenuScene.getStylesheets().add("lunarlander/css/style.css");
  }


  public Scene getMainMenuScene() {
    return mainMenuScene;
  }

  private Scene mainMenuScene;
  private BorderPane mainMenuPane;

  private Label menuLabel;
  private Label newGameLabel;
  private Label scoreboardLabel;
  private Label rulesLabel;
  private Label creditsLabel;
  private Label exitLabel;

}
