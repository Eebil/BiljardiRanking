package biljardi;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vaihe5.Biljardi;
import vaihe5.Jasen;

/**
 * @author Eetu Rantala
 * @version 11.3.2019
 * luokka pääikkunan hallintaa varten
 *
 */
public class PaaikkunaGUIController implements ModalControllerInterface<String> {

	@FXML private Button Poistu;
	@FXML private ListChooser<Jasen> rankingLista;
    @FXML private TextField hakuLaatikko;
    @FXML private ListChooser<Jasen> hakuChooser;


	@FXML void handleHae() {
	     haePelaajaa(hakuChooser, hakuLaatikko);
    }
	@FXML void handleApua() {
		apuja();

    }
    @FXML void handleAvaa() {
    	avaa();
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("BiljardiGUIView.fxml"), "Liigan nimi", null, "");
	}	
	@FXML void handleLisaaJasen() {
		lisaaJasen();
    }
    
	@FXML void handleLopeta() {
    	Platform.exit();
    }
    @FXML void handlePelihistoria() {
        avaaPelihistoria();
    	
    }
    @FXML void handleTallenna() {
    	tallenna();
    }
    @FXML void handleTietoja() {
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("TietojaGUIView.fxml"), "Tietoja", null, "" );
    	
    }
    @FXML void handleUusiPeli() {
    	avaaUusiPeli();
    	
    }
    @FXML void handlePoistu() {
    	 ModalController.closeStage(Poistu);
    }
    @FXML void handlePelaajanTiedot() {
    	naytaPelaajanTiedot();
    	paivita();
    	
    }

	@Override
	public String getResult() {
		return null;
	}
	@Override
	public void handleShown() {
		// Auto-generated method stub
		
	}
	@Override
	public void setDefault(String arg0) {
		// Auto-generated method stub
		
	}
    
 //-----------------------------------------------------------
	private Biljardi biljardi;
	private String rankingNimi = "biljardisankarit";
	private Boolean muutettu = true;
	
	
	/**
	*
     * @return palauttaa onko tallennettu ja voiko turvallisesti sulkea ohjelman
     */
    public boolean voikoSulkea() {
        if (muutettu) { 
            tallenna();
            System.out.println("tallenetaan!");
        }
        return true;
    }
   
    
    /**
     * @param nimi tiedoston polusta
     */
    public void lueTiedosto(String nimi) {
    	rankingNimi = nimi;
    	ModalController.getStage(rankingLista).setTitle("Ranking - " + rankingNimi);
    	try {
			biljardi.lueTiedostosta(rankingNimi);
			paivita();
		} catch (FileNotFoundException e) {
			System.out.println("EI L�YDY TIEDOSTOJA!");
		}
    }
    
    /**
     * tallennetaan muutokset asetettuun polkuun
     */
    public void tallenna() {
    	biljardi.tallenna();
    	muutettu = false;
    }
    
    /**
     * avaa tiedoston kerhon luettavaksi
     * @return voiko avata
     */
    public boolean avaa() {
    	String liiganNimi = BiljardiGUIController.kysyNimi(null, rankingNimi);
    	if (liiganNimi == null) return false;
    	lueTiedosto(liiganNimi);
        return true;
    }
    /**
     * asettaa kontrollerille biljardi-otuksen
     * @param biljardi bilis olio
     */
    public void setRanking(Biljardi biljardi) {
		this.biljardi = biljardi;
		
	}
	 private void lisaaJasen() {
	    ModalController.showModal(PaaikkunaGUIController.class.getResource("LisaaPelaaja.fxml"), "Lisaa Pelaaja", null, biljardi);
		paivita();
		muutettu = true;
	 }
	 
	 /**
	 * verrataan hakulaatikkoon kirjoitettua tekstiä jäsenten nimiin ja laitetaan listaan osumat
	 * @param hakuLista ListChooser, johon hakutulokset laitetaan lajiteltuna
	 * @param hakuField tekstikenttä mihin kirjoitetaan hakusana, joka muutetaan regexiksi
	 */
	public void haePelaajaa(ListChooser<Jasen> hakuLista, TextField hakuField) {
	     hakuLista.clear();
	     List<Jasen> osumat = new ArrayList<Jasen>();
	     String hakusana = "(?i:.*" + hakuField.getText().trim() + ".*)";
	     System.out.println(hakusana);
	     for (int i = 0 ; i < biljardi.getLkm(); i++) {
	         Jasen j = biljardi.annaJasen(i);
	         if (j.getNimi().matches(hakusana)) osumat.add(j);
	     }
	     
	     Collections.sort(osumat, new SortNimi());
	     for (int i = 0 ; i < osumat.size(); i++) {
	         hakuLista.add(osumat.get(i).getNimi(), osumat.get(i));
	     }
	 }
	
	/**
	 * 
	 * Lajitellaan osumat nimen mukaan
	 *
	 */
	public static class SortNimi implements Comparator<Jasen> {

        @Override
        public int compare(Jasen o1, Jasen o2) {
            return o1.getNimi().compareTo(o2.getNimi());
        }
	    
	    
	}
	 
	 /**
	 * Lajittelee jäsenet laskevaan järjestykseen elon perusteella
	 */
	public static class SortElo implements Comparator<Jasen> {

	     @Override
	     public int compare(Jasen o1, Jasen o2) {
	         return o2.getElo() - o1.getElo();
	     }
	     
	 }
    
    private void paivita() {
    	rankingLista.clear();
    	List<Jasen> lajittelu = new ArrayList<Jasen>();
        for (int i = 0; i < biljardi.getLkm(); i++) {
        	Jasen jasen = biljardi.annaJasen(i);;
        	lajittelu.add(jasen);
       }
        Collections.sort(lajittelu, new SortElo());
        for (int i = 0; i < biljardi.getLkm(); i++) {
            Jasen jasen = lajittelu.get(i);
            jasen.setRanking(i + 1);
            rankingLista.add(String.format("%3d %-20s %d", jasen.getRanking(), jasen.getNimi(), jasen.getElo()), jasen);          
        }
    }
    
    /**
     * avaa kontrollerin pelihistoria-näkymään
     */
    public void avaaPelihistoria() {
        List<String> kokoPelihistoria = biljardi.annaPelihistoria();
        ModalController.showModal(PaaikkunaGUIController.class.getResource("Pelihistoria.fxml"), "Pelihistoria", null, kokoPelihistoria);
    }
    
    /**
     * Avataan uuden pelin kontrolleri, jolle annetaan biljardi-olio pelin tuloksen rekisteröimiseksi
     */
    public void avaaUusiPeli() {
    	
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("UusiPeli.fxml"), "Uusi Peli", null, biljardi);
    	paivita();
    	muutettu = true;
    }
    
    private void naytaPelaajanTiedot() {
        Jasen jasen;
       
    	    if (rankingLista.getSelectedObject() != null) {jasen = rankingLista.getSelectedObject();  paivita();}
    	    else if (hakuChooser.getSelectedObject() != null) jasen = hakuChooser.getSelectedObject();   	    
    	    else return;
    	ModalController.<Jasen, PelaajanTiedotGUIController>showModal(PelaajanTiedotGUIController.class.getResource("PelaajanTiedot.fxml"), "Pelaajan tiedot", null, jasen, ctrl -> ctrl.setBiljardi(biljardi));
    	muutettu = true;
    }
    
    
    /**
     * Avaa TIM-suunnitelmasivun
     * 
     */
	 private void apuja() {
         Desktop desktop = Desktop.getDesktop();
         try {
             URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2019k/ht/eewerant");
             desktop.browse(uri);
         } catch (URISyntaxException e) {
             return;
         } catch (IOException e) {
             return;
         }
     }
	
    
}
