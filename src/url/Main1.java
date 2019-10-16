package url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<String> li = new ArrayList<String>();
        File file = new File("adresy.txt");
        //region czytanie adresów z pliku adresy.txt
        try {
            FileReader re = new FileReader(file);
            BufferedReader bu = new BufferedReader(re);
            String line;
            int ile = 0;
            while((line = bu.readLine())!= null) {
                li.add(line);
                ile++;
            }
            bu.close();
            re.close();
            System.out.println("Przeczytano " + ile + " adresów");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        //endregion
        long startT = System.currentTimeMillis();
        int i = 0;
        Dane dane = new Dane(li.get(0),0);
        do {
            if (dane.getIle() < 30) {
                String adres = li.get(i);
                URLThread urlT = new URLThread(adres, dane);
                urlT.start();
                i++;
            }
            else {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } while (i < li.size());
        try {
            Thread.sleep(30);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        };
        long stopT = System.currentTimeMillis();
        long elapsedTime = stopT - startT;
        dane.print();
        System.out.println("\nCzas = " + elapsedTime + " ms");
        //wyświetlenie adresów stron, które zawierają słowa: ssak lub ptak
        dane.print();
    }
}
