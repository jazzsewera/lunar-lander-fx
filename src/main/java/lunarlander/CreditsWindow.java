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

public class CreditsWindow {

  public CreditsWindow() {
    this.creditsHeader = new Label("Credits");
    this.creditsHeader.getStyleClass().add("credits-label-primary");

    this.creatorNames = new Label("BLAZE Sewera\nMatt M. Winnicki");
    this.creatorNames.getStyleClass().add("credits-label-secondary");

    this.backButton = new Label("Back");
    this.backButton.getStyleClass().add("back-button");

    VBox creditsVBox = new VBox();
    creditsVBox.getChildren().addAll(
      creditsHeader,
      creatorNames
    );
    creditsVBox.getStyleClass().addAll("credits-vertical-layout", "menu-root");
    creditsVBox.setAlignment(Pos.CENTER_LEFT);
    creditsVBox.setMinWidth(400);
    creditsVBox.setPrefWidth(750);
    creditsVBox.setMaxWidth(1200);

    this.creditsRoot = new BorderPane();
    this.creditsRoot.getStyleClass().add("menu-root");

    this.creditsRoot.setCenter(creditsVBox);
    BorderPane.setAlignment(creditsVBox, Pos.CENTER);

    this.creditsRoot.setBottom(backButton);
    BorderPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(backButton, new Insets(0, 50, 50, 0));

    this.creditsScene = new Scene(creditsRoot);
    this.creditsScene.getStylesheets().add("lunarlander/css/style.css");

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
    this.creditsScene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    this.creditsScene.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        this.backButton.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
      }
    });
  }

  public Scene getCreditsScene() {
    return creditsScene;
  }


  private Scene creditsScene;

  private Label creditsHeader;
  private Label creatorNames;
  private BorderPane creditsRoot;
  private Label backButton;
}
