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

/**
 * Class operating on attributes and methods linked to
 * the configuration.json file that stores initial game state.
 */
public class Configuration {
  // Serialize and deserialize only Configuration fields, that is
  // moonMaps and params

  /**
   * Constructor making ArrayList of Moon class objects {@link Moon} -
   * each Moon has its own surface shape. We will store
   * them in moonMaps.
   */
  public Configuration() {
    this.moonMaps = new ArrayList<Moon>();
  }

  /**
   * Method serialising moonMaps into Json and saving it
   * in configuration.json file.
   */
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

  /**
   * Method reading configuration.json file and deserialising its
   * content back into moonMaps ArrayList.
   *
   * @param filePath Specifying where does configuration.json exist, String.
   */
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

  /**
   * Method returning moonMap of chosen level.
   *
   * @param lvl Number of level we want to get surface shape of, int.
   * @return Moon of moonMaps at lvl-1 index, {@link Moon}.
   */
  public Moon getMoonMap(int lvl) {
    return moonMaps.get(lvl-1);
  }

  /**
   * Method adding or overwritting moonMap of chosen level.
   *
   * @param lvl Number of level we want to create moon surface with, int.
   */
  public void generateLevel(int lvl) {
    this.moonMaps.add(new Moon(lvl));
  }


  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private ArrayList<Moon> moonMaps;

  // For future use:
  // private HashMap<String, String> params; // for example for server ip and port
}
