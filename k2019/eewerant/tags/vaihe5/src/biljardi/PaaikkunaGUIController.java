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
import vaihe5.Peli;

/**
 * @author eewerant
 * @version 11.3.2019
 *
 */
public class PaaikkunaGUIController implements ModalControllerInterface<String> {

	@FXML private Button Poistu;
	@FXML private ListChooser<Jasen> rankingLista;
    @FXML private TextField hakuLaatikko;
    @FXML private ListChooser<Jasen> hakuChooser;


	@FXML void handleHae() {
	     haePelaajaa();
		//Dialogs.showMessageDialog("Emm� osaa teh� t�t�");
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
    	//Dialogs.showMessageDialog("Emm� osaa teh� t�t�");
    	//Dialogs.showMessageDialog("Emm� osaa lis�st� viel�");
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
    	
    }

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void handleShown() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDefault(String arg0) {
		// TODO Auto-generated method stub
		
	}
    
 //-----------------------------------------------------------
	private Biljardi biljardi;
	private String rankingNimi = "biljardisankarit";
	
	
	/**
	*
     * @return palauttaa onko tallennettu ja voiko turvallisesti sulkea ohjelman
     */
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return true;
    }
   
    
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
    
    public void tallenna() {
    	biljardi.tallenna();
    }
    
    /**
     * avaa tiedoston kerhon luettavaksi TODO: koko paska
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
	 }
	 
	 /**
	 * verrataan hakulaatikkoon kirjoitettua tekstiä jäsenten nimiin ja laitetaan listaan osumat
	 */
	public void haePelaajaa() {
	     hakuChooser.clear();
	     List<Jasen> osumat = new ArrayList<Jasen>();
	     String hakusana = "(?i:.*" + hakuLaatikko.getText().trim() + ".*)";
	     System.out.println(hakusana);
	     for (int i = 0 ; i < biljardi.getLkm(); i++) {
	         Jasen j = biljardi.annaJasen(i);
	         if (j.getNimi().matches(hakusana)) osumat.add(j);
	     }
	     
	     Collections.sort(osumat, new SortNimi());
	     for (int i = 0 ; i < osumat.size(); i++) {
	         hakuChooser.add(osumat.get(i).getNimi(), osumat.get(i));
	     }
	 }
	
	/**
	 * 
	 * @author eewerant
	 * @version 22.3.2019
	 * Lajitellaan osumat nimen mukaan hash-coden avulla
	 *
	 */
	public static class SortNimi implements Comparator<Jasen> {

        @Override
        public int compare(Jasen o1, Jasen o2) {
            // TODO Auto-generated method stub
            return o1.getNimi().compareTo(o2.getNimi());
        }
	    
	    
	}
	 
	 /**
	 * @author eewerant
	 * @version 20.3.2019
	 * Lajittelee jäsenet laskevaan järjestykseen elon perusteella
	 */
	public static class SortElo implements Comparator<Jasen> {

	     @Override
	     public int compare(Jasen o1, Jasen o2) {
	         return o2.getElo() - o1.getElo();
	     }
	     
	 }
    
    private void paivita() {
    	rankingLista.clear(); //TODO: Laita nimet järjestykseen Elon mukaan
    	List<Jasen> lajittelu = new ArrayList<Jasen>();
    	// int index = 0;
        for (int i = 0; i < biljardi.getLkm(); i++) {
        	Jasen jasen = biljardi.annaJasen(i);
        //	if (jasen.getId() == idNro) index = i;
        	lajittelu.add(jasen);
       }
        Collections.sort(lajittelu, new SortElo());
        for (int i = 0; i < biljardi.getLkm(); i++) {
            Jasen jasen = lajittelu.get(i);
            jasen.setRanking(i + 1);
            rankingLista.add(String.format("%3d %-20s %20d", jasen.getRanking(), jasen.getNimi(), jasen.getElo()), jasen);          
        }
        
        // rankingLista.setSelectedIndex(index);
    }
    
    /**
     * avaa kontrollerin pelihistoria-näkymään
     */
    public void avaaPelihistoria() {
        List<Peli> kokoPelihistoria = biljardi.annaPelihistoria();
        ModalController.showModal(PaaikkunaGUIController.class.getResource("Pelihistoria.fxml"), "Pelihistoria", null, kokoPelihistoria);
    }
    
    /**
     * Avataan uuden pelin kontrolleri, jolle annetaan biljardi-olio pelin tuloksen rekisteröimiseksi
     */
    public void avaaUusiPeli() {
    	
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("UusiPeli.fxml"), "Uusi Peli", null, biljardi);
    	paivita();
    }
    
    private void naytaPelaajanTiedot() {
        Jasen jasen;
        //TODO: Heitä tästä vielä pelihistoria pelaajan tiedot ikkunalle, jotta voidaan näyttää henkilökohtainen pelihistoria
        //FIX: PASKA RATKASU JOKA EI TO*IMI KEKSI KEINO LAITTAA CHOOSERI PARAMETRINA
    	    if (rankingLista.getSelectedObject() != null) {jasen = rankingLista.getSelectedObject();  paivita();}
    	    else jasen = hakuChooser.getSelectedObject();
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("PelaajanTiedot.fxml"), "Pelaajan tiedot", null, jasen);
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
