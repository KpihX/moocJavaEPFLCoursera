class FiguresGeometriques2 {
	public static void main(String[] args) {
		Figure[] f = new Figure[3];
		f[0] = new RectangleColore(1.2, 3.4, 12.3, 43.2, 4);
		f[1] = new Cercle(2.3, 4.5, 12.2);
		f[2] = new Rectangle(1.3, 3.6, 2.3, 56.2);
		for (int i = 0; i < f.length; i++) {
			f[i].affiche();
			System.out.println("La surface de cette forme est :" + f[i].surface());
		}
	}
}


abstract class Figure {
	private double x;  // abscisse du centre
	private double y;  // ordonnée du centre

	public Figure(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract double surface();

	public void affiche() {
		System.out.println("centre = (" + x + ", " + y + ")");
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setCentre(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class Rectangle extends Figure {
	private double largeur;
	private double longueur;

	public Rectangle(double x, double y, double larg, double longueur) {
		super(x, y);
		this.largeur = larg;
		this.longueur = longueur;
	}

	public double surface() {
		return largeur * longueur;
	}

	public double getLongueur() {
		return longueur;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double l) {
		largeur = l;
	}

	public void setLongueur(double l) {
		longueur = l;
	}

	public void affiche() {
		super.affiche();
		System.out.println("Largeur = " + largeur);
		System.out.println("Longeur = " + longueur);
	}
}

class RectangleColore extends Rectangle {

	private int couleur;

	public RectangleColore(double x, double y, double larg, double longeur, int couleur) {
		super(x, y, larg, longeur);
		this.couleur = couleur;
	}

	public void affiche() {
		super.affiche();
		System.out.println("Couleur = " + couleur);
	}
}

class Cercle extends Figure {
	private double rayon;

	public Cercle(double x, double y, double r) {
		super(x, y);
		rayon = r;
	}

	public void affiche() {
		super.affiche();
		System.out.println("rayon = " + rayon);
	}

	public double surface() {
		return Math.PI * rayon * rayon;
	}

	public boolean estInterieur(double x, double y) {
		return (((x - getX()) * (x - getX()) +
				(y - getY()) * (y - getY())) <= rayon * rayon);
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double r) {
		if (r < 0.0) {
			r = 0.0;
		}
		rayon = r;
	}

}
