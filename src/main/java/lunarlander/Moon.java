package lunarlander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Moon {

  private Double[] moonSurfacePoints;

  Moon() {
    moonSurfacePoints = calculateSurfacePoints(640, 480, 100);
  }

  protected Double[] calculateSurfacePoints(double width, double height, int lvl) {
    // in order to make universal array without presupposing about its size
    // we make it from the list that we will convert to an array
    ArrayList<Double> points = new ArrayList<Double>();

    double step = 0.04 * width; // we make x coordinates based on steps
    double maxHeight = 0.035 * height * (lvl + 3); // and y coordinates based on variation
                                                    // that depends on 0.5*height
                                                    // and lvl-based multiplication
    if (maxHeight > 0.035*height*15) maxHeight = 0.035*height*15;

    Random rand = new Random();

    points.add(0.0);
    points.add(height);
    for(double currentStep = 0; currentStep <= width; currentStep += step) {
      points.add(currentStep); // adding first coordinate (width)
      points.add(480 - (maxHeight * rand.nextDouble()));
    }
    points.add(width);
    points.add(height);

    // we are starting from 4 to 6 gaps suitable for landing at lvl 1
    // with next stages this number decreases by 1 with 5lvls to 1-3 gaps at 15lvl
    int landingGaps = (rand.nextInt(3) + 4) - (lvl/5);
    if (lvl > 20) landingGaps = (rand.nextInt(3) + 4) - 4;

    for(int i = 0; i <= landingGaps; i++) {
      int n = rand.nextInt(49);
      n += (n%2 == 0? 1:0);

      if(n != 1 && n != 49) {
        points.set(n - 2, points.get(n));
        points.set(n + 2, points.get(n));
      } else if (n == 1) {
        points.set(n + 2, points.get(n));
        points.set(n + 4, points.get(n));
      } else if (n == 49) {
        points.set(n - 2, points.get(n));
        points.set(n - 4, points.get(n));
      }
    // todo THERE IS STILL ONE FRICKING BUG WE NEED TO DISCUSS
    }

    // we go back with List to double array, bcs polygon accepcts only that container
    Double[] result = points.toArray(new Double[points.size()]);
    return result;
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
