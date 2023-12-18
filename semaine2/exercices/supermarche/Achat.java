package supermarche;

public class Achat {
    private Article article;
    private int quantite;
    private double solde;

    public Achat(Article article, int quantite) {
        assert quantite > 0 : "!La quantité doit être strictement positive!";
        this.article = article;
        this.quantite = quantite;
        setSolde();
    }

    public Achat(Achat achat) {
        this.article = achat.article;
        this.quantite = achat.quantite;
        solde = achat.getSolde();
    }

    public void afficher() {
        String indicationEnSolde = article.isEnSolde() ? " (1/2 prix)" : "";
        System.out.println(article.getNom() + " : " + article.getPrix() + " x " + quantite + " = " + this.getSolde() + " Frs" + indicationEnSolde);
    }

    public void augmenter(int quantite) {
        assert quantite > 0 : "!La quantité doit être strictement positive!";
        this.quantite += quantite;
        solde += article.getPrixSolde() * quantite;
    }

    /**
     * Decreases the quantity of the product by the specified amount.
     *
     * @param  quantite  the amount by which to decrease the quantity (must be strictly positive)
     * @return           the actual amount that was decreased (could be less than the specified amount if the quantity in stock is less)
     */
    public int diminuer(int quantite) {
        assert quantite > 0 : "!La quantité doit être strictement positive!";
        boolean answer;
        int quantiteRetire;
        if (quantite > this.quantite) {
            System.err.println("!On ne peut pas retirer " + quantite + " du produit " + article.getNom() + " car il n'en reste que " + this.quantite + "dans l'achat considéré!");
            System.out.println("Voulez vous le retirer entierement ? (o/n)");
            answer = ScannerUtil.getScanner().nextLine().toLowerCase().equals("o");
            if (answer) {
                quantiteRetire = this.quantite;
                this.quantite = 0;
            } else {
                quantiteRetire = 0;
            }
        } else {
            this.quantite -= quantite;
            quantiteRetire = quantite;
        }
        solde -= article.getPrixSolde() * quantiteRetire;
        return quantiteRetire;
    }

    public Article getArticle() {
        return new Article(article);
    }

    public double getSolde() {
        return solde;
    }

    private void setSolde() {
        solde = article.getPrixSolde() * quantite;
    }

    public void setArticle(Article article) {
        this.article = article;
        setSolde();
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        setSolde();
    }
}
