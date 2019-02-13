package biljardi;

import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;


/**
 * @author eewerant
 * @version 31.1.2019
 *
 */
public class BiljardiGUIController {
	
	@FXML void handleAvaus() {
		avaaPaaikkuna();
    }
	//-----------------------------------

	private void avaaPaaikkuna() {		
		ModalController.showModal(PaaikkunaGUIController.class.getResource("Paaikkuna.fxml"), "paaikkuna", null, "");
	}

}