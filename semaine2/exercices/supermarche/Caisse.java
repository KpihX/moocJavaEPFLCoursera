package supermarche;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Caisse {
    private int numero;
    private double solde;

    public Caisse(int numero, double solde) {
        this.numero = numero;
        this.solde = solde;
    }

    public void afficherTicket(Caddie caddie) {
        String border = "======================================================";
        System.out.println(border);
        Date dateCourante = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy");
        System.out.println(formatDate.format(dateCourante));
        System.out.println("Caisse numéro " + numero);
        System.out.println();

        for (Achat achat : caddie.getAchats()) {
            achat.afficher();
        }

        System.out.println();
        System.out.println("Montant à payer : " + caddie.getSolde() + "Frs");
        System.out.println(border);
    }

    public void scanner(Caddie caddie) {
        solde += caddie.getSolde();
        afficherTicket(caddie);
        caddie.vider();
    }

    public void totalCaisse() {
        System.out.printf("La caisse numéro %d a encaissé %.2f Frs aujourd'hui\n", numero, solde);
    }
}
