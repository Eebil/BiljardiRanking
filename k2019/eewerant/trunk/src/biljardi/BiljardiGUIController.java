package biljardi;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * @author Eetu Rantala
 * @version  27.4.2019
 *
 */
public class BiljardiGUIController implements ModalControllerInterface<String> {
	
    @FXML private TextField liiganNimi;
    private String vastaus = null;
	
	@FXML void handleAvaus() {
		vastaus = liiganNimi.getText();
		ModalController.closeStage(liiganNimi);
    }

	@Override
	public String getResult() {
		return vastaus;
	}
	@Override
	public void handleShown() {
		liiganNimi.requestFocus();
		
	}
	@Override
	public void setDefault(String oletus) {
		liiganNimi.setText(oletus);
		
	}

	//-----------------------------------

	/*private void avaaPaaikkuna() {		
		ModalController.showModal(PaaikkunaGUIController.class.getResource("Paaikkuna.fxml"), "paaikkuna", null, "");
	}*/


		
	
	/**
	 * kysytään ohjelmaa avattaessa rankingin nimi
	 * @param modalityStage stage
	 * @param oletus oletusarvo
	 * @return rankingin nimi
	 */
	public static String kysyNimi(Stage modalityStage, String oletus) {
		return ModalController.showModal(BiljardiGUIController.class.getResource("BiljardiGUIView.fxml"), "BiljardiRanking", modalityStage, oletus);
	}

    

    
	
}