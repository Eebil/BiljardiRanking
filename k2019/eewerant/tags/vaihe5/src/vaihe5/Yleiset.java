package vaihe5;


/**
 * erilaista härpäkettä tietojen käsittelyyn
 * @author eewerant
 * @version 14.3.2019
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

}


