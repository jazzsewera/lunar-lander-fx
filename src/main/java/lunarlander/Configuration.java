package lunarlander;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Configuration {
  // Serialize and deserialize only Configuration fields, that is
  // moonMaps and params

  // Keep the fields at the bottom of classes


  public void toFile(Double[] coords) {
    // serialization
    String json = this.gson.toJson(coords);
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

  public Double[] fromFile(String filePath) {
    File file = new File(filePath);
    CharSource source = Files.asCharSource(file, Charsets.UTF_8);
    try {
      String result = source.read();
      Double[] coords = this.gson.fromJson(result, Double[].class);
      return coords;
    } catch (IOException e) {
      System.out.println("Something went wrong. Possible reasons: ");
      System.out.println("1) Folder your are trying to open does not exist.");
      System.out.println("2) You don't have permissions to open that file.");
    }
    Double[] coords = {0.0};
    return coords;
  }

  public Double[] getMoonMap(int lvl) {
    // Search moonMaps HashMap and if the map exists, return it.
    // If not, generate new moonMap with a generator in class Moon.
    return new Double[] {0.0};
  }

  public Double[] calculateSurfacePoints(double width, double height, int lvl) {
    // in order to make universal array without presupposing about its size
    // we make it from the list that we will convert to an array
    ArrayList<Double> points = new ArrayList<Double>();

    double step = 0.04;                              // we make x coordinates based on steps
    double maxHeight = 0.035 * height * (lvl + 3);   // and y coordinates based on variation
    // that depends on 0.5*height
    // and lvl-based multiplication
    if (maxHeight > 0.035*height*15) maxHeight = 0.035*height*15;

    Random rand = new Random();

    points.add(0.0);
    points.add(height);
    for(double currentStep = 0; currentStep <= width; currentStep += step*width) {
      points.add(currentStep); // adding first coordinate (width)
      points.add(480 - (maxHeight * rand.nextDouble()));
    }
    points.add(width);
    points.add(height);

    // we are starting from 4 to 6 gaps suitable for landing at lvl 1
    // with next stages this number decreases by 1 with 5lvls to 1-3 gaps at 15lvl
    //
    // edit: we actually want the WIDTH to vary, because this is what makes the difficulty vary
    int landingGaps = rand.nextInt(3) + 1;
    int landingWidth = lvl < 12? 14 - lvl: 3; // we want landing area width to be wider in lower levels (always odd)
    if (lvl > 20) landingGaps = (rand.nextInt(3) + 4) - 4;

    int pointsInWindow = points.size()/2 - 2; // the two subtracted points are in the corners

    int landingRandomCenter
      = 2 * ( // we want every second number, because we want the height
      rand.nextInt( // randomly generated landing spot to make the game thrilling
        pointsInWindow - landingWidth - 1) // we generate the CENTER point of the landing area
        + landingWidth/2 + 1) // trim half a width from both sides so that our index won't be out of bounds
      + 1; // we only want the height, so only odd numbers

    // Set all the surrounding points of a random center to the same height as the center itself
    for (int distanceFromCenter = 1; distanceFromCenter <= landingWidth/2; distanceFromCenter += 1) {
      points.set(landingRandomCenter + 2*distanceFromCenter, points.get(landingRandomCenter));
      points.set(landingRandomCenter - 2*distanceFromCenter, points.get(landingRandomCenter));
    }

    // we go back with List to double array, because polygon accepts only that container
    Double[] result = points.toArray(new Double[points.size()]);
    return result;
  }

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private HashMap<Integer, Double[]> moonMaps; // We will keep level maps in it
  // For future use:
  private HashMap<String, String> params; // for example for server ip and port
}
