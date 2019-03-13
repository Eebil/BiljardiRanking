package biljardi;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author eewerant
 * @version 11.3.2019
 *
 */
public class PaaikkunaGUIController implements ModalControllerInterface<String> {

	@FXML private Button Poistu;

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
    	Dialogs.showMessageDialog("Emm� osaa teh� t�t�");
    	//Dialogs.showMessageDialog("Emm� osaa lis�st� viel�");
    }
    @FXML void handleLopeta() {
    	Platform.exit();
    }
    @FXML void handlePelihistoria() {
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("Pelihistoria.fxml"), "Pelihistoria", null, "");
    }
    @FXML void handleTietoja() {;
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("TietojaGUIView.fxml"), "Tietoja", null, "");
    }
    @FXML void handleUusiPeli() {
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("UusiPeli.fxml"), "Uusi Peli", null, "");
    }
    @FXML void handlePoistu() {
    	 ModalController.closeStage(Poistu);
    }
    @FXML void handlePelaajanTiedot() {
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("PelaajanTiedot.fxml"), "Pelaajan tiedot", null, "");
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
	
	/*
	*
     * @return palauttaa onko tallennettu ja voiko turvallisesti sulkea ohjelman
     
    public boolean voikoSulkea() {
        // TODO Auto-generated method stub
        return false;
    }
    
    **
     * avaa tiedoston kerhon luettavaksi TODO: koko paska
     * @return voiko avata
     
    public boolean avaa() {
        // TODO Auto-generated method stub
        return false;
    }
    */
    
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
