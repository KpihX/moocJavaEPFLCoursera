package tokenizableString;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main (String[] args) {
        String phrase;// = " heuu bonjour, voici ma chaîne !  ";  
        System.out.print("Entrez une chaine : ");  
        phrase = scanner.nextLine();  
        TokenizableString toToken = new TokenizableString(phrase);  
        toToken.tokenize();
    }
}
