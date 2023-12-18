package puissance4;

public class Partie {
    private Joueur j1, j2,winner;
    private Jeu jeu;

    public Partie(Jeu jeu, Joueur j1, Joueur j2) {
        this.jeu = jeu;
        this.j1 = j1;
        this.j2 = j2;
    }

    public Joueur getWinner() {
        return winner;
    }

    public void joue() {
        int numb = j1.getNom().length();
        do {
            System.out.println(repeatChar('_', numb));
            j1.joue(jeu);
            jeu.afficher();
            j2.joue(jeu);
        } while ((winner = jeu.chercher4()) == null && !jeu.getPlein());
        terminer();
    }

    public void terminer() {

    }

    public static String repeatChar(char c, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The given parameter n = '" + n + "', must be positive");
        }

        String cRepeat = "";
        int i;
        for (i = 0; i < n; i++) {
            cRepeat += c;
        }

        return cRepeat;
    }
}
