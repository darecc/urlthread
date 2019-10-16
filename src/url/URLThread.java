package url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLThread extends Thread {
    Dane dane;
    String adres;
    public URLThread(String adres, Dane dane) {
        this.dane = dane;
        this.adres = adres;
        synchronized (dane) {
            dane.plus();
        }
    }
    public void run() {
        try {
            System.out.println("Sprawdzam: " + adres);
            URL url = new URL(adres);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;
            String tekst = "";
            Thread.sleep(10);
            while ((line = in.readLine()) != null)
                tekst += line;
            in.close();
            if (tekst.contains("ssak") || tekst.contains("ptak")) {
                dane.push(adres);
            }
            synchronized (dane) {
                dane.minus();
            }
        }
        catch(MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        catch(IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        catch(InterruptedException iex) {
            System.out.println(iex.getMessage());
        }
    }
}
