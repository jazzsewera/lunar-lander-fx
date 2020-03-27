package lunarlander;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Configuration {

  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  // Method converting Java obj to JSON
  public String serialize(SomePrimitives obj) {
    String json = gson.toJson(obj);
    return json;
  }

  // Method converting JSON to Java obj
  public SomePrimitives deserialize(String json) {
    SomePrimitives obj2 = gson.fromJson(json, SomePrimitives.class);
    return obj2;
  }

  public static void toFile(String json) {
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

  public static void fromFile(String filePath) {
    File file = new File(filePath);
    CharSource source = Files.asCharSource(file, Charsets.UTF_8);
    try {
      String result = source.read();
      System.out.println(result);
    } catch (IOException e) {
      System.out.println("Something went wrong. Possible reasons: ");
      System.out.println("1) Folder your are trying to open does not exist.");
      System.out.println("2) You don't have permissions to open that file.");
    }
  }

}
