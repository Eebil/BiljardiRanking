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
        this.nimi = "Anski Panski";
        this.vuosikurssi = 2015;               
    }
    
    /**
     * töyttää mahdilla
     */
    public void taytaMahti() {
        this.nimi = "Mahti Mahturi";
        this.vuosikurssi = 1690;
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
