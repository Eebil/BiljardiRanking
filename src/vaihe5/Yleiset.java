package vaihe5;


/**
 * erilaista hÃ¤rpÃ¤kettÃ¤ tietojen kÃ¤sittelyyn
 * @author eewerant
 * @version 14.3.2019
 *
 */
public class Yleiset {
	
	/**
	 * arpoo kokonaisluvun ala- ja ylÃ¤rajan vÃ¤liltÃ¤
	 * @param ala alaraja
	 * @param yla ylÃ¤raja
	 * @return arvottu numero
	 */
	public static int arpoja(int ala, int yla) {
		double n = (yla - ala)*Math.random() + ala;
		return (int)Math.round(n);
		
	}
	/**
	 * Lasketaan todennäköisyys pelaajan voitolle perustuen pelaajan ja vastustajan ELO-rankingiin kahden pelaajan yhteenlaskettu todennäköisyys on 1
	 * @param p1Elo Kysyttävän pelaajan ELO
	 * @param p2Elo Vastustajan ELO
	 * @return todennäköisyys voitolle (välillä 0-1)
	 */
	public static double laskeTodennakoisyys(int p1Elo, int p2Elo) {
		
		return 1.0 * 1.0 / (1 + 1.0 *  (Math.pow(10, 1.0 *  (p1Elo - p2Elo) / 400)));
	}
}


