package lunarlander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Moon {

  private Double[] moonSurfacePoints;

  Moon() {
    moonSurfacePoints = calculateSurfacePoints(640, 480, 3);
  }

  protected Double[] calculateSurfacePoints(double width, double height, int lvl) {
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
              pointsInWindow - landingWidth/2) // we generate the CENTER point of the landing area
          + landingWidth/2) // trim half a width from both sides so that our index won't be out of bounds
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

  public Double[] getMoonSurfacePoints() {
    return moonSurfacePoints;
  }

  public void recalculateWidth(double width) {
    // height stays the same but width is calculated with the same step but new
  }

  public void recalculateHeight(double height) {
  }
}
