package lunarlander;

import javafx.scene.image.Image;
//import javafx.scene.paint.ImagePattern;
//import java.awt.*;

public class Lander {

  public Lander(double xCoords, double yCoords, double angle, double fuel) {

    this.landerImage = new Image("lunarlander/assets/lander.png", 25, 25, false, false);
    this.xCoords = xCoords;
    this.yCoords = yCoords;
    this.angle = angle;
    this.fuel = fuel;
  }


  public double getxCoords() { return xCoords; }

  public void setxCoords(double xCoords) {
    this.xCoords = xCoords;
  }

  public double getyCoords() {
    return yCoords;
  }

  public void setyCoords(double yCoords) {
    this.yCoords = yCoords;
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

  public Image getLanderImage() { return landerImage; }

  public void setLanderImage(Image landerImage) { this.landerImage = landerImage; }

  private Image landerImage;
  private double xCoords;
  private double yCoords;
  private double vx;
  private double xy;

  private double angle;
  private double fuel;

  // needs Polygon here

}
