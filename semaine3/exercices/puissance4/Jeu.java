package puissance4;

public class Jeu {
    private int[][] grille;
    private boolean plein;

    public Jeu(int taille) {
        assert taille > 0 : "Taille doit etre positive";
        grille = new int[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = 0;
            }
        }
    }

    public Jeu() {
        this(8);
    }

    public boolean joueCoup(int colonne, int couleur) {
        assert couleur == Joueur.ROUGE || couleur == Joueur.BLEU : "Couleur invalide";
        assert colonne >= 0 && colonne < grille.length : "Colonne invalide";
        
        int i;
        for (i = 0; i < grille.length; i++) {
            if (grille[i][colonne] == 0) {
                grille[i][colonne] = couleur;
                return true;
            }
        }
        return false;
    }

    public Joueur chercher4() {
        return null;
    }

    public boolean getPlein() {
        return plein;
    }

    public void afficher() {

    }

    public int getTaille() {
        return grille.length;
    }
}
