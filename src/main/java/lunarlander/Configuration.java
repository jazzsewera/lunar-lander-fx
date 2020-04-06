package lunarlander;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class Configuration {
  // Serialize and deserialize only Configuration fields, that is
  // moonMaps and params

  // SomePrimitives needs to be deleted

  // Keep the fields at the bottom of classes


  public void toFile(SomePrimitives obj) {
    // serialization
    String json = this.gson.toJson(obj);
    // saving to file
    File file = new File("src/main/resources/lunarlander/configuration.json");
    try {
      CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
      sink.write(json);
    } catch (IOException e) {
      System.out.println("Something went wrong. Possible reasons: ");
      System.out.println("1) Folder you are trying to place file in does not exist.");
      System.out.println("2) You are running out of disk space.");
      System.out.println("3) You don't have permissions to place a file here.");
    }
  }

  public SomePrimitives fromFile(String filePath) {
    File file = new File(filePath);
    CharSource source = Files.asCharSource(file, Charsets.UTF_8);
    try {
      String result = source.read();
      SomePrimitives obj = this.gson.fromJson(result, SomePrimitives.class);
      return obj;
    } catch (IOException e) {
      System.out.println("Something went wrong. Possible reasons: ");
      System.out.println("1) Folder your are trying to open does not exist.");
      System.out.println("2) You don't have permissions to open that file.");
    }
    SomePrimitives obj = new SomePrimitives("0",0,0);
    return obj;
  }

  public Double[] getMoonMap(int lvl) {
    // Search moonMaps HashMap and if the map exists, return it.
    // If not, generate new moonMap with a generator in class Moon.
    return new Double[] {0.0};
  }

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private HashMap<Integer, Double[]> moonMaps; // We will keep level maps in it
  // For future use:
  private HashMap<String, String> params; // for example for server ip and port
}
