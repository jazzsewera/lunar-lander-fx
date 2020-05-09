package lunarlander;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RulesWindow {

  public RulesWindow() {
    this.rulesFirstHeader = new Label("Rules");
    this.rulesFirstHeader.getStyleClass().add("rules-text-primary");

    this.rulesFirstParagraph = new Label(
        "You have to touch down on the Moon with low\n"
        + "enough velocity and angle to the ground until you\n"
        + "run out of fuel and Landers.");
    this.rulesFirstParagraph.getStyleClass().add("rules-text-secondary");

    this.rulesSecondHeader = new Label("Steering");
    this.rulesSecondHeader.getStyleClass().add("rules-text-primary");

    this.rulesSecondParagraph = new Label(
        "Up Arrow - control the thrust of the Lander's engine\n"
        + "Left and Right Arrows - control the rotation of the Lander");
    this.rulesSecondParagraph.getStyleClass().add("rules-text-secondary");

    this.backButton = new Label("Back");
    this.backButton.getStyleClass().add("back-button");

    VBox rulesVBox = new VBox();
    rulesVBox.getChildren().addAll(
        rulesFirstHeader,
        rulesFirstParagraph,
        rulesSecondHeader,
        rulesSecondParagraph
    );

    rulesVBox.getStyleClass().addAll("rules-vertical-layout", "menu-root");
    rulesVBox.setAlignment(Pos.CENTER_LEFT);
    rulesVBox.setMinWidth(400);
    rulesVBox.setPrefWidth(750);
    rulesVBox.setMaxWidth(1200);

    //this.rulesRoot = new HBox();
    //this.rulesRoot.getStyleClass().add("menu-root");
    //this.rulesRoot.getChildren().addAll(rulesVBox, backButton);
    //this.rulesRoot.setAlignment(Pos.CENTER);

    this.rulesRoot = new BorderPane();
    this.rulesRoot.getStyleClass().add("menu-root");

    this.rulesRoot.setCenter(rulesVBox);
    BorderPane.setAlignment(rulesVBox, Pos.CENTER);

    this.rulesRoot.setBottom(backButton);
    BorderPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(backButton, new Insets(0, 50, 50, 0));

    this.rulesScene = new Scene(rulesRoot);
    this.rulesScene.getStylesheets().add("lunarlander/css/style.css");

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
    this.rulesScene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    this.rulesScene.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        this.backButton.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
      }
    });
  }

  public Scene getRulesScene() {
    return rulesScene;
  }


  private Scene rulesScene;

  private Label rulesFirstHeader;
  private Label rulesFirstParagraph;
  private Label rulesSecondHeader;
  private Label rulesSecondParagraph;
  private Label backButton;
  private BorderPane rulesRoot;
}
