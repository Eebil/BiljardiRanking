/**
 * 
 */
package vaihe5;

/**
 * @author eewerant
 * @version 13.3.2019
 *
 */
public class Biljardi {
    
    private final Jasenet jasenet = new Jasenet(); 

    /**
     * @param j jäsenistöön lisättävä jäsen
     */
    public void lisaa(Jasen j) {
        jasenet.lisaa(j);
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
