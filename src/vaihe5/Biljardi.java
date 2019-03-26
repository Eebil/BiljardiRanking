/**
 * 
 */
package vaihe5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public List<String> annaPelihistoria() {
        return muutaPelinTiedoiksi(pelihistoria.haeKaikkiPelit());
    }
    
    /**
     * hakee tietyn jäsenen kaikki pelit
     * @param kenen kenen pelejä haetaan
     * @return kaikki pelaajan pelit
     */
    public List<String> pelaajanPelihistoria(Jasen kenen) {
        return muutaPelinTiedoiksi(pelihistoria.haePelit(kenen));
    }
    /**
     * muutetaan pelihistoria sellaiseen muotoon, ett� se voidaan tulostaa k�ytt�liittym�n StrinGridiin
     * ,eli postetaan turhat ja korvataan id-numerot pelaajien nimill�
     * @param pelihistoria lista merkkijonoksi muutetuista peleist�
     * @return uusi lista, jossa tiedot muotoa p1 - p2
     */
    public List<String> muutaPelinTiedoiksi(List<Peli> pelihistoria){
    	List<String> muutettu = new ArrayList<String>();
    	StringBuilder sb;
    	for (Peli peli : pelihistoria) {
    		sb = new StringBuilder();
    		sb.append(jasenet.annaJasenId(peli.getP1Id()).getNimi() + " - " + jasenet.annaJasenId(peli.getP2Id()).getNimi() + "|" + peli.getTulosString() + "|" + peli.getPvmString());
    		muutettu.add(sb.toString());
    	}
    	return muutettu;
    }
    
    /**
     * Lisätään uusi peli rekisteriin kahden pelaajan välillä
     * @param p1 pelaaja 1
     * @param p2 pelaaja 2
     * @param tulos true, jos 1 voitti, false jos 2 voitti
     */
    public void pelaa(Jasen p1, Jasen p2, Boolean tulos) {
        pelihistoria.lisaa(p1.getId(), p2.getId(), tulos);
        jasenet.laskeTulos(p1, p2, tulos);
    }
    
    /**
     * tallennetaan tiedot tiedostoon
     */
    public void tallenna() {
        jasenet.tallennaTiedostoon();
        pelihistoria.tallennaTiedostoon();
    }
    
    /**
     * @param tiedosto tiedostonnimi
     */
    public void lueTiedostosta(String tiedosto) throws FileNotFoundException {
        this.jasenet = new Jasenet();
        this.pelihistoria = new Pelihistoria();
        
        setTiedosto(tiedosto);
        jasenet.lueTiedostosta();
        pelihistoria.lueTiedostosta();
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
        System.out.println("nyt on luotu hakemisto: " + hakemisto + "jasenet"); //testi, poista my�hemmin
        pelihistoria.setTiedostonNimi(hakemisto + "pelihistoria");
        
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
