/**
 * 
 */
package vaihe5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * luokka pelien s�ilytt�mist� varten
 * @author Eetu Rantala
 * @version  27.4.2019
 */
public class Pelihistoria implements Iterable<Peli> {
	
	private final Collection<Peli> pelit = new ArrayList<Peli>();
	private String tiedNimi = "";
	
	
	/**
	 * iteraattori pelien l�pik�ymiseen. ty�n alla
	 */
	@Override
	public Iterator<Peli> iterator() {
		return pelit.iterator();
	}
	/**
	 * Asetetaan jaseniston tiedostolle nimi
	 * @param nimi nimi joka tiedostolle tulee
	 */
	public void setTiedostonNimi(String nimi) {
		tiedNimi = nimi + ".dat";
	}
	/**
	 * luetaan tiedostosta tiedot ohjelmalle
	 * @example
	 * <pre name="test">
	 *  #import java.io.File;
	 *  #import java.util.Iterator;
     *  
     *  Pelihistoria testPelihistoria = new Pelihistoria();
     *  testPelihistoria.setTiedostonNimi("Testisankarit/pelihistoria");
     *  File testiTiedosto = new File("Testisankarit/pelihistoria.dat");
     *  File dir =  new File("Testisankarit");
     *  dir.mkdir();
     *  testPelihistoria.lisaa(2, 1, true);
     *  testPelihistoria.lisaa(3, 1, false);
     *  testPelihistoria.tallennaTiedostoon();
     *  testPelihistoria = new Pelihistoria();
     *  testPelihistoria.setTiedostonNimi("Testisankarit/pelihistoria");
     *  testPelihistoria.lueTiedostosta();
     *  Iterator<Peli> it = testPelihistoria.iterator();
     *  it.next().getP1Id() === 2;
     *  it.next().getP1Id() === 3;  
     *  testiTiedosto.delete() === true;
     *  dir.delete() === true;
     *  
	 * </pre>
	 */
	public void lueTiedostosta() {
		// setTiedostonNimi(tiedosto);
        String rivi;
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            while (fi.hasNext()) {
                rivi = fi.nextLine().trim();
                if (rivi.equals("") || rivi.startsWith(";")) continue;
                Peli peli = new Peli();
                peli.parse(rivi);
                pelit.add(peli);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ei l�ytynyt tiedostoa, jatketaan");
        }
	}
	
	/**
	 * Tallennetaan tiedot tiedostoon erotettuna tolppamerkillä
	 */
	public void tallennaTiedostoon() {
		File tiedosto = new File(tiedNimi);
        try (PrintStream os = new PrintStream(new FileOutputStream(tiedosto))) {
            Peli peli;
            for (var ottelu : pelit) {
                peli = ottelu;
                peli.tulosta(os);
            }
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
	}

	
	/**
	 * Lisätään uusi peli rekisteriiin
	 * @param p1Id pelaajan 1 Id
	 * @param p2Id Pelaajan 2 Id
	 * @param tulos True, jos p1 voitti, false jos p2
	 */
	public void lisaa(int p1Id, int p2Id, Boolean tulos) {
	    Peli uusipeli = new Peli(p1Id, p2Id, tulos);
	    pelit.add(uusipeli);
	}
	
	/**
	 * haetaan koko pelihistoria
	 * @return kaikki liigassa pelatut pelit
	 */
	public List<Peli> haeKaikkiPelit() {
	    List<Peli> kaikkiPelit = new ArrayList<Peli>();
	    for (Peli peli: pelit)
	        kaikkiPelit.add(peli);
	    return kaikkiPelit;
	}
	
	/**
	 * hakee kaikki pelit jossa haettava pelaaja on ollut osallisena
	 * @param kenen Kenen pelej� haetaan
	 * @return kaikki pelit jossa pelaaja on ollut osallisena
	 * @example
	 * <pre name="test">
	 * #import java.util.List;
	 * 
	 * Pelihistoria testiPh = new Pelihistoria();
	 * Jasen pelaaja = new Jasen();
	 * pelaaja.parse("1|||||||");
	 * testiPh.lisaa(1, 2, false);
	 * testiPh.lisaa(3, 4, true);
	 * testiPh.lisaa(4, 1, true);
	 * testiPh.lisaa(4, 2, false);
	 * List<Peli> haetut = testiPh.haePelit(pelaaja);
	 * haetut.get(0).getP1Id() === 1;
	 * haetut.get(1).getP1Id() === 4;
	 *  
	 * </pre>
	 */
	public List<Peli> haePelit(Jasen kenen) {
		List<Peli> hakijanPelit = new ArrayList<Peli>();
		for (Peli peli: pelit) {
			if (peli.getP1Id() == kenen.getId() || peli.getP2Id() == kenen.getId() ) hakijanPelit.add(peli);
		}
		return hakijanPelit;
		
	}
	

	/**
	 * testipääohjelma pelihistoria-luokalle
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Pelihistoria testihistoria = new Pelihistoria();
		
		Jasen anski = new Jasen(); Jasen mahti = new Jasen();
		anski.taytaAnski(); mahti.taytaMahti();
		anski.rekisteroi(); mahti.rekisteroi();
		testihistoria.lisaa(anski.getId() , mahti.getId(), true);
		testihistoria.lisaa(anski.getId(), mahti.getId(), false);
		testihistoria.lisaa(mahti.getId(), anski.getId(), false);
		testihistoria.lisaa(mahti.getId(), mahti.getId(), false);
		

		
		System.out.println("-.-.-.-.-.-.-.Testataanharrastuksia-.-..--.");
		
		List<Peli> anskinPelit = testihistoria.haePelit(anski);
		System.out.println("Anskin pelihistoria on seuraavanlainen:");
		
		for (Peli peli : anskinPelit) {
			peli.tulosta(System.out);
		}
		

	}

	
}
