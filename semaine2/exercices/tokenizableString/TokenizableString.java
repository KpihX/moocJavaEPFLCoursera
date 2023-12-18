package tokenizableString;

public class TokenizableString {
    String contenu;
    int from;
    int len;

    public TokenizableString(String contenu) {
        this.contenu = contenu;
        this.from = 0;
        this.len = 0;
    }

    public boolean nextToken() {
        from += len;
        int end = 0;
        try {
            while (contenu.charAt(from) == ' ') {
                from ++;
            }
            end = from;
            while (contenu.charAt(end) != ' ') {
                end ++;
            }
            len = end - from;
            return true;
        } catch (StringIndexOutOfBoundsException e) {
            if (from == contenu.length()) {
                return false;
            } else { //In this case end = contenu.length()
                len = end - from;
                return true;
            }
        }
    }

    public void tokenize() {
        System.out.println("Les mots de \"" + contenu + "\" sont:");
        while (nextToken()) {
            System.out.println("'"+contenu.substring(from, from+len)+"'");
        }
    }
}
