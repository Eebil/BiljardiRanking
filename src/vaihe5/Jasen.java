package vaihe5;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka yksittäistä jäsentä varten
 * @author Eetu Rantala
 * @version 11.3.2019
 *
 */
public class Jasen {
    
    private static int idLaskin = 1;
    

    private int id;
    private String nimi = "";
    private int vuosikurssi;
    private int elo = 1500;
    private int pelatut;
    private int voitot;
    private int haviot;
    private int ranking;
    
    /**
     * Luo uuden jäsenen annetuilla parametreilla
     * @param nimi tuotu nimi
     * @param vuosikurssi tuotu vuosikurssi
     * @example
     * <pre name="test">
     *  Jasen testi = new Jasen("testimies", 2009);
     *  testi.getNimi() === "testimies";
     *  testi.getVuosikurssi() === 2009;
     * </pre>
     */
    public Jasen(String nimi, int vuosikurssi) {
        this.nimi = nimi;
        this.vuosikurssi = vuosikurssi;
    }

    /**
     * tyhjä kunstruktori
     */
    public Jasen() {
     
    }

    /**
     * rekisteröi jäsenen ja antaa uniikin id:n
     */
    public void rekisteroi() {
        // if (this.id <= idLaskin) idLaskin = this.id + 1; ei ehkä tähän
        
        if (this.id != 0) return;
        this.id = idLaskin;
        idLaskin++;
    }
    
    /**
     * täyttää jasenelle tiedot
     */
    public void taytaAnski() {
        this.nimi = "Anski Panski " + Yleiset.arpoja(1000, 4000);
        this.vuosikurssi = 2015;
        this.elo = Yleiset.arpoja(1000, 3000);
    }
    
    /**
     * töyttää mahdilla
     */
    public void taytaMahti() {
        this.nimi = "Mahti Mahturi " + Yleiset.arpoja(1000, 4000);
        this.vuosikurssi = 1690;
        this.elo = Yleiset.arpoja(1000, 3000);
    }
    /**
     * 
     * @return palauttaa j�senen id-numeron
     */
    public int getId() {
		return id;
	}
    
    /**
     * @return palauttaa rankingin
     */
    public int getRanking() {
    	return ranking;
    }
    
    /**
     * @return palauttaa elon
     */
    public int getElo() {
    	return elo;
    }
    
    /**
     * @return palauttaa nimen
     */
    public String getNimi() {
    	return nimi;
    }

    /**
     * @return palauttaa vuosikurssin
     */
    public int getVuosikurssi() {
    	return vuosikurssi;
    }
    /**
     * @return palauttaa pelatut pelit
     */
    public int getPelatut() {
    	return pelatut;
    }
    /**
     * @return palauttaa voitetut pelit
     */
    public int getVoitot() {
    	return voitot;
    }
    /**
     * @return palauttaa tappioy
     */
    public int getHaviot() {
    	return haviot;
    }
    
    /**
     * @param uusiNimi nimi joka asetetaan
     */
    public void setNimi(String uusiNimi) {
		this.nimi = uusiNimi;		
	}
    
    /**
     * @param uusiVuosi vuosikurssi mikä asetetaan
     */
    public void setVuosikurssi(int uusiVuosi) {
		this.vuosikurssi = uusiVuosi;
		
	}
    /**
     * setteri id-nu,eroa varten. asettaa laskurin oikeaan paikkaan jos ylittää sen
     * @param id id joka asetetaan jäsenelle
     */
    public void setId(int id) {
        this.id = id;
        if (id >= idLaskin) idLaskin = id + 1;
    }
    
    /**
     * asettaa elon
     * @param elo elo joka laitetaan
     */
    public void setElo(int elo) {
    	this.elo = elo;
    }
    /**
     * asettaa tämänhetkisen sijoituksen rankingissa elon perusteella
     * @param ranking monesko rankingissa
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    /**
     * lisä yhden voiton sekä pelatun pelin
     */
    public void lisaaVoitto() {
    	voitot++;
    	pelatut++;
    }
    /**
     * lisää yhden tappion sekä pelatun pelin
     */
    public void lisaaHavio() {
    	haviot++;
    	pelatut++;
    }
    
    @Override
    public String toString() {
        return (id + "|" + nimi + "|" + vuosikurssi + "|" + elo + "|" + pelatut + "|" + voitot + "|" +  haviot + "|" + ranking);
        
    }
    /**
     * Asetetaan jäsenelle tiedot merkkijonosta
     * @param rivi rivi josta tiedot haetaan
     * @example
     * <pre name="test">
     *  Jasen testi = new Jasen();
     *  testi.parse("900|hessu|9000|2301|4|2|2|3");
     *  testi.getNimi() === "hessu";
     *  testi.getElo() === 2301;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setId(Mjonot.erota(sb, '|', getId()));
        nimi = Mjonot.erota(sb, '|', nimi);
        vuosikurssi = Mjonot.erota(sb, '|', vuosikurssi);
        elo = Mjonot.erota(sb, '|', elo);
        pelatut = Mjonot.erota(sb, '|', pelatut);
        voitot = Mjonot.erota(sb, '|', voitot);
        haviot = Mjonot.erota(sb, '|', haviot);
        ranking = Mjonot.erota(sb, '|', ranking);       
    }

    /**
     * @param out tietovirta
     * tulostelee tiedot
     */
    public void tulosta(PrintStream out) {
        out.println(toString());      
    }
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Jasen anski = new Jasen();
        Jasen mahti = new Jasen();
        
        anski.rekisteroi();
        mahti.rekisteroi();
        
        anski.tulosta(System.out);
        mahti.tulosta(System.out);
        

        anski.taytaAnski();
        mahti.taytaMahti();
        
        anski.tulosta(System.out);
        mahti.tulosta(System.out);
        
    }

	

	

	

}
