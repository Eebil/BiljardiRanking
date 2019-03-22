package vaihe5;

import java.io.*;

/**
 * @author eewerant
 * @version 11.3.2019
 *
 */
public class Jasen {
    
    private static int idLaskin = 1;
    
    private String nimi;
    private int id;
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
     */
    public Jasen(String nimi, int vuosikurssi) {
        this.nimi = nimi;
        this.vuosikurssi = vuosikurssi;
    }

    /**
     * tyhjä kunstruktori
     */
    public Jasen() {
        // TODO Auto-generated constructor stub
    }

    /**
     * rekisteröi jäsenen ja antaa uniikin id:n
     */
    public void rekisteroi() {
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

    /**
     * @param out tietovirta
     * tulostelee tiedot
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", id));
        out.println("Nimi: " + nimi + " Vuosikurssi: " + vuosikurssi);
        out.println("Elo: " + elo);
        out.println("pelatut pelit: " + pelatut + " voitot: " + voitot + " häviöt " + haviot);
        out.println("rankingi tällä hetkellä: " + ranking);
        
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
