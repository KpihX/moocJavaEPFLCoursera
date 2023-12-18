import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Neurone {
    protected Position position;
    protected double signal;
    protected double attenuation;
    protected ArrayList<Neurone> connexion;

    public Neurone(Position position, double attenuation) {
        this.position = position;
        this.attenuation = attenuation;
        connexion = new ArrayList<Neurone>();
        signal = 0;
    }

    public Position getPosition() {
        return position;
    }

    public int getNbConnexions() {
        return connexion.size();
    }

    public Neurone getConnexion(int index) {
        return connexion.get(index);
    }
        
    public double getAttenuation() {
        return attenuation;
    }

    public double getSignal() {
        return signal;
    }

    public void connexion(Neurone n) {
        if (n != null) {
            connexion.add(n);
        }
    }

    public void recoitStimulus(double stimulus) {
        signal = stimulus * getAttenuation();
        for (Neurone neurone : connexion) {
            neurone.recoitStimulus(signal);
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Le neurone en position " + getPosition().toString() + " avec attenuation " + getAttenuation());
        if (getNbConnexions() == 0) {
            str.append(" sans connexion\n");
        } else {
            str.append(" en connexion avec\n");
            for (Neurone neurone : connexion) {
                str.append("  - un neurone en position " + neurone.getPosition().toString() + "\n");
            }
        }
        return str.toString()+"\n";
    }
}

class NeuroneCumulatif extends Neurone {
    public NeuroneCumulatif(Position position, double attenuation) {
        super(position, attenuation);
    }

    public void recoitStimulus(double stimulus) {
        super.recoitStimulus(signal/attenuation + stimulus);
    }
}

class Cerveau {
    private ArrayList<Neurone> neurones = new ArrayList<Neurone>();

    public int getNbNeurones() {
        return neurones.size();
    }

    public Neurone getNeurone(int index) {
        try {
            return neurones.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index invalide");
            return null;
        }
        
    }

    public void ajouterNeurone(Position pos, double attenuation) {
        Neurone neurone = new Neurone(pos, attenuation);
        neurones.add(neurone);
    }

    public void ajouterNeuroneCumulatif(Position pos, double attenuation) {
        Neurone neurone = new NeuroneCumulatif(pos, attenuation);
        neurones.add(neurone);
    }

    public void stimuler(int index, double stimulus) {
        try {
            getNeurone(index).recoitStimulus(stimulus);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index invalide");
        }
    }

    public double sonder(int index) {
        try {
            return getNeurone(index).getSignal();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index invalide");
            return 0;
        }
    }

    public void creerConnexions() {
        try {
            neurones.get(0).connexion(neurones.get(1));   
        } catch (IndexOutOfBoundsException e) {}
        try {
            neurones.get(0).connexion(neurones.get(2));   
        } catch (IndexOutOfBoundsException e) {}
        for (int i = 0; i < (neurones.size() - 3)/2.0; i++) {
            neurones.get(2*i+1).connexion(neurones.get(2*i+2));
            neurones.get(2*i+2).connexion(neurones.get(2*i+3));
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder("\n*----------*\n\nLe cerveau contient " + getNbNeurones() + " neurone(s)\n");
        for (Neurone neurone : neurones) {
            str.append(neurone.toString());
        }
        str.append("*----------*\n\n");
        return str.toString();
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
