/**
 * 
 */
package vaihe5;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * luokka osaa luoda uuden pelin kahden pelin v�lille ja tiet�� sen ajan
 * @author eewerant
 *
 */
public class Peli {
	
	private static int pelilaskuri = 0;
	
	private final Date pvm = new Date();
	private int p1Id;
	private int p2Id;
	private String p1Nimi;
	private String p2Nimi;
	@SuppressWarnings("unused") //TODO POISTA JOS EI TARVITAKKAAN
    private int pelinId;
	private Boolean tulos;
	
	

	/**
	 * Luodaan uusi peli kahden pelaajan v�lille
	 * @param p1 Pelaaja 1 Jaasen luokasta
	 * @param p2 Pelaaja 2 Jasen luokasta
	 * @param tulos pelin tulos
	 * @param p1Nimi pelaaja1 nimi
	 * @param p2Nimi pelaaja 2 nimi
	 */
	public Peli(int p1, int p2, Boolean tulos, String p1Nimi, String p2Nimi) {
	    this.p1Nimi = p1Nimi;
	    this.p2Nimi = p2Nimi;
		this.p1Id = p1;
		this.p2Id = p2;
		this.pelinId = pelilaskuri;
		this.tulos = tulos;
		pelilaskuri++;
		
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
	 * tulsotaa pelin osallistujat ja tuloksen valittuun tietovirtaan
	 * @param os tietovorta mihin tulostetaan
	 */
	public void tulosta(PrintStream os) {
		String stulos = "";
		if (tulos) stulos = "W - L";
		else stulos = "L - W";
		SimpleDateFormat date = new SimpleDateFormat("E dd.MM.yyyy 'klo:' HH.mm");
		os.println(String.format("%40s      %-5s      %24s" , (p1Nimi + " - " + p2Nimi), stulos, date.format(pvm)));
	}
	

	/**
	 * testipääohjelma Peli-luokalle
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Jasen anski = new Jasen(); Jasen mahti = new Jasen();
		anski.taytaAnski(); mahti.taytaMahti();
		anski.rekisteroi(); mahti.rekisteroi();
		Peli testipeli = new Peli(anski.getId() , mahti.getId(), true, anski.getNimi(), mahti.getNimi());
		// testipeli.pelaa();
		testipeli.tulosta(System.out);
		
	}





}
