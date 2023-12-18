package cercle;

public class Cercle {
  private double r;
  private double x;
  private double y;

  public void setCentre(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void setRayon(double r) {
    this.r = r; 
  }

  double surface() {
    return r*r*Math.PI;
  }

  boolean estInterieur(double x, double y) {
    return Math.pow(x-this.x, 2) + Math.pow(y-this.y, 2) <= r*r;
  }
}
