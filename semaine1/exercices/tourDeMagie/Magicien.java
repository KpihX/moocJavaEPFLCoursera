package tourDeMagie;

public class Magicien {
  private final static String prompt = "[Magicien] ";
  private static int age, montant;
  public static void commenceMagie(Spectateur spec) {
    System.out.println(prompt + "un petit tour de magie...");
    spec.ecritPapier();
  }

  public static void finiMagie(int resultat) {
    resultat += 115;
    age = resultat / 100;
    montant = resultat - age * 100;

    System.out.println(prompt + "\thum... je vois que vous êtes agé de " + age + " ans\n\tet que vous avez " + montant + " franc(s) suisses en poche !");
  }
}
