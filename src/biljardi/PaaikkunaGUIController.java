package biljardi;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

	@FXML void handleHae() {
		Dialogs.showMessageDialog("Emm� osaa teh� t�t�");
    }
	@FXML void handleApua() {
		apuja();

    }
    @FXML void handleAvaa() {
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
	
	
	/**
	*
     * @return palauttaa onko tallennettu ja voiko turvallisesti sulkea ohjelman
     */
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return true;
    }
    
    /**
     * avaa tiedoston kerhon luettavaksi TODO: koko paska
     * @return voiko avata
     */
    public boolean avaa() {
        // TODO Auto-generated method stub
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
			Jasen uusi = new Jasen();
			if (biljardi.getLkm() % 2 == 0) uusi.taytaAnski();
			else uusi.taytaMahti();
			uusi.rekisteroi();
			biljardi.lisaa(uusi);
			paivita();
	 }
    
    private void paivita() {
    	rankingLista.clear(); //TODO: Laita nimet järjestykseen Elon mukaan
    	
    	// int index = 0;
        for (int i = 0; i < biljardi.getLkm(); i++) {
        	Jasen jasen = biljardi.annaJasen(i);
        //	if (jasen.getId() == idNro) index = i;
        	rankingLista.add(String.format("%3d %20s %20d", jasen.getRanking(), jasen.getNimi(), jasen.getElo()), jasen);
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
        //TODO: Heitä tästä vielä pelihistoria pelaajan tiedot ikkunalle, jotta voidaan näyttää henkilökohtainen pelihistoria
    	Jasen jasen = rankingLista.getSelectedObject();
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
