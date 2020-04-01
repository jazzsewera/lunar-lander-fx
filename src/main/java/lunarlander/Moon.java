package lunarlander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Moon {

  private Double[] moonSurfacePoints;

  Moon() {
    moonSurfacePoints = calculateSurfacePoints(640,480,1);
  }

  protected Double[] calculateSurfacePoints(double width, double height, int lvl) {
    // in order to make universal array without presupposing about its size
    // we make it from the list that we will convert to an array
    ArrayList<Double> points = new ArrayList<Double>();

    double step = 0.02 * width; // we make x coordinates based on steps
    double maxHeight = 0.5 * height *  0.025 * lvl; // and y coordinates based on variation
                                                    // that depends on 0.5*height
                                                    // and lvl-based multiplication
    Random rand = new Random();

    for(double currentStep = 0; currentStep <= width; currentStep += step) {

      points.add(new Double(currentStep)); // adding first coordinate (width)
      points.add(new Double(maxHeight * rand.nextDouble()));
    }

    //change some values to the same ones todo

    // we go back with List to double array, bcs polygon accepcts only that container
    return (Double[]) points.toArray();
  }

  public Double[] getMoonSurfacePoints() {
    return moonSurfacePoints;
  }

  public void recalculateWidth(double width) {
    //height stays the same but width is calculated with the same step but new
  }

  public void recalculateHeight(double height) {
  }
}
