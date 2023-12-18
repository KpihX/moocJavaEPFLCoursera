package puissance4;

public class Joueur {
    final static int BLEU = 1, ROUGE = 2; 
    protected int couleur; //1 pour bleu, 2 pour rouge
    protected String nom;

    public Joueur (int couleur, String nom) {
        this.couleur = couleur;
        this.nom = nom;
    }

    public void joue(Jeu jeu) {
    }

    public String getNom() {
        return nom;
    }
}
