package puissance4;

public class Ordinateur extends Joueur {
    public Ordinateur(String nom) {
        super(Joueur.ROUGE, nom);
    }

    public Ordinateur() {
        this("Ordinateur");
    }

    public void joue(Jeu jeu) {
        System.out.println("Le programme a joué en ");
    }
    
}
