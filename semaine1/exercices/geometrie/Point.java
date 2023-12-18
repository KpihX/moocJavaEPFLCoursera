package geometrie;

public class Point implements Cloneable{
  /* Attribts */
  private double x;
  private double y;
  private String nom;

  /* Methodes statiques */

  public static double distance(Point A, Point B) {
    return Math.sqrt(Math.pow(A.getX()-B.getX(), 2) + Math.pow(A.getY()-B.getY(), 2));
  }

  /* Methodes non-statiques */

  public Point(double x, double y, String... nom) {
    this.x = x;
    this.y = y;
    if (nom.length == 0) {
      this.nom = new String("M");
    } else if (nom.length >= 2) {
      throw new IllegalArgumentException("Trop d'arguments pour 'nom'!");
    } else {
      this.nom = nom[0];
    }
  }

  @Override
  public Point clone() throws CloneNotSupportedException {
    return (Point) super.clone();
  }

  /* Getteurs et Setteurs */

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }
  
  public String getNom() {
    return nom;
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }
}