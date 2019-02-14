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

public class PaaikkunaGUIController implements ModalControllerInterface<String> {

	@FXML private Button Poistu;

	@FXML void handleHae() {
		Dialogs.showMessageDialog("Emmä osaa tehä tätä");
    }
	@FXML void handleApua() {
		apuja();

    }
    @FXML void handleAvaa() {
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("BiljardiGUIView.fxml"), "Pelihistoria", null, "");
	}	
	@FXML void handleLisaaJasen() {
    	Dialogs.showMessageDialog("Emmä osaa tehä tätä");
    	//Dialogs.showMessageDialog("Emmä osaa lisästä vielä");
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
