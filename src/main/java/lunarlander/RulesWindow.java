package lunarlander;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RulesWindow {

  public RulesWindow() {
    this.rulesContent = new Text();
    this.rulesContent.setText("Papież polak");

    this.rulesRoot = new HBox();
    this.rulesRoot.getStyleClass().add("menu-root");
    this.rulesRoot.getChildren().add(rulesContent);
    this.rulesRoot.setAlignment(Pos.CENTER);

    this.rulesScene = new Scene(rulesRoot);
    this.rulesScene.getStylesheets().add("lunarlander/css/style.css");
  }


  public Scene getRulesScene() {
    return rulesScene;
  }

  private Scene rulesScene;

  private Text rulesContent;
  private HBox rulesRoot;
}
