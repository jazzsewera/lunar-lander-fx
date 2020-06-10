package lunarlander;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
//import javafx.scene.paint.ImagePattern;
//import java.awt.*;

public class Lander {

  public Lander(double xCoord, double yCoord, double vx, double vy, double angle, double fuel) {

    this.landerImage = new Image("lunarlander/assets/lander.png", 35, 35, false, false);
    this.flameImage = new Image("lunarlander/assets/flame.png", 8, 47, false, false);
    this.noFlameImage = new Image("lunarlander/assets/no_flame.png", 8, 47, false, false);
    this.nitroFlameImage = new Image("lunarlander/assets/immortal_flame.png", 8, 47, false, false);
    this.noFlameImagePattern = new ImagePattern(this.noFlameImage);
    this.flameImagePattern = new ImagePattern(this.flameImage);
    this.nitroFlameImagePattern = new ImagePattern(this.nitroFlameImage);
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.vx = vx;
    this.vy = vy;
    this.angle = angle;
    this.fuel = fuel;

    // lander.getPoints().addAll(new Double[]{
    //   0.0, 0.0,
    //   0.0, 25.0,
    //   25.0, 25.0,
    //   25.0, 0.0,
    // });

    this.lander.getPoints().addAll(new Double[] {
      this.xCoord, this.yCoord,
      this.xCoord, this.yCoord+35.0,
      this.xCoord+35.0, this.yCoord+35.0,
      this.xCoord+35.0, this.yCoord,
    });

    this.landerWidth = 35.0;
    this.landerHeight = 35.0;

    this.flame.getPoints().addAll(new Double[] {
      this.xCoord+14.0, this.yCoord+28.0,
      this.xCoord+14.0, this.yCoord+28.0+47.0,
      this.xCoord+14.0+8.0, this.yCoord+28.0+47.0,
      this.xCoord+14.0+8.0, this.yCoord+28.0,
    });

    this.landerGroup.getChildren().addAll(this.lander, this.flame);
    lander.setFill(new ImagePattern(this.landerImage));
    flame.setFill(this.noFlameImagePattern);
  }

  public double getLeftCoord() {
    return this.lander.getPoints().get(0);
  }
  public double getRightCoord() {
    return this.lander.getPoints().get(4);
  }
  public double getTopCoord() {
    return this.lander.getPoints().get(1);
  }
  public double getBottomCoord() {
    return yCoord + landerHeight;
  }

  public Image getLanderImage() { return landerImage; }

  public enum FlameImageType {
    NO_FLAME,
    FLAME,
    NITRO_FLAME
  }

  public void setFlameImage(FlameImageType type) {
    switch (type) {
      case FLAME:
        this.flame.setFill(this.flameImagePattern);
        break;
      case NITRO_FLAME:
        this.flame.setFill(this.nitroFlameImagePattern);
        break;
      default:
        this.flame.setFill(this.noFlameImagePattern);
        break;
    }
  }

  // public Group getLanderGroup() { return landerGroup; }

  //public void setLanderOFFImage(Image landerImage) { this.landerImage = landerImage; }

  //public void setLanderONImage(Image landerONImage) { this.landerONImage = landerONImage; }

  double xCoord;
  double yCoord;
  double vx;
  double vy;
  double v;
  double ax;
  double ay;

  double angle;
  double fuel;

  double landerHeight;
  double landerWidth;

  int currentLevel = 1;

  private Image landerImage;
  private Image flameImage;
  private Image noFlameImage;
  private Image nitroFlameImage;
  private ImagePattern noFlameImagePattern;
  private ImagePattern flameImagePattern;
  private ImagePattern nitroFlameImagePattern;

  Polygon lander = new Polygon();
  Polygon flame = new Polygon();
  Group landerGroup = new Group();
}
