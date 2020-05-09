package lunarlander;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    VBox rulesVBox = new VBox();
    rulesVBox.getChildren().addAll(
      rulesFirstHeader,
      rulesFirstParagraph,
      rulesSecondHeader,
      rulesSecondParagraph
    );
    rulesVBox.getStyleClass().addAll("menu-main-vertical-layout", "menu-root");
    rulesVBox.setAlignment(Pos.CENTER_LEFT);
    rulesVBox.setMinWidth(400);
    rulesVBox.setPrefWidth(750);
    rulesVBox.setMaxWidth(1200);


    this.rulesRoot = new HBox();
    this.rulesRoot.getStyleClass().add("menu-root");
    this.rulesRoot.getChildren().add(rulesVBox);
    this.rulesRoot.setAlignment(Pos.CENTER);

    this.rulesScene = new Scene(rulesRoot);
    this.rulesScene.getStylesheets().add("lunarlander/css/style.css");
  }


  public Scene getRulesScene() {
    return rulesScene;
  }

  private Scene rulesScene;

  private Label rulesFirstHeader;
  private Label rulesFirstParagraph;
  private Label rulesSecondHeader;
  private Label rulesSecondParagraph;
  private HBox rulesRoot;
}
