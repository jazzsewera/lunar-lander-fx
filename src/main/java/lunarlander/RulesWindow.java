package lunarlander;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RulesWindow {

  public RulesWindow() {
    this.rulesFirstHeader = new Label("Rules");
    this.rulesFirstHeader.getStyleClass().add("rules-text-primary");

    this.rulesFirstParagraph = new Label("You have to touch down on the Moon with low" +
      "\nenough velocity and angle to the ground until you" +
      "\nrun out of fuel and Landers.");
    this.rulesFirstParagraph.getStyleClass().add("rules-text-secondary");

    this.rulesSecondHeader = new Label("Steering");
    this.rulesSecondHeader.getStyleClass().add("rules-text-primary");

    this.rulesSecondParagraph = new Label("Up Arrow - control the thrust of the Lander's engine" +
      "\nLeft and Right Arrows - control the rotation of the Lander");
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
    this.rulesRoot.setAlignment(rulesVBox, Pos.CENTER);

    this.rulesRoot.setBottom(backButton);
    this.rulesRoot.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    this.rulesRoot.setMargin(backButton, new Insets(0,50,50,0));

    this.rulesScene = new Scene(rulesRoot);
    this.rulesScene.getStylesheets().add("lunarlander/css/style.css");

    this.backButton.setOnMouseClicked((event) -> {
      backButton.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
    });

    this.backButton.setOnMouseEntered((event) -> {
      this.backButton.getStyleClass().addAll("back-button-hover-effect");
    });

    this.backButton.setOnMouseExited((event) -> {
      this.backButton.getStyleClass().clear();
      this.backButton.getStyleClass().addAll("back-button");
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
