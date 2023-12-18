package banque;

public class Client{
    private String nom, ville;
    private Sex sex;
    private Compte comptePrive, compteEpargne;

    public Client(String nom, String ville, Sex sex, double... solde) {
        this.nom = nom;
        this.ville = ville;
        this.sex = sex;
        
        double soldePrive = 0, soldeEpargne = 0;
        if (solde.length >= 3) {
            throw new IllegalArgumentException("Trop de soldes! Juste deux soldes max!");
        } else if (solde.length >= 1) {
            soldePrive = solde[0];
            if (solde.length == 2) {
                soldeEpargne = solde[1];
            }
        }
        comptePrive = new Compte(TypeCompte.PRIVE, soldePrive);
        compteEpargne = new Compte(TypeCompte.EPARGNE, soldeEpargne);
    }

    public void afficherClient() {
        System.out.println(this.titleClient() + nom + " de " + ville);
        comptePrive.afficherCompte();
        compteEpargne.afficherCompte();
    }

    public void bouclerCompte() {
        comptePrive.bouclerCompte();
        compteEpargne.bouclerCompte();
    }

    private String titleClient() {
        return sex == Sex.M ? "Client " : "Cliente ";
    }

    /* Getters and setters */

    public String getNom() {
    return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
