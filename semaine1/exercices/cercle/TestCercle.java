package cercle;

public class TestCercle {
  public static void main(String[] args) {
    Cercle c1 = new Cercle();
    Cercle c2 = new Cercle();
    c1.setRayon(5.0);
    c2.setCentre(1, 2);
    c2.setRayon(2);
    System.out.println(c1.surface());
    System.out.println(c1.estInterieur(1, 20));
  }
}
