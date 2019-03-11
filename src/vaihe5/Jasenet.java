/**
 * 
 */
package vaihe5;

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
