package poeme;

class Fleur {
  private String couleur1;
  private String couleur2;

  public Fleur(String c1, String c2) {
    couleur1 = c1;
    couleur2 = c2;
    System.out.println(couleur1 + " fraichement cueillie");
  }

  public Fleur(Fleur f) {
    couleur1 = f.couleur1;
    couleur2 = f.couleur2;
    System.out.print("Fragile corolle taillée ");
  }

  public void eclore() {
    System.out.println("veiné de " + couleur2);
  }

  @Override
  public String toString() {
    return "qu'un simple souffle";
  }
}
