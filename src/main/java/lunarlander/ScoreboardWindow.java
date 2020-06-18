package lunarlander;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;


/**
 * Scoreboard window class displaying top 10
 * scores
 *
 * @author Błażej Sewera
 * @author Mateusz Winnicki
 */
public class ScoreboardWindow {

  /**
   * Constructor creating Label arrays containing
   * names and scores of top 10 players. Contains onMouseEntered
   * and onMouseExited event handlers in order to bring visual
   * effects to Back button and onMouseClicked event that is
   * fired to Main class for changing scenes.
   */
  public ScoreboardWindow(Configuration configuration) {
    configuration.readScoresFromFile();
    ArrayList<Configuration.Score> scoreBoard = configuration.getScoreBoard();
    String[] names = new String[10];
    int[] scores = new int[10];

    for (int i = 0; i < 10; i++) {
      try {
        names[i] = scoreBoard.get(i).name;
        scores[i] = scoreBoard.get(i).score;
      } catch (IndexOutOfBoundsException e) {
        names[i] = "-";
        scores[i] = 0;
      }
    }


    this.topPlayersLabels = new Label[] {
      new Label("1. " + "XYZ"),
      new Label("2. " + "XYZ"),
      new Label("3. " + "XYZ"),
      new Label("4. " + "XYZ"),
      new Label("5. " + "XYZ"),
      new Label("6. " + "XYZ"),
      new Label("7. " + "XYZ"),
      new Label("8. " + "XYZ"),
      new Label("9. " + "XYZ"),
      new Label("10. " + "XYZ")
    };

    for (Label label : this.topPlayersLabels) {
      label.getStyleClass().add("name-label");
    }

    this.topScoresLabels = new Label[] {
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137"),
      new Label("2137")
    };

    for (Label label : this.topScoresLabels) {
      label.getStyleClass().add("score-label");
    }

    GridPane gridPane = new GridPane();

    for(int j = 0; j < 10; j++) {
      gridPane.add(this.topPlayersLabels[j], 0, j);
    }

    for(int k = 0; k < 10; k++) {
      gridPane.add(this.topScoresLabels[k], 1, k);
    }

    gridPane.getStyleClass().addAll("scores-gridpane");
    gridPane.setAlignment(Pos.CENTER_LEFT);
    // gridPane.setMinWidth(400);
    // gridPane.setPrefWidth(750);
    // gridPane.setMaxWidth(1200);
    gridPane.setHgap(400);
    gridPane.setVgap(20);

    ScrollPane scrollPane = new ScrollPane(gridPane);
    scrollPane.getStyleClass().addAll("scores-scrollpane");

    this.backButton = new Label("Back");
    this.backButton.getStyleClass().add("back-button");

    this.scoreboardRoot = new BorderPane();
    this.scoreboardRoot.getStyleClass().add("menu-root");

    this.scoreboardRoot.setCenter(scrollPane);
    BorderPane.setAlignment(scrollPane, Pos.CENTER);

    this.scoreboardRoot.setBottom(backButton);
    BorderPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(backButton, new Insets(0, 50, 50, 0));

    this.scoreboardScene = new Scene(scoreboardRoot);
    this.scoreboardScene.getStylesheets().add("lunarlander/css/style.css");

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
    this.scoreboardScene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
      if (ctrlQuit.match(event)) {
        Platform.exit();
      }
    });

    this.scoreboardScene.setOnKeyPressed((event) -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        this.backButton.fireEvent(new Main.ChangeSceneEvent(Main.SceneType.MAIN_MENU));
      }
    });
  }

  /**
   * Method to get an actual scene that contains the root pane
   * and can be rendered in the window.
   *
   * @return scoreboardScene, {@link Scene}
   */
  public Scene getScoreboardScene() {
    return scoreboardScene;
  }

  private Scene scoreboardScene;

  //private Label scoreboardHeader;
  private Label[] topPlayersLabels;
  private Label[] topScoresLabels;
  private Label backButton;
  private BorderPane scoreboardRoot;

}
