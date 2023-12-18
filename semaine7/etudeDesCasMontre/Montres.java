import java.util.ArrayList;

abstract class Produit {
    private final double valeur;

    public Produit(double valeur) {
        this.valeur = valeur;
    }

    public Produit() {
        valeur = 0;
    }

    public Produit(Produit autre) {
        valeur = autre.valeur;
    }

    public double prix() {
        return valeur;
    }

    @Override
    public String toString() {
        return Double.toString(prix());
    }
}

abstract class Accessoire extends Produit {
    private final String nom;

    public Accessoire(String nom, double valeurDeBase) {
        super(valeurDeBase);
        this.nom = nom;
    }

    public Accessoire(Accessoire autre) {
        super(autre);
        nom = new String(autre.nom);
    }

    @Override
    public String toString() {
        String result = nom + " coûtant ";
        result += super.toString();
        return result;
    }

    public abstract  Accessoire copie();
}

class Bracelet extends Accessoire {
    
    public Bracelet(String nom, double valeurDeBase) {
        super("bracelet " + nom, valeurDeBase);
    }

    public Bracelet (Bracelet autre) {
        super(autre);
    }

    @Override
    public Bracelet copie() {
        return new Bracelet(this);
    }
}

class Fermoir extends Accessoire {
    
    public Fermoir(String nom, double valeurDeBase) {
        super("fermoir " + nom, valeurDeBase);
    }

    public Fermoir (Fermoir autre) {
        super(autre);
    }

    @Override
    public Fermoir copie() {
        return new Fermoir(this);
    }
}

class Boitier extends Accessoire {

    public Boitier(String nom, double valeurDeBase) {
        super("boitier " + nom, valeurDeBase);
    }

    public Boitier (Boitier autre) {
        super(autre);
    }

    @Override
    public Boitier copie() {
        return new Boitier(this);
    }
}

class Vitre extends Accessoire {

    public Vitre(String nom, double valeurDeBase) {
        super("vitre " + nom, valeurDeBase);
    }

    public Vitre (Vitre autre) {
        super(autre);
    }

    @Override
    public Vitre copie() {
        return new Vitre(this);
    }
}

abstract class Mecanisme extends Produit {
    private String heure;

    public Mecanisme(double valeurDeBase, String heure) {
        super(valeurDeBase);
        this.heure = heure;
    }

    public Mecanisme(double valeurDeBase) {
        super(valeurDeBase);
        heure = "12:00";
    }

    public Mecanisme(Mecanisme autre) {
        super(autre);
        heure = new String(autre.heure);
    }

    public final String toString() {
        String result = "mécanisme ";
        result += toStringType();
        result += " (affichage : ";
        result += toStringCadran();
        result += "), prix : ";
        result += super.toString();
        return result;
    }

    protected String toStringCadran() {
        return heure;
    }

    protected abstract String toStringType();

    public abstract Mecanisme copie();
}

class MecanismeAnalogique extends Mecanisme {
    private int date;
    public MecanismeAnalogique(double valeurDeBase, String heure, int date) {
        super(valeurDeBase, heure);
        this.date = date;
    }

    public MecanismeAnalogique(double valeurDeBase, int date) {
        super(valeurDeBase);
        this.date = date;
    }

    public MecanismeAnalogique(MecanismeAnalogique autre) {
        super(autre);
        date = autre.date;
    }

    @Override
    protected String toStringType() {
        return "analogique";
    }

    @Override
    public MecanismeAnalogique copie() {
        return new MecanismeAnalogique(this);
    }
}

interface ReveilDigital {
    String toStringReveil();
}

class MecanismeDigital extends Mecanisme {
    private String reveil;
    public MecanismeDigital(double valeurDeBase, String heure, String reveil) {
        super(valeurDeBase, heure);
        this.reveil = reveil;
    }

    public MecanismeDigital(MecanismeDigital autre) {
        super(autre);
        reveil = new String(autre.reveil);
    }

    @Override
    protected String toStringType() {
        return "digital";
    }

    @Override
    protected String toStringCadran() {
        return super.toStringCadran() + ", " + toStringReveil();
    }

    public String toStringReveil() {
        return " réveil " + reveil;
    }

    @Override
    public MecanismeDigital copie() {
        return new MecanismeDigital(this);
    }
}

class MecanismeDouble extends MecanismeAnalogique implements ReveilDigital {
    private String reveil;
    public MecanismeDouble(double valeurDeBase, String heure, int date, String reveil) {
        super(valeurDeBase, heure, date);
        this.reveil = reveil;
    }

    public MecanismeDouble(double valeurDeBase, int date, String reveil) {
        super(valeurDeBase, date);
        this.reveil = reveil;
    }

    public MecanismeDouble(MecanismeDouble autre) {
        super(autre);
        reveil = new String(autre.reveil);
    }

    @Override
    protected String toStringCadran() {
        String result = "sur l'écran : ";
        result += super.toStringCadran();
        result += ", sous les aiguilles : ";
        result += toStringReveil();
        return result;
    }

    @Override
    protected String toStringType() {
        return "double";
    }

    @Override
    public String toStringReveil() {
        return " réveil " + reveil;
    }

    @Override
    public MecanismeDouble copie() {
        return new MecanismeDouble(this);
    }
}

class Montre extends Produit {
    private Mecanisme coeur;
    private ArrayList<Accessoire> accessoires;

    public Montre(Mecanisme depart) {
        coeur = depart.copie();
        accessoires = new ArrayList<Accessoire>();
    }

    public Montre(Montre autre) {
        super(autre);
        coeur = autre.coeur.copie();
        accessoires = new ArrayList<Accessoire>();
        for (Accessoire accessoire : autre.accessoires) {
            accessoires.add(accessoire.copie());
        }
    }

    public void ajouter(Accessoire accessoire) {
        accessoires.add(accessoire.copie());
    }

    @Override
    public double prix() {
        double prixFinal = super.prix();

        for (Accessoire accessoire : accessoires) {
            prixFinal += accessoire.prix();
        }
        return prixFinal;
    }

    public void afficher() {
        System.out.print("Une montre ");
        System.out.println("composée de :");

        for (Accessoire accessoire : accessoires) {
            System.out.println("  * " + accessoire);
        }
        System.out.print("===> Prix total : ");
        System.out.println(prix());
    }
}


public class Montres {
    public static void main(String[] args) {
        // test de l'affichage des mécanismes
        MecanismeAnalogique v1 = new MecanismeAnalogique(312.00, 20141212);
        MecanismeDigital v2 = new MecanismeDigital(32.00, "11:45", "7:00");
        MecanismeDouble v3 = new MecanismeDouble(543.00, "8:20", 20140328, "6:30");
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        // Test des montres
        Montre m = new Montre(new MecanismeDouble(468.00, "9:15", 20140401, "7:00"));
        m.ajouter(new Bracelet("cuir", 54.0));
        m.ajouter(new Fermoir("acier", 12.5));
        m.ajouter(new Boitier("acier", 36.60));
        m.ajouter(new Vitre("quartz", 44.80));
        System.out.println('\n' + "Montre m :");
        m.afficher();
    }
}
