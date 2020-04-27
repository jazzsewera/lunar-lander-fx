package lunarlander;

import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
//import javafx.scene.paint.ImagePattern;
//import java.awt.*;

public class Lander {

  public Lander(double xCoord, double yCoord, double angle, double fuel) {

    this.landerOFFImage = new Image("lunarlander/assets/lander.png", 25, 25, false, false);
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.angle = angle;
    this.fuel = fuel;

    // lander.getPoints().addAll(new Double[]{
    //   0.0, 0.0,
    //   0.0, 25.0,
    //   25.0, 25.0,
    //   25.0, 0.0,
    // });

    lander.getPoints().addAll(new Double[] {
      this.xCoord, this.yCoord,
      this.xCoord, this.yCoord+25.0,
      this.xCoord+25.0, this.yCoord+25.0,
      this.xCoord+25.0, this.yCoord,
    });
  }

  public double getxCoords() { return xCoord; }

  public void setxCoords(double xCoord) { this.xCoord = xCoord; }

  public double getyCoords() {
    return yCoord;
  }

  public void setyCoords(double yCoord) {
    this.yCoord = yCoord;
  }

  public double getAngle() {
    return angle;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public double getFuel() {
    return fuel;
  }

  public void setFuel(double fuel) {
    this.fuel = fuel;
  }

  public Image getLanderOFFImage() { return landerOFFImage; }

  //public void setLanderOFFImage(Image landerImage) { this.landerOFFImage = landerImage; }

  public Image getLanderONImage() { return landerONImage; }

  //public void setLanderONImage(Image landerONImage) { this.landerONImage = landerONImage; }

  private double xCoord;
  private double yCoord;
  private double vx;
  private double xy;

  private double angle;
  private double fuel;

  private Image landerOFFImage;
  private Image landerONImage;

  //how to get actual polygon if i made it private? asking coz with getter i can only GET, not setFill or whatsoever
  Polygon lander = new Polygon();

}
