package biljardi;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PaaikkunaGUIController implements Initializable {

	@FXML void handleHae() {
		Dialogs.showMessageDialog("Emmä osaa tehä tätä");
    }
    @FXML void handleLisaaJasen() {
    	Dialogs.showMessageDialog("Emmä osaa hakea");
    }
    @FXML void handleLopeta() {
    	Platform.exit();
    }
    @FXML void handlePelihistoria() {
    	
    }
    @FXML void handleTietoja() {;
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("TietojaGUIView.fxml"), "Tietoja", null, "");
    }
    @FXML void handleUusiPeli() {
    	ModalController.showModal(PaaikkunaGUIController.class.getResource("UusiPeli.fxml"), "Uusi Peli", null, "");
    }
    @FXML void handleMahtia() {

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
 //-----------------------------------------------------------
    
    
}
