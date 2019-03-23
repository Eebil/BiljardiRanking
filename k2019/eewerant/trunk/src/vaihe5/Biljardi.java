/**
 * 
 */
package vaihe5;

import java.io.File;
import java.util.List;

/**
 * @author eewerant
 * @version 13.3.2019
 *
 */
public class Biljardi {
    
    private  Jasenet jasenet = new Jasenet(); 
    private  Pelihistoria pelihistoria = new Pelihistoria();

    /**
     * @param j jäsenistöön lisättävä jäsen
     */
    public void lisaa(Jasen j) {
        jasenet.lisaa(j);
    }
    /**
     * Lisätään uusi jäsen annetulla nimellä ja vuosikurssilla
     * @param nimi jäsenen nimi
     * @param vuosikurssi jäsenen vuosikurssi
     */
    public void lisaaJasen(String nimi, int vuosikurssi) {
        jasenet.lisaa(nimi, vuosikurssi);
    }
    
    /**
     * @return palauttaa jäsenten määrän
     */
    public int getLkm() {
        return jasenet.getLkm();
    }
    
    /**
     * @param i indeksi josta haetaan
     * @return paikassa ollut jäsen
     */
    public Jasen annaJasen(int i) {
        return jasenet.anna(i);
    }
    
    /**
     * @return palauttaa listan kaikista pelatuista peleistä
     */
    public List<Peli> annaPelihistoria() {
        return pelihistoria.haeKaikkiPelit();
    }
    
    /**
     * hakee tietyn jäsenen kaikki pelit
     * @param kenen kenen pelejä haetaan
     * @return kaikki pelaajan pelit
     */
    public List<Peli> pelaajanPelihistoria(Jasen kenen) {
        return pelihistoria.haePelit(kenen);
    }
    
    /**
     * Lisätään uusi peli rekisteriin kahden pelaajan välillä
     * @param p1 pelaaja 1
     * @param p2 pelaaja 2
     * @param tulos true, jos 1 voitti, false jos 2 voitti
     */
    public void pelaa(Jasen p1, Jasen p2, Boolean tulos) {
        pelihistoria.lisaa(p1.getId(), p2.getId(), tulos, p1.getNimi(), p2.getNimi());
        jasenet.laskeTulos(p1, p2, tulos);
    }
    
    /**
     * tallennetaan tiedot tiedostoon
     */
    public void tallenna() {
        jasenet.tallennaTiedostoon();
        // pelihistoria.tallenna
    }
    
    /**
     * @param tiedosto tiedostonnimi
     */
    public void lueTiedostosta(String tiedosto) {
        this.jasenet = new Jasenet();
        // this.pelihistoria = new Pelihistoria();
        
        setTiedosto(tiedosto);
        jasenet.lueTiedostosta(tiedosto);
    }
    
    /**
     * asettaa tiedostojen nimet
     * @param tiedosto nimi joka tuodaan
     */
    public void setTiedosto(String tiedosto) {
        File dir = new File(tiedosto);
        dir.mkdirs();
        String hakemisto = "";
        if (!tiedosto.isEmpty()) hakemisto = tiedosto + "/";
        jasenet.setTiedostonNimi(hakemisto + "jasenet");
       // pelihistoria.setTiedostonNimi(hakemisto + "pelihistoria");
        
    }
    /**
     * 
     * testipääohjelma biljardi-luokalle
     * @param args ei käytösä
     */
    public static void main(String[] args) {
        
        Biljardi sankarit = new Biljardi();
        
      //  sankarit.lueTiedostosta("biljardisankarit.dat");
        
        Jasen anski = new Jasen();
        Jasen mahti = new Jasen();
        anski.rekisteroi();
        mahti.rekisteroi();
        anski.taytaAnski();
        mahti.taytaMahti();
        
        sankarit.lisaa(anski);
        sankarit.lisaa(mahti);
        
        System.out.println("!== testaillaan ==!");
        
        System.out.println("kerhossa on tällä hetkellä " + sankarit.getLkm() + " jäsentä");
        for (int i = 0; i < sankarit.getLkm(); i++) {
            Jasen jasen = sankarit.annaJasen(i);
            System.out.println("jäsen paikassa " + i);
            jasen.tulosta(System.out);
        }
        
        sankarit.lisaa(anski);
        sankarit.lisaa(mahti);
        sankarit.lisaa(anski);
        sankarit.lisaa(mahti);
        sankarit.lisaa(anski);
        sankarit.lisaa(mahti);
        
        System.out.println("kerhossa on tällä hetkellä " + sankarit.getLkm() + " jäsentä");
        for (int i = 0; i < sankarit.getLkm(); i++) {
            Jasen jasen = sankarit.annaJasen(i);
            System.out.println("jäsen paikassa " + i);
            jasen.tulosta(System.out);
        }
    }

}
