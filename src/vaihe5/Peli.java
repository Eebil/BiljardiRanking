/**
 * 
 */
package vaihe5;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * luokka osaa luoda uuden pelin kahden pelin v‰lille ja tiet‰‰ sen ajan
 * @author eewerant
 *
 */
public class Peli {
	
	private static int pelilaskuri = 0;
	
	private final Date pvm = new Date();
	private Jasen p1;
	private Jasen p2;
	private int pelinId;
	private Boolean tulos;
	
	

	/**
	 * Luodaan uusi peli kahden pelaajan v‰lille
	 * @param p1 Pelaaja 1 Jaasen luokasta
	 * @param p2 Pelaaja 2 Jasen luokasta
	 * @param tulos pelin tulos
	 */
	public Peli(Jasen p1, Jasen p2, Boolean tulos) {
		this.p1 = p1;
		this.p2 = p2;
		this.pelinId = pelilaskuri;
		this.tulos = tulos;
		
	}
	
	public Jasen getP1() {
		return p1;
	}
	
	public Jasen getP2() {
		return p2;
	}
	/**
	 * pelataan peli ja muutetaan pelaajien atribuutteja sen mukaisesti
	 */
	public void pelaa() {
		if (tulos) {
			p1.setElo(p1.getElo() + 20);
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
	 * tulsotaa pelin osallistujat ja tuloksen valittuun tietovirtaan
	 * @param os tietovorta mihin tulostetaan
	 */
	public void tulosta(PrintStream os) {
		String stulos = "";
		if (tulos) stulos = "W - L";
		else stulos = "L - W";
		SimpleDateFormat date = new SimpleDateFormat("E dd.MM.yyyy 'klo:' HH.mm");
		os.println(String.format("%40s      %-5s      %24s" , (p1.getNimi() + " - " + p2.getNimi()), stulos, date.format(pvm)));
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jasen anski = new Jasen(); Jasen mahti = new Jasen();
		anski.taytaAnski(); mahti.taytaMahti();
		anski.rekisteroi(); mahti.rekisteroi();
		Peli testipeli = new Peli(anski , mahti, true);
		testipeli.pelaa();
		testipeli.tulosta(System.out);
		
	}





}
