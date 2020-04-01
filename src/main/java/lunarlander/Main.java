package lunarlander;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    Scene scene = new Scene(new StackPane(l), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {

    launch();

    Configuration conf = new Configuration();
    SomePrimitives ja = new SomePrimitives();
    SomePrimitives jaRazyDwa = new SomePrimitives();

    // serializtion
    String json;
    json = conf.serialize(ja);
    System.out.println("JSON content after successful serialization: ");
    System.out.println(json);

    // saving serialized obj to file
    conf.toFile(json);

    // reading config file
    System.out.println("\nExtracting configuration.json content: ");
    conf.fromFile("src/main/resources/lunarlander/configuration.json");

    // saving 2nd object in configuration.json to check if overwriting works just fine
    String json2;
    json2 = conf.serialize(jaRazyDwa);
    conf.toFile(json2);
    conf.fromFile("src/main/resources/lunarlander/configuration.json");

    // deserialization
    SomePrimitives obj2;
    obj2 = conf.deserialize(json);
    System.out.println("\nExample obj2 field after successful deserialiation: ");
    System.out.println(obj2.getName());

  }

}
