package banque;

public class Compte {
    private TypeCompte typeCompte;
    private double solde;

    protected Compte(TypeCompte typeCompte, double solde) {
        this.typeCompte = typeCompte;
        this.solde = solde;
    }

    protected void afficherCompte() {
        System.out.printf("%-20s", "    Compte " + typeCompte.toString().toLowerCase() + ":");
        System.out.println(solde + " francs");
    }

    protected void bouclerCompte() {
        solde += this.getTaux() * solde;
    }

    protected double getSolde() {
        return solde;
    }

    protected double getTaux() {
        if (typeCompte == TypeCompte.PRIVE) {
            return 0.01;
        } else {
            return 0.02;
        }
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    protected void setSolde(double solde) {
        this.solde = solde;
    }
    
    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }
}
