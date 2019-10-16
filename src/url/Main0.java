package url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main0 {
    private static List<String> wyniki = new ArrayList<>();
    public static void czytaj(String adres) {
        try {
            System.out.println("Sprawdzam: " + adres);
            URL url = new URL(adres);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String line;
            String tekst = "";
            while ((line = in.readLine()) != null)
                tekst += line;
            in.close();
            if (tekst.contains("ssak") || tekst.contains("ptak")) {
                wyniki.add(adres);
            }
        }
        catch(MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        catch(IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }
    public static void main(String[] args) {
        List<String> listaAdresów = new ArrayList<String>();
        //region czytanie adresów z pliku adresy.txt
        File file = new File("adresy.txt");
        try {
            FileReader re = new FileReader(file);
            BufferedReader bu = new BufferedReader(re);
            String line;
            while((line = bu.readLine())!= null) {
                listaAdresów.add(line);
            }
            bu.close();
            re.close();
            System.out.println("Przeczytano " + listaAdresów.size() + " adresów");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        //endregion
        long startT = System.currentTimeMillis();
        int i = 0;
        do {
            czytaj(listaAdresów.get(i++));
        } while (i < listaAdresów.size());
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        };
        long stopT = System.currentTimeMillis();
        long elapsedTime = stopT - startT;
        System.out.println("\nCzas = " + elapsedTime + " ms");
        //wyświetlenie adresów stron, które zawierają słowa: ssak lub ptak
        System.out.println("Wyniki:");
        for(String st : wyniki) {
            System.out.println(st);
        }
    }
}
