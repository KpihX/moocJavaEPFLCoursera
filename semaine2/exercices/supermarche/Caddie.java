package supermarche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Caddie {
    private double solde;
    private List<Achat> achats = new ArrayList<>();
    
    public Caddie() {
        solde = 0.0;
    }

    public void remplir(Article article, int quantite) {
        assert quantite > 0 : "!La quantité doit être strictement positive!";
        
        for (Achat achat : achats) {
            if (achat.getArticle().equals(article)) {
                achat.augmenter(quantite);
                solde += achat.getSolde();
                return;
            }
        }
        Achat achat = new Achat(article, quantite);
        achats.add(achat);
        solde += achat.getSolde();
    }

    public void retirer(Article article, int quantite) {
        assert quantite > 0 : "!La quantité doit être strictement positive!";
        
        for (Achat achat : achats) {
            if (achat.getArticle().equals(article)) {
                achat.diminuer(quantite);
                solde -= achat.getSolde();
                return;
            }
        }
        System.err.println("!L'article '" + article.getNom() + "'' à rétirer n'a pas été trouvé dans le caddie!");
    }

    public List<Achat> getAchats() {
        return Collections.unmodifiableList(achats);
    }

    public double getSolde() {
        return solde;
    }

    public void vider() {
        achats.clear();
        solde = 0.0;
    }
}

