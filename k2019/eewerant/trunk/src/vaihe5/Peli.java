/**
 * 
 */
package vaihe5;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * luokka osaa luoda uuden pelin kahden pelin vÔøΩlille ja tietÔøΩÔøΩ sen ajan
 * @author eewerant
 *
 */
public class Peli {
	
	private static int pelilaskuri = 0;
	
	private Date pvm = new Date();
	private int p1Id;
	private int p2Id;
    private int pelinId;
	private Boolean tulos;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'klo:' HH:mm");
	

	/**
	 * Luodaan uusi peli kahden pelaajan vÔøΩlille
	 * @param p1 Pelaaja 1 Jaasen luokasta
	 * @param p2 Pelaaja 2 Jasen luokasta
	 * @param tulos pelin tulos
	 * @param p1Nimi pelaaja1 nimi
	 * @param p2Nimi pelaaja 2 nimi
	 */
	public Peli(int p1, int p2, Boolean tulos) {
		this.p1Id = p1;
		this.p2Id = p2;
		this.pelinId = pelilaskuri;
		this.tulos = tulos;
		pelilaskuri++;
		
	}
	/**
	 * peli oletusatribuuteilla
	 */
	public Peli() {
		// tyhj‰ konstruktori alustuu itsest‰‰n
	}
	
	/**
	 * @return palauttaa pelaaja 1 idn
	 */
	public int getP1Id() {
		return p1Id;
	}
	
	/**
	 * @return palauttaa pelaaja 2 idn
	 */
	public int getP2Id() {
		return p2Id;
	}
	
	/**
	 * palauttaa pelin p‰iv‰m‰‰r‰n merkkijonona
	 * @return ylla sanottu
	 */
	public String getPvmString() {
		return dateFormat.format(pvm);
	}
	
	/**
	 * muuttaa Boolean tuloksen k‰ytt‰j‰lle ymm‰rrett‰v‰‰n merkkijonomuotoon
	 * @return
	 */
	public String getTulosString() {
		if(tulos) return "W - L";
		return "L - W"; 
	}
	
	public void setPelinId(int pelinId) {
		this.pelinId = pelinId;
		if (pelinId > pelilaskuri) pelilaskuri = pelinId + 1;
	}
	/**
	 * @return palauttaa merkkijonoesityksen pelist√§
	 */
	@Override
    public String toString() {
		
		
		String pvmJono = dateFormat.format(pvm);		
		
		return pelinId + "|" + p1Id + "|" + p2Id + "|" + pvmJono + "|" + tulos.toString();
	   /* String stulos = "";
        if (tulos) stulos = "W - L";
        else stulos = "L - W";
        SimpleDateFormat date = new SimpleDateFormat("E dd.MM.yyyy 'klo:' HH.mm");
        return String.format("%40s   |   %-5s   |   %24s" , (p1Nimi + " - " + p2Nimi), stulos, date.format(pvm));*/
	}
	
	 public void parse(String rivi) {
	        StringBuffer sb = new StringBuffer(rivi);
	        try {
	        setPelinId((Mjonot.erota(sb, '|', this.pelinId)));
	        p1Id = Mjonot.erota(sb, '|', p1Id);
	        p2Id = Mjonot.erota(sb, '|', p2Id);
			pvm = dateFormat.parse(Mjonot.erota(sb, '|', dateFormat.format(pvm))); //TODO NULL pointteria t‰st‰ jostain syyst‰
	        tulos = Boolean.parseBoolean(Mjonot.erota(sb, '|', "true"));
	    	} catch (ParseException e) {
				
				e.printStackTrace();
			}
	    }

		
	
	/**
	 * tulsotaa pelin osallistujat ja tuloksen valittuun tietovirtaan
	 * @param os tietovorta mihin tulostetaan
	 */
	public void tulosta(PrintStream os) {		
		os.println(toString());		
	}
	

	/**
	 * testip√§√§ohjelma Peli-luokalle
	 * @param args ei k√§yt√∂ss√§
	 */
	public static void main(String[] args) {
		Jasen anski = new Jasen(); Jasen mahti = new Jasen();
		anski.taytaAnski(); mahti.taytaMahti();
		anski.rekisteroi(); mahti.rekisteroi();
		Peli testipeli = new Peli(anski.getId() , mahti.getId(), true);
		// testipeli.pelaa();
		testipeli.tulosta(System.out);
		
	}





}
