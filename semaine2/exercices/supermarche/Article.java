package supermarche;

public class Article {
    private String nom;
    private double prix;
    private boolean enSolde;
    private double prixSolde;

    public Article(String nom, double prix, boolean enSolde) {
        assert prix > 0.0 : "!Le prix doit être strictement positif!";
        this.nom = nom;
        this.prix = prix;
        this.enSolde = enSolde;
        setPrixSolde();
    }

    public Article(Article article) {
        this.nom = article.nom;
        this.prix = article.prix;
        this.enSolde = article.enSolde;
        prixSolde = article.getPrixSolde();
    }

    public boolean equals(Article article) {
        return nom == article.nom && prix == article.prix;
    }

    public double ratio() {
        return enSolde ? 0.5 : 1;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        assert prix > 0.0 : "!Le prix doit être strictement positif!";
        this.prix = prix;
        setPrixSolde();
    }

    public double getPrixSolde() {
        return prixSolde;
    }

    private void setPrixSolde() {
        prixSolde = getPrix() * ratio();
    }

    public boolean isEnSolde() {
        return enSolde;
    }

    public void setEnSolde(boolean enSolde) {
        this.enSolde = enSolde;
        setPrixSolde();
    }
}