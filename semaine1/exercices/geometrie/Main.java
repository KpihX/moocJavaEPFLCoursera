package geometrie;

import java.util.Scanner;

public class Main {
  private final static Scanner clavier = new Scanner(System.in);

  public static void main(String[] args) throws CloneNotSupportedException {
    /*Triangle t1 = new Triangle(0, 0, 0, 2, 1, 0, "A1", "A2");
    Object[] nature = t1.getNature();
    System.out.println("Nature : triangle " + ((Triangle.Type) nature[0]).toString().toLowerCase() + " en " + (String) nature[1]);
    System.out.println("Périmetre = " + t1.perimetre());
    System.out.println("Surface = " + (float) t1.surface());
    System.out.println("Noms Points : " + t1.getA().getNom() + ", " + t1.getB().getNom() + ", " + t1.getC().getNom());*/
    double xA, yA, xB, yB, xC, yC;
    System.out.println("Construction d'un nouveau point");
    System.out.print("\tVeuillez entrer x : ");
    xA = clavier.nextDouble();
    System.out.print("\tVeuillez entrer y : ");
    yA = clavier.nextDouble();

    System.out.println("Construction d'un nouveau point");
    System.out.print("\tVeuillez entrer x : ");
    xB = clavier.nextDouble();
    System.out.print("\tVeuillez entrer y : ");
    yB = clavier.nextDouble();

    System.out.println("Construction d'un nouveau point");
    System.out.print("\tVeuillez entrer x : ");
    xC = clavier.nextDouble();
    System.out.print("\tVeuillez entrer y : ");
    yC = clavier.nextDouble();

    Triangle t1 = new Triangle(xA, yA, xB, yB, xC, yC);
    System.out.println("Périmètre : " +  t1.perimetre());
    System.out.println("Le triangle est : " + t1.getNature()[0]);
  }
}
