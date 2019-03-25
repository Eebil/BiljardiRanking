/**
 * 
 */
package vaihe5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author eewerant
 * @version 11.3.2019
 *
 */
public class Jasenet {
    
    private int MAX_JASENET = 5;
    String tiedNimi = "";
    private int lkm = 0;
    private Jasen[] alkiot = new Jasen[MAX_JASENET];
    
    
    /**
     * Kloonaa ja kaksinkertaistaa taulukon koon
     * @return paluttaa kloonin taulukosta kaksinkertaisen kokoisena
     */
    public Jasen[] kloonaaJaKasvata() {
        Jasen[] klooni = new Jasen[2 * this.alkiot.length];
        for (int i = 0; i < lkm; i++) {
            klooni[i] = this.alkiot[i];
        }
        return klooni;    
    }
    
    /**
     * @return palauttaa jäsentaulukon jäsenien lukumäärän
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Hakee jäsenen nimen id-numeron perusteella
     * @param id id jonka nimenhaltijaa etsitään
     * @return palauttaa etsityn idn omaajan nimen
     */
    public String getNimiId(int id) {
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getId() == id) return alkiot[i].getNimi();            
        }
        return "virhe";
    }
    
    /**
     * lasketaan uudet Elo-pisteet pelaajille pelatun pelin jälkeen, sekä lisätään havio/voitti + pelattu peli asianmukaisesti
     * @param p1 pelaaja 1
     * @param p2 pelaaja 2
     * @param tulos true jos p1 voitti false jos p2 voitti
     */
    public void laskeTulos(Jasen p1, Jasen p2, Boolean tulos) {
        if (tulos) {
            p1.setElo(p1.getElo() + 20); //TODO: IMPLEMENTOI ELO-LASKURI
            p2.setElo(p2.getElo() - 20);
            p1.lisaaVoitto();
            p2.lisaaHavio();
        }
        else {
            p1.setElo(p1.getElo() - 20);
            p2.setElo(p2.getElo() + 20);
            p2.lisaaVoitto();
            p1.lisaaHavio();
        } 

    }
    /**
     * asetetaan tiedostolle nimi
     * @param nimi joka asetetaan
     */
    public void setTiedostonNimi(String nimi) {
         this.tiedNimi = nimi + ".dat" ;
    }
    
    /**
     * luetaan tiedostosta jäsenten tiedot ja lisätään jäsenistöön
     * @param tiedosto tiedostonnimi josta luetaan
     */
    public void lueTiedostosta() throws FileNotFoundException {
        //setTiedostonNimi(tiedosto);
        String rivi;
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            while (fi.hasNext()) {
                rivi = fi.nextLine().trim();
                if (rivi.equals("") || rivi.startsWith(";")) continue;
                Jasen jasen = new Jasen();
                jasen.parse(rivi);
                lisaa(jasen);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ei l�ytynyt tiedostoa, jatketaan");
        }
    }
    
    /**
     * Tallentaa jäsenistön tiedostoon
     */
    public void tallennaTiedostoon() {
        File tiedosto = new File(tiedNimi);
        try (PrintStream os = new PrintStream(new FileOutputStream(tiedosto))) {
            Jasen jasen;
            for (int i = 0; i < lkm; i++) {
                jasen = anna(i);
                jasen.tulosta(os);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * @param i indeksi
     * @return alkion indeksin kohdalta
     * @throws IndexOutOfBoundsException heittää virhettä jos väärä indeksi
     */
    public Jasen anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > lkm) throw new IndexOutOfBoundsException("Laitonta! " + i);
        return alkiot[i];
    }
    
    /**
     * lisätään jäsen taulukkoon, jos taulukko ylittyy niin tuplataan sen koko
     * @param lisattava jäsen joka lisätään taulukkoon
     */
    public void lisaa(Jasen lisattava) {
       try {
            this.alkiot[lkm] = lisattava;
            lkm++;
      }catch (Exception e) {
            this.alkiot = kloonaaJaKasvata();
            System.out.println("kasvatetaan taulukon kokoa! koko nyt: " + alkiot.length);
            this.lisaa(lisattava);
      } 
       
    }
    /**
     * lisää jäsenistöön uuden jäsenen
     * @param nimi nimi jolla luodaan
     * @param vuosikurssi vuosikurssi jolla luodaan
     */
    public void lisaa(String nimi, int vuosikurssi) {
        Jasen uusi = new Jasen(nimi, vuosikurssi);
        uusi.rekisteroi();
        this.lisaa(uusi);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Jasenet jasenet = new Jasenet();
        Jasen anski = new Jasen();
        Jasen mahti = new Jasen();
        anski.rekisteroi(); mahti.rekisteroi();
        anski.taytaAnski(); mahti.taytaMahti();
        
        jasenet.lisaa(anski);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(anski);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
        jasenet.lisaa(mahti);
       
        System.out.println("=^:=^:=^:==^:=^=:=^:=TEST^_=^_=^_=^_=^=_");
        for (int i = 0; i < jasenet.getLkm(); i++) {
            Jasen testi = jasenet.anna(i);
            System.out.println("Taulukon paikassa " + i + " oleva jäsen");
            testi.tulosta(System.out);
        }
    
    }

  

}
