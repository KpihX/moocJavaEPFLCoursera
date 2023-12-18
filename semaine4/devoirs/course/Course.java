/*/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

import java.util.ArrayList;

class Vehicule {
    private String nom;
    private double vitesseMax;
    private int poids;
    private int carburant;

    public Vehicule(String nom, double vitesseMax, int poids, int carburant) {
        this.nom = nom;
        this.vitesseMax = vitesseMax;
        this.poids = poids;
        this.carburant = carburant;
    }

    public Vehicule() {
        this("Anonyme", 130, 1000, 0);
    }

    public String toString() {
        return getNom() + " -> vitesse max = " + getVitesseMax() + " km/h, poids = " + getPoids() + " kg";
    }

    public boolean meilleur(Vehicule autreVehicule) {
        return performance() >= autreVehicule.performance();
    }

    public String getNom() {
        return nom;
    }

    public double getVitesseMax() {
        return vitesseMax;
    }

    public int getPoids() {
        return poids;
    }

    public int getCarburant() {
        return carburant;
    }

    public void diminuerCarburant(int carburant) {
        this.carburant -= carburant;
    }

    private double performance() {
        return getVitesseMax() / getPoids();
    }

    public boolean estDeuxRoues() {
        return false;
    }
}

class Voiture extends Vehicule {
    private String categorie;

    public Voiture(String nom, double vitesseMax, int poids, int carburant, String categorie) {
        super(nom, vitesseMax, poids, carburant);
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public String toString() {
        return super.toString() + ", Voiture de " + getCategorie();
    }
}

class Moto extends Vehicule {
    private boolean avecSidecar;

    public Moto(String nom, double vitesseMax, int poids, int carburant, boolean avecSidecar) {
        super(nom, vitesseMax, poids, carburant);
        this.avecSidecar = avecSidecar;
    }

    public Moto(String nom, double vitesseMax, int poids, int carburant) {
        super(nom, vitesseMax, poids, carburant);
        this.avecSidecar = false;
    }

    public boolean getAvecSidecar() {
        return avecSidecar;
    }

    public String toString() {
        return super.toString() + ", Moto" + (getAvecSidecar() ? ", avec sidecar" : "");
    }

    public boolean estDeuxRoues() {
        return !getAvecSidecar();
    }
}

abstract class Rallye {
    public abstract boolean check();
}

class GrandPrix extends Rallye {
    private ArrayList<Vehicule> voitures = new ArrayList<Vehicule>();
    
    public void ajouter(Vehicule vehicule) {
        voitures.add(vehicule);
    }

    public boolean check() {
        if (voitures.size() == 0) {
            return false;
        }
        boolean verif1 = voitures.get(0).estDeuxRoues();
        for (Vehicule voiture : voitures.subList(1, voitures.size())) {
            if (verif1 != voiture.estDeuxRoues()) {
                return false;
            }
        }
        return true;
    }

    public  ArrayList<Vehicule> arrivee() {
        ArrayList<Vehicule> voituresArrivee = new ArrayList<Vehicule>();
        for (Vehicule vehicule : voitures) {
            if (vehicule.getCarburant() > 0) {
                voituresArrivee.add(vehicule);
            }
        }
        return voituresArrivee;
    }

    public Vehicule gagnant(ArrayList<Vehicule> voituresArrivee) {
        if (voituresArrivee.size() == 0) {
            return null;
        }

        Vehicule champion = voituresArrivee.get(0);
        for (Vehicule vehicule : voituresArrivee.subList(1, voituresArrivee.size())) {
            if (vehicule.meilleur(champion)) {
                champion = vehicule;
            }
        }
        return champion;
    }

    void run(int tours) {
        if (!check()) {
            System.out.println("Pas de Grand Prix");
            return;
        }
        for (int i = 0; i < tours; i++) {
            for (Vehicule vehicule : voitures) {
                vehicule.diminuerCarburant(1);
            }
        }

        ArrayList<Vehicule> voituresArrivee = arrivee();
        if (voituresArrivee.size() == 0) {
            System.out.println("Elimination de tous les véhicules");
            return;
        }
        Vehicule champion = gagnant(voituresArrivee);
        System.out.println("Le gagnant du grand prix est :\n" + champion.toString());
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}
