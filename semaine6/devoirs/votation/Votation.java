import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme à partir d'ici.
 *******************************************/

class Postulant {
    private String nom;
    private int nbElecteurs;

    public Postulant(String nom, int nbElecteurs) {
        this.nom = nom;
        this.nbElecteurs = nbElecteurs;
    }

    public Postulant(String nom) {
        this(nom, 0);
    }

    public Postulant(Postulant postulant) {
        this(postulant.nom, postulant.nbElecteurs);
    }

    public void elect() {
        nbElecteurs++;
    }

    public void init() {
        nbElecteurs = 0;
    }

    public int getVotes() {
        return nbElecteurs;
    }

    public String getNom() {
        return nom;
    }
}

class Scrutin {
    private ArrayList<Postulant> postulants;
    private int nbVotantsMax;
    private int date;
    private ArrayList<Vote> votes;

    public Scrutin(ArrayList<Postulant> postulants, int nbVotantsMax, int date, boolean init) {
        this.postulants = new ArrayList<Postulant>();
        if (postulants != null) {
            for (Postulant postulant : postulants) {
                this.postulants.add(new Postulant(postulant));
            }
        }
        this.nbVotantsMax = nbVotantsMax;
        this.date = date;

        if (init) {
            for (Postulant postulant : this.postulants) {
                postulant.init();
            }
        }

        votes = new ArrayList<Vote>();
    }

    public Scrutin(ArrayList<Postulant> postulants, int nbVotantsMax, int date) {
        this(postulants, nbVotantsMax, date, true);
    }

    public int calculerVotants() {
        int nbVotants = 0;
        for (Postulant postulant : postulants) {
            nbVotants += postulant.getVotes();
        }
        return nbVotants;
    }

    public String gagnant() {
        Postulant vainqueur = postulants.get(0);
        for (int i =1; i < postulants.size(); i++) {
            if (postulants.get(i).getVotes() >= vainqueur.getVotes()) {
                vainqueur = postulants.get(i);
            }
        }

        return vainqueur.getNom();
    }

    public void resultats() {
        int nbVotants = calculerVotants();
        if (nbVotants == 0) {
            System.out.println("Scrutin annulé, pas de votants\n");
            return;
        }
        StringBuilder str = new StringBuilder("Taux de participation -> " + String.format("%.1f", (double)nbVotants/nbVotantsMax * 100) + " pour cent\n");
        str.append("Nombre effectif de votants -> " + nbVotants + "\n");
        str.append("Le chef choisi est -> " + gagnant() + "\n\n");
        str.append("Repartition des electeurs\n");
        for (Postulant postulant : postulants) {
            str.append(postulant.getNom() + " -> " + String.format("%.1f", (double)postulant.getVotes()/nbVotants * 100) + " pour cent des électeurs\n");
        }
        str.append("\n");
        System.out.println(str);
    }

    public void compterVotes() {
        for (Vote vote : votes) {
            for (Postulant postulant : postulants) {
                if (!vote.estInvalide() && vote.getPostulant().equals(postulant.getNom())) {
                    postulant.elect();
                }
            }
        }
    }

    public void simuler(double taux, int date) {
        int nbVotants = (int) (nbVotantsMax * taux);

        for(int i = 0; i < nbVotants; i++) {
            int candNum = Utils.randomInt(postulants.size());
            if (i%3 == 0) {
                votes.add(new BulletinElectronique(postulants.get(candNum).getNom(), date, this.date));
            } else if (i%3 == 1) {
                votes.add(new BulletinPapier(postulants.get(candNum).getNom(), date, this.date, i%2 == 0 ? false : true));
            } else {
                votes.add(new BulletinCourrier(postulants.get(candNum).getNom(), date, this.date, i%2 == 0 ? false : true));
            }
        }

        for (Vote vote : votes) {
            System.out.println(vote);
        }
    }
}

abstract class Vote {
    private String nomPostulant;
    private int date;
    private int dateLimite;

    public Vote(String nomPostulant, int date, int dateLimite) {
        this.nomPostulant = nomPostulant;
        this.date = date;
        this.dateLimite = dateLimite;
    }

    public abstract boolean estInvalide();

    public String getPostulant() {
        return nomPostulant;
    }

    public int getDate() {
        return date;
    }

    public int getDateLimite() {
        return dateLimite;
    }

    public String toString() {
        return " pour " + nomPostulant + " -> " + (estInvalide() ?  "invalide" : "valide");
    }
}

interface CheckBulletin {
    boolean checkDate();
}

class BulletinPapier extends Vote{
    private boolean estSigne;

    public BulletinPapier(String nomPostulant, int date, int dateLimite, boolean estSigne) {
        super(nomPostulant, date, dateLimite);
        this.estSigne = estSigne;
    }

    public boolean estInvalide() {
        return !estSigne;
    }

    public String toString() {
        return "vote par bulletin papier pour " + getPostulant() + " -> " + (estInvalide() ? " invalide" : " valide");
    }

    public boolean getEstSigne() {
        return estSigne;
    }
}

class BulletinElectronique extends Vote implements CheckBulletin{
    public BulletinElectronique(String nomPostulant, int date, int dateLimite) {
        super(nomPostulant, date, dateLimite);
    }

    public boolean checkDate() {
        return getDate() <= getDateLimite() - 2;
    }

    public boolean estInvalide() {
        return !checkDate();
    }

    public String toString() {
        return "vote electronique pour " + getPostulant() + " -> " + (estInvalide() ? " invalide" : " valide");
    }
}

class BulletinCourrier extends BulletinPapier implements CheckBulletin{
    public BulletinCourrier(String nomPostulant, int date, int dateLimite, boolean estSigne) {
        super(nomPostulant, date, dateLimite, estSigne);
    }

    public boolean checkDate() {
        return getDate() <= getDateLimite();
    }

    public boolean estInvalide() {
        return !checkDate() || !getEstSigne();
    }

    public String toString() {
        return "envoi par courrier d'un vote par bulletin papier pour " + getPostulant() + " -> " + (estInvalide() ? " invalide" : " valide");
    }
}

/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
