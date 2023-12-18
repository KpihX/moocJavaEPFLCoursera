package geometrie;

public class Triangle {
  /* Types */

  public enum Type {
    QUELCONQUE,
    RECTANGLE,
    ISOCELE,
    ISOCELE_RECTANGLE,
    EQUILATERAL
  }

  /* Attributs */

  private Point A, B, C;
  private double AB, BC, CA;
  private Object[] nature = new Object[2];

  /* Méthodes non Statiques */

  public Triangle(double xA, double yA, double xB, double yB, double xC, double yC, String... nomsPoints) {
    if (nomsPoints.length >= 4) {
      throw new IllegalArgumentException("Trop d'arguments pour 'nomPoints'!");
    }
    
    A = new Point(xA, yA, "A");
    B = new Point(xB, yB, "B");
    C = new Point(xC, yC, "C");

    try {
      A.setNom(nomsPoints[0]);
      B.setNom(nomsPoints[1]);
      B.setNom(nomsPoints[2]);
    } catch (IndexOutOfBoundsException e) {}

    setCotes();
    isTriangle();
    setType();
  }

  public void isTriangle() {
    assert AB + BC > CA && AB + CA > BC && BC + CA > AB : "Les données passées ne permettent pas de définir un triangle, car l'inégalité triangulaire n'est pas vérifiée!";
  }

  private void setAB() {
    AB = Point.distance(A, B);
  }

  private void setBC() {
    BC = Point.distance(B, C);
  }

  private void setCA() {
    CA = Point.distance(C, A);
  }

  private void setCotes() {
    setAB();
    setBC();
    setCA();
  }

  public double perimetre() {
    return (float) (AB + BC + CA);
  }

  public float surface() {
    double demiPeri = this.perimetre() / 2;
    return (float) Math.sqrt(demiPeri*(demiPeri-AB)*(demiPeri-BC)*(demiPeri-CA));
  }

  private void setType() {
    double AB2 = (float) Math.pow(AB, 2), BC2 = (float) Math.pow(BC, 2), CA2 = (float) Math.pow(CA, 2);
    if (AB2 + CA2 == BC2) {
      nature[1] = A.getNom();
      if (AB == CA) {
        nature[0] = Type.ISOCELE_RECTANGLE;
      } else {
        nature[0] = Type.RECTANGLE;
      }
    } else if (AB2 + BC2 == CA2) {
      nature[1] = B.getNom();
      if (AB == BC) {
        nature[0] = Type.ISOCELE_RECTANGLE;
      } else {
        nature[0] = Type.RECTANGLE;
      }
    } else if (CA2 + BC2 == AB2) {
      nature[1] = C.getNom();
      if (BC == CA) {
        nature[0] = Type.ISOCELE_RECTANGLE;
      } else {
        nature[0] = Type.RECTANGLE;
      }
    } else if (AB == CA && CA == BC) {
      nature[0] = Type.EQUILATERAL;
      nature[1] = "";
    } else if (AB == CA) {
      nature[0] = Type.ISOCELE;
      nature[1] = A.getNom();
    } else if (AB == BC) {
      nature[0] = Type.RECTANGLE;
      nature[1] = B.getNom();
    } else if (BC == CA) {
      nature[0] = Type.RECTANGLE;
      nature[1] = C.getNom();
    } else {
      nature[0] = Type.QUELCONQUE;
      nature[1] = "";
    }
  }

  /* Getteurs & Setteurs */

  public Point getA() throws CloneNotSupportedException {
    return A.clone();
  }

  public void setA(Point A) {
      this.A = A;
      setCotes();
      setType();
  }

  public Point getB() throws CloneNotSupportedException  {
      return B.clone();
  }

  public void setB(Point B) {
      this.B = B;
      setCotes();
      setType();
  }

  public Point getC() throws CloneNotSupportedException  {
      return C.clone();
  }

  public void setC(Point C) {
      this.C = C;
      setCotes();
      setType();
  }

  public double getAB() {
    return AB;
  }

  public double getBC() {
      return BC;
  }

  public double getCA() {
      return CA;
  }

  public Object[] getNature(){
    return new Object[] {nature[0], nature[1]};
  }
}
