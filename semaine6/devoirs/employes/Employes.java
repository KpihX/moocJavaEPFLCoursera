/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Employe {
    private final String nom;
    private double salaire;
    private int taux;
    private double prime;

    public Employe(String nom, double salaire, int taux) {
        this.nom = nom;
        this.salaire = salaire;
        if (taux < 10) {
            this.taux = 10;
        } else if (taux > 100) {
            this.taux = 100;
        } else {
            this.taux = taux;
        }
        this.prime = 0.0;

        System.out.print("Nous avons un nouvel employé : " + nom + ", ");
    }

    public Employe(String nom, double salaire) {
        this(nom, salaire, 100);
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public int getTaux() {
        return taux;
    }

    public double revenuAnnuel() {
        return salaire * 12 * (double)taux/100 + prime;
    }

    public String toString() {
        return nom + " :\n  Taux d'occupation : " + taux + "%. Salaire annuel : " + String.format("%.2f", revenuAnnuel()) + " francs" + (prime > 0 ? ",  Prime : " + String.format("%.2f", prime) : "") + ".\n";
    }

    public void demandePrime() {
        int nbTentatives = 0;
        double primeDemandee;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Montant de la prime souhaitée par " + nom + " ?\n");
            try {
                primeDemandee = scanner.nextDouble();
                if (primeDemandee < 0) {
                    throw new IllegalArgumentException("Vous devez introduire un nombre positif!");
                }
                if (primeDemandee > 0.2 * getSalaire()) {
                    throw new IllegalArgumentException("Trop cher!");
                } 
                prime = primeDemandee;
                nbTentatives = 5;
            } catch (InputMismatchException e) {
                System.out.println("Vous devez introduire un nombre!");
                scanner.nextLine();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            nbTentatives++;
        } while (nbTentatives < 5);

        scanner.close();
    }
}

class Manager extends Employe {
    public final static int FACTEUR_GAIN_CLIENT = 500;
    public final static int FACTEUR_GAIN_VOYAGE = 100;

    private int nbJours;
    private int nbNouveauxClients;

    public Manager(String nom, double salaire, int nbJours, int nbNouveauxClients, int taux) {
        super(nom, salaire, taux);
        System.out.println(" c'est un manager.");
        if (nbJours >= 0) {
            this.nbJours = nbJours;
        } else {
            throw new IllegalArgumentException("nbJours doit être >= 0");
        }

        if (nbNouveauxClients >= 0) {
            this.nbNouveauxClients = nbNouveauxClients;
        } else {
            throw new IllegalArgumentException("nbNouveauxClients doit être >= 0");
        }
        
    }

    public Manager(String nom, double salaire, int nbJours, int nbNouveauxClients) {
        this(nom, salaire, nbJours, nbNouveauxClients, 100);
    }

    public int getNbJours() {
        return nbJours;
    }

    public int getNbNouveauxClients() {
        return nbNouveauxClients;
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + getNbJours() * FACTEUR_GAIN_VOYAGE + getNbNouveauxClients() * FACTEUR_GAIN_CLIENT;
    }

    public String toString() {
        return super.toString() + "  A voyagé " + getNbJours() + " jours et apporté " + getNbNouveauxClients() + " nouveaux clients.\n";
    }
}

class Testeur extends Employe {
    public final static int FACTEUR_GAIN_ERREURS = 10;
    private int nbErreursCorrigees;

    public Testeur(String nom, double salaire, int nbErreursCorrigees, int taux) {
        super(nom, salaire, taux);
        System.out.println(" c'est un testeur.");
        
        if (nbErreursCorrigees >= 0) {
            this.nbErreursCorrigees = nbErreursCorrigees;
        } else {
            throw new IllegalArgumentException("nbErreursCorrigees doit être >= 0");
        }
    }

    public Testeur(String nom, double salaire, int nbErreursCorrigees) {
        this(nom, salaire, nbErreursCorrigees, 100);
    }

    public int getNbErreursCorrigees() {
        return nbErreursCorrigees;
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + getNbErreursCorrigees() * FACTEUR_GAIN_ERREURS;
    }

    public String toString() {
        return super.toString() + "  A corrige " + getNbErreursCorrigees() + " erreurs.\n";
    }
}

class Programmeur extends Employe { 
    public final static int FACTEUR_GAIN_PROJETS = 200;
    private int nbProjetsAcheves;

    public Programmeur(String nom, double salaire, int nbProjetsAcheves, int taux) {
        super(nom, salaire, taux);
        System.out.println(" c'est un programmeur.");
        
        if (nbProjetsAcheves >= 0) {
            this.nbProjetsAcheves = nbProjetsAcheves;
        } else {
            throw new IllegalArgumentException("nbProjetsAcheves doit être >= 0");
        }
    }

    public Programmeur(String nom, double salaire, int nbProjetsAcheves) {
        this(nom, salaire, nbProjetsAcheves, 100);
    }

    public int getNbProjetsAcheves() {
        return nbProjetsAcheves;
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + getNbProjetsAcheves() * FACTEUR_GAIN_PROJETS;
    }

    public String toString() {
        return super.toString() + "  A mené à bien " + getNbProjetsAcheves() + " projets\n";
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {
        
        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

