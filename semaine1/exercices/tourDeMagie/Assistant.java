package tourDeMagie;

public class Assistant {
  private final static String prompt = "[Assistant] ";
  
  public static void calculs(int age, int montant) {
    System.out.println(prompt + "(je lis le papier)");
    System.out.println(prompt + "(je calcule mentalement)");

    int resultat = (age*2 + 5)*50 + montant - 365;
    System.out.println(prompt + "J'annonce : " + resultat + " !");
    Magicien.finiMagie(resultat);
  }
}
