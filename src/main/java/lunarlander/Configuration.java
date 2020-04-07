package lunarlander;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
// import java.util.HashMap;
import java.util.ArrayList;


public class Configuration {
  // Serialize and deserialize only Configuration fields, that is
  // moonMaps and params

  public Configuration() {
    this.moonMaps = new ArrayList<Moon>();
  }

  public void toFile() {
    // serialization
    Moon[] moonMapsArray = this.moonMaps.toArray(new Moon[this.moonMaps.size()]);
    String json = this.gson.toJson(moonMapsArray);
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

  public void fromFile(String filePath) {
    File file = new File(filePath);
    CharSource source = Files.asCharSource(file, Charsets.UTF_8);
    try {
      String result = source.read();
      Moon[] moonMapsArray = this.gson.fromJson(result, Moon[].class);
      this.moonMaps = Lists.newArrayList(moonMapsArray);
    } catch (IOException e) {
      System.out.println("Something went wrong. Possible reasons: ");
      System.out.println("1) Folder your are trying to open does not exist.");
      System.out.println("2) You don't have permissions to open that file.");
    }
  }

  public Moon getMoonMap(int lvl) {
    return moonMaps.get(lvl-1);
  }

  public void generateLevel(int lvl) {
    this.moonMaps.add(new Moon(lvl));
  }

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private ArrayList<Moon> moonMaps;

  // For future use:
  // private HashMap<String, String> params; // for example for server ip and port
}
