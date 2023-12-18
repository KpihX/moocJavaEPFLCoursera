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
	private final String nom;
	
	
	public Accessoire(String unNom, double valeurDeBase)
	{
		super(valeurDeBase);
		nom = unNom;
	}

	public String toString() {
		String result = nom + " coûtant ";
		result += super.toString();
		return result;
	}
}


//------------------------------------------------------------
class Bracelet extends Accessoire {

	public Bracelet(String unNom, double valeurDeBase)
	{ super("bracelet " + unNom, valeurDeBase);}

}


//------------------------------------------------------------
class Fermoir extends Accessoire {
	public Fermoir(String unNom, double valeurDeBase)
	{ super("fermoir " + unNom, valeurDeBase);}
}

//------------------------------------------------------------
class Boitier extends Accessoire {
	public Boitier(String unNom, double valeurDeBase)
	{super("boitier " + unNom, valeurDeBase);}
}


//------------------------------------------------------------
class Vitre extends Accessoire {

	public Vitre(String unNom, double valeurDeBase)
	{super("vitre " + unNom, valeurDeBase);}

}

//======================================================================
class Mecanisme extends Produit {
	
}

//======================================================================
class MecanismeAnalogique extends Mecanisme {

}

//======================================================================
class MecanismeDigital extends  Mecanisme {
}

//======================================================================
class MecanismeDouble extends  Mecanisme{
}


//======================================================================
class Montre extends Produit {
	public Montre()
	{
		accessoires = new ArrayList<Accessoire>();
	}
	@Override
	public double prix() {

		// Au départ, le prix est le prix de base plus celui du mécanisme
		double prixFinal = super.prix();

		for (Accessoire acc : accessoires) {
			prixFinal += acc.prix();
		}
		return prixFinal;
	}

	public void ajouter(Accessoire accessoire)
	{
		accessoires.add(accessoire);
	}

	public void afficher () {
		System.out.println("Une montre composée de :");
		for (Accessoire acc : accessoires) {
			System.out.println("  * " + acc);
		}
		System.out.println("==> Prix total : " + prix());
	}

	private Mecanisme coeur;
	private ArrayList<Accessoire> accessoires;
}
	

//======================================================================
class Montres01
{
	public static void main(String[] args)
	{

		Montre m = new Montre();
		m.ajouter(new Bracelet("cuir", 54.0));
		m.ajouter(new Fermoir("acier", 12.5));
		m.ajouter(new Boitier("acier", 36.60));
		m.ajouter(new Vitre("quartz", 44.80));
		System.out.println('\n' + "Montre m :");
		m.afficher();
	}
}
