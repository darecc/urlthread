package url;

import java.util.Vector;

public class Dane {
    public volatile int ile;
    private static Vector<String> wektor = new Vector();
    public Dane(String adres, int dane) {
        this.ile = dane;
    }
    public int getIle() {
        return ile;
    }
    public void minus() {
        ile--;
    }
    public void plus() {
        ile++;
    }
    public void push(String adres) {
        wektor.add(adres);
    }
    public void print() {
        System.out.println("Wyniki:");
        int j = 0;
        for(int i = 0; i < wektor.size(); i++)
             System.out.format("%s %n", wektor.get(i));
    }
}
