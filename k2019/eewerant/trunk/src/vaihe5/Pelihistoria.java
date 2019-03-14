/**
 * 
 */
package vaihe5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * luokka pelien säilyttämistä varten
 * @author eewerant
 *
 */
public class Pelihistoria implements Iterable<Peli> {
	
	private final Collection<Peli> pelit = new ArrayList<Peli>(); 
	
	
	/**
	 * iteraattori pelien läpikäymiseen. työn alla
	 */
	@Override
	public Iterator<Peli> iterator() {
		return pelit.iterator();
	}
	
	public void lisaa(Peli peli) {
		pelit.add(peli);
	}
	
	/**
	 * hakee kaikki pelit jossa haettava pelaaja on ollut osallisena
	 * @param kenen Kenen pelejä haetaan
	 * @return kaikki pelit jossa pelaaja on ollut osallisena
	 */
	public List<Peli> haePelit(Jasen kenen) {
		List<Peli> hakijanPelit = new ArrayList<Peli>();
		for (Peli peli: pelit) {
			if (peli.getP1().getId() == kenen.getId() || peli.getP2().getId() == kenen.getId() ) hakijanPelit.add(peli);
		}
		return hakijanPelit;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pelihistoria testihistoria = new Pelihistoria();
		
		Jasen anski = new Jasen(); Jasen mahti = new Jasen();
		anski.taytaAnski(); mahti.taytaMahti();
		anski.rekisteroi(); mahti.rekisteroi();
		Peli testipeli1 = new Peli(anski , mahti, true);
		Peli testipeli2 = new Peli(anski, mahti, false);
		Peli testipeli3 = new Peli(mahti, anski, false);
		Peli testipeli4 = new Peli(mahti, mahti, false);
		
		testihistoria.lisaa(testipeli1);
		testihistoria.lisaa(testipeli2);
		testihistoria.lisaa(testipeli3);
		testihistoria.lisaa(testipeli4);
		
		System.out.println("-.-.-.-.-.-.-.Testataanharrastuksia-.-..--.");
		
		List<Peli> anskinPelit = testihistoria.haePelit(anski);
		System.out.println("Anskin pelihistoria on seuraavanlainen:");
		
		for (Peli peli : anskinPelit) {
			peli.tulosta(System.out);
		}
		

	}

	
}
