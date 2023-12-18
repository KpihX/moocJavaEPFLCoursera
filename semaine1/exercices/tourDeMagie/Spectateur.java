package tourDeMagie;

import java.util.Scanner;

public class Spectateur {
  private final static Scanner clavier = new Scanner(System.in);
  private final static String prompt = "[Spectateur] ";
  private int age;
  private int montant;

  public Spectateur() {
    System.out.println(prompt + "(j'entre en scène)");
    do {
      System.out.print("Quel âge ai-je ? ");
      age = clavier.nextInt();
    } while (age <= 0);
    do {
      System.out.print("Combien d'argent ai-je en poche (<100) ? ");
      montant = clavier.nextInt();
    } while (montant <= 0 || montant >= 100);
    System.out.println(prompt + "(j'ai un montant qui convient)");
  }

  public void ecritPapier() {
    System.out.println(prompt + "(j'écris le papier)");
    Assistant.calculs(age, montant);
  }
}
