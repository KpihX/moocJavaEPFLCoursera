package banque;

public class Banque {
    public static void main(String[] args) {
        Client client1 = new Client("Pedro", "Genève", Sex.M, 1000, 2000);
        Client client2 = new Client("Alexandra", "Lausanne", Sex.F, 3000, 4000);

        System.out.println("*** Données avant le bouclement des comptes ***");
        client1.afficherClient();
        client2.afficherClient();

        client1.bouclerCompte();
        client2.bouclerCompte();

        System.out.println("*** Données apres le bouclement des comptes ***");
        client1.afficherClient();
        client2.afficherClient();
    }
}


