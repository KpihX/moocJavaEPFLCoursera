package puissance4;

public class Main {
    public static void main(String[] args) {
        System.out.println("Entrez votre nom:");
        String nom = ScannerUtil.getScanner().nextLine();
        Joueur h1 = new Humain(nom);
        Joueur o = new Ordinateur("j2");
        Jeu j = new Jeu();

        Partie p = new Partie(j, o, h1);
        p.joue();
    }
}
