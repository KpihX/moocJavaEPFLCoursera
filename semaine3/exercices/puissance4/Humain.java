package puissance4;

import java.util.InputMismatchException;

public class Humain extends Joueur{
    public Humain(String nom) {
        super(Joueur.ROUGE, nom);
    }

    public void joue(Jeu jeu) {
        int numColonne = 0;
        do {
            System.out.println("Joueur " + nom + ", entrez une colonne (entre 1 et " + jeu.getTaille() + ") :");
            try {
                numColonne = ScannerUtil.getScanner().nextInt();
                jeu.joueCoup(numColonne, Joueur.ROUGE);
            } catch (InputMismatchException e) {
                System.out.println("Vous n'avez pas entré un entier!");
                continue;
            } 
        } while (false);
    }
}
