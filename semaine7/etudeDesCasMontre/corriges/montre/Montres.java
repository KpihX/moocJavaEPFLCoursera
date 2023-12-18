import java.util.ArrayList;

//======================================================================
abstract class Produit {
	// un produit a un prix de base
	private final double valeur;

	public Produit(double uneValeur) {
		valeur = uneValeur;
	}

	public Produit() {
		valeur = 0.0;
	}

	public Produit(Produit autre)
	{
		valeur = autre.valeur;
	}

	// Méthode polymorphique de copie
	public abstract Produit copie();

	// Méthode polymorphique (on pourrait vouloir la modifier)...
	public double prix()
	// ...dont le comportement par défaut est de retourner la valeur de base
	{ return valeur; }

	public String toString() {
		// Par défaut, un produit affiche son prix.
		return Double.toString(prix());

		/* Notez bien qu'ici nous n'avons pas écrit
		 *     Double.toString(valeur);
		 * car nous voulons qu'un produit affiche son PRIX,
		 * lequel pourrait très bien être différent de la valeur de base
		 * (aspect polymorphique de la méthode prix()).
		 */
	}
}


//======================================================================
abstract class Accessoire extends Produit {

	public Accessoire(String unNom, double valeurDeBase)
	{
		super(valeurDeBase);
		nom = unNom;
	}

	public Accessoire(Accessoire autre)
	{   super(autre);
	nom = autre.nom;
	}

	public String toString() {
		String result = nom + " coûtant ";
		result += super.toString();
		return result;
	}

	// copie polymorphique d'Accessoire
	public abstract Accessoire copie();

	private final String nom;
}


//------------------------------------------------------------
class Bracelet extends Accessoire {

	public Bracelet(String unNom, double valeurDeBase)
	{ super("bracelet " + unNom, valeurDeBase);}

	public Bracelet(Bracelet autre)
	{ super(autre);
	}

	// copie polymorphique de Bracelet
	@Override
	public Bracelet copie()
	{ return new Bracelet(this);
	}
}


//------------------------------------------------------------
class Fermoir extends Accessoire {
	public Fermoir(String unNom, double valeurDeBase)
	{ super("fermoir " + unNom, valeurDeBase);}

	public Fermoir(Fermoir autre)
	{ super(autre);
	}
	// copie polymorphique de Fermoir
	@Override
	public Fermoir copie()
	{ return new Fermoir(this);
	}
}

//------------------------------------------------------------
class Boitier extends Accessoire {
	public Boitier(String unNom, double valeurDeBase)
	{super("boitier " + unNom, valeurDeBase);}

	public Boitier(Boitier autre)
	{ super(autre);
	}

	// copie polymorphique de Boitier
	@Override
	public Boitier copie()
	{ return new Boitier(this);
	}
}


//------------------------------------------------------------
class Vitre extends Accessoire {

	public Vitre(String unNom, double valeurDeBase)
	{super("vitre " + unNom, valeurDeBase);}

	public Vitre(Vitre autre)
	{ super(autre);
	}

	// copie polymorphique de Boitier
	@Override
	public Vitre copie()
	{ return new Vitre(this);
	}
}

//======================================================================
abstract class Mecanisme extends Produit {

		public Mecanisme(double valeurDeBase, String uneHeure) 
		{ super(valeurDeBase);
		heure = uneHeure;
		}
		public Mecanisme(double valeurDeBase) 
		{ super(valeurDeBase);
		heure = "12:00";
		}
		public Mecanisme(Mecanisme autre) 
		{ super(autre);
			heure = autre.heure;
		}
		// copie polymorphique de Mecanisme
		@Override
		public abstract Mecanisme copie();

		// Tous les mécanismes doivent s'afficher comme ceci
		@Override
		public final String toString()  {
			String result = "mécanisme ";
			result += toStringType();
			result += " (affichage : ";
			result += toStringCadran();
			result += "), prix : ";
			result += super.toString();
			return result;
		}


		// on veut offrir la version par défaut aux sous-classes
		// et aux classes du même paquetage
		// Par défaut, on affiche juste l'heure.
		protected String toStringCadran() {
			return heure;
		}
		// Un mécanisme, ici à ce niveau, reste abstrait (= classe abstraite)
		protected  abstract String toStringType();

		private  String heure;

}

//======================================================================
interface ReveilDigital
{
	String toStringReveil();
}
//======================================================================
class MecanismeAnalogique extends Mecanisme {

	public MecanismeAnalogique(double valeurDeBase, String uneHeure, int uneDate)
	{super (valeurDeBase, uneHeure);
	date = uneDate;
	}

	// gestion propre de la valeur par défaut de l'heure (super-classe)
	public MecanismeAnalogique(double valeurDeBase, int uneDate)
	{ super(valeurDeBase);
	date= uneDate;
	}

	public MecanismeAnalogique(MecanismeAnalogique autre)
	{
		super(autre);
		date = autre.date;
	}

	// Copie polymorphique de MecanismeAnalogique

	@Override
	public MecanismeAnalogique copie()
	{ return new MecanismeAnalogique(this);
	}

	public String toStringDate()
		{
			return ", date " + date;
		}
	
	protected String toStringType() {
		return "analogique";
	}

	@Override
	
	protected String toStringCadran () {
		// On affiche l'heure (façon de base)...
		return super.toStringCadran() + toStringDate();

	}
	
	private int date;
}

//======================================================================
class MecanismeDigital extends  Mecanisme implements ReveilDigital {

	public  MecanismeDigital(double valeurDeBase, String uneHeure, String heureReveil)
	{ super(valeurDeBase, uneHeure);
	reveil = heureReveil;
	}


	// gestion propre de la valeur par défaut de l'heure (super-classe)
	public MecanismeDigital(double valeurDeBase, String heureReveil)
	{
		super(valeurDeBase);
		reveil = heureReveil;
	}

	public MecanismeDigital(MecanismeDigital autre)
	{
		super(autre);
		reveil = autre.reveil;
	}

	// Copie polymorphique de MecanismeDigital
	@Override
	public MecanismeDigital copie()
	{ return new MecanismeDigital(this);
	}

	public String toStringReveil()
		{
			return " réveil " + reveil;
		}
	
	@Override
	protected String toStringType()  {
		return "digital";
	}

	@Override
	protected String toStringCadran() {
	// On affiche l'heure (façon de base)...
	// ...et en plus l'heure de réveil.
		return super.toStringCadran() + ", " + toStringReveil();
 }

private String reveil;
}

//======================================================================
class MecanismeDouble extends MecanismeAnalogique implements ReveilDigital {

	public MecanismeDouble(double valeurDeBase, String uneHeure, int uneDate, String heureReveil)
	{
		super(valeurDeBase, uneHeure, uneDate);
		reveil = heureReveil;
	}

	// gestion propre de la valeur par défaut de l'heure (super-classe)
	public MecanismeDouble(double valeurDeBase, int uneDate, String heureReveil)
		{ super(valeurDeBase, uneDate);
		reveil = heureReveil;
	}
	
    public MecanismeDouble(MecanismeDouble autre){
    	super(autre);
		reveil = autre.reveil;
    }	
	// Copie polymorphique de MecanismeDouble
	@Override
	public MecanismeDouble copie()
	{ return new MecanismeDouble(this);
	}

	public String toStringReveil()
		{
			return " réveil " + reveil;
		}
	

	protected String toStringType()  {
		return "double";
	}

	@Override
	protected String toStringCadran() {
		// Par exemple...
		String result = "sur l'écran : ";
		result += super.toStringCadran();
		result += ", sous les aiguilles : ";
		result += toStringReveil();
		return result;
	}
	// duplication nécessaire de l'attribut
	private String reveil;
}

//======================================================================
class Montre extends Produit {
	public Montre(Mecanisme depart)
	{
		coeur = depart.copie();
		accessoires = new ArrayList<Accessoire>();
	}

	// Copie PROFONDE d'une Montre
	public Montre(Montre autre)
	{	super(autre);
		coeur = autre.coeur.copie();
		accessoires = new ArrayList<Accessoire>();
		for (Accessoire acc : autre.accessoires) {
						accessoires.add(acc.copie());
				}
	}
	@Override 
	public Montre copie(){
		return new Montre(this);
	}

	@Override
	public double prix() {

		// Au départ, le prix est le prix de base plus celui du mécanisme
		double prixFinal = super.prix() + coeur.prix();

		for (Accessoire acc : accessoires) {
			prixFinal += acc.prix();
		}
		return prixFinal;
	}

	public void ajouter(Accessoire accessoire)
	{
		accessoires.add(accessoire.copie());
	}

	public void afficher () {
		System.out.println("Une montre composée de :");
		System.out.println("  * " + coeur);

		for (Accessoire acc : accessoires) {
			System.out.println("  * " + acc);
		}
		System.out.println("==> Prix total : " + prix());
	}

	private Mecanisme coeur;
	private ArrayList<Accessoire> accessoires;
}
	

//======================================================================
class Montres
{
	public static void main(String[] args)
	{

		// test de l'affichage des mécanismes
		MecanismeAnalogique v1 = new MecanismeAnalogique(312.00, 20141212);
		MecanismeDigital   v2 = new MecanismeDigital(32.00, "11:45", "7:00");
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


		// Nous faisons une copie de la montre m
		Montre m2 = new Montre(m);
		System.out.println("Montre m2 :");
		m2.afficher();
	}
	
}