package vaihe5;


/**
 * erilaista härpäkettä tietojen käsittelyyn
 * @author Eetu Rantala
 * @version  27.4.2019
 *
 */
public class Yleiset {
	
	/**
	 * arpoo kokonaisluvun ala- ja ylärajan väliltä
	 * @param ala alaraja
	 * @param yla yläraja
	 * @return arvottu numero
	 */
	public static int arpoja(int ala, int yla) {
		double n = (yla - ala)*Math.random() + ala;
		return (int)Math.round(n);
		
	}
	/**
	 * Lasketaan todenn�k�isyys pelaajan voitolle perustuen pelaajan ja vastustajan ELO-rankingiin kahden pelaajan yhteenlaskettu todenn�k�isyys on 1
	 * @param p1Elo Vastustaja ELO
	 * @param p2Elo Pelaajan ELO jonka todennäköisyys voittoon palautetaan
	 * @return todenn�k�isyys voitolle (v�lill� 0-1)
	 * @example
	 * <pre name="test">
	 *     laskeTodennakoisyys(1000, 1000) ~~~ 0.50;
	 *     laskeTodennakoisyys(0, 1000000)    ~~~ 1.0;
	 * </pre>
	 */
	public static double laskeTodennakoisyys(int p1Elo, int p2Elo) {
		
		return 1.0 * 1.0 / (1 + 1.0 *  (Math.pow(10, 1.0 *  (p1Elo - p2Elo) / 400)));
	}
}


