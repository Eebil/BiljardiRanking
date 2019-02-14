package biljardi;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * @author eewerant
 * @version 31.1.2019
 *
 */
public class BiljardiGUIController implements ModalControllerInterface<String> {
	
    @FXML private TextField liiganNimi;
	
	@FXML void handleAvaus() {
		avaaTiedosto();
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

	//-----------------------------------

	/*private void avaaPaaikkuna() {		
		ModalController.showModal(PaaikkunaGUIController.class.getResource("Paaikkuna.fxml"), "paaikkuna", null, "");
	}*/
	/**
	 * avaa dat. tiedoston liigan tietorakennetta varten
	 * 
	 */
	private void avaaTiedosto() {
		ModalController.closeStage(liiganNimi);
		Dialogs.showMessageDialog("ei voida");
		
	}
	
}