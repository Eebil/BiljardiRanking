package biljardi;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PelaajanTiedotGUIController implements ModalControllerInterface<String> {

	@FXML private Button tallennaJaPoistu;

    @FXML void handlePelihistoria() {
    	haePelihistoria();
    }



	@FXML void handlePoistaJasen() {
		Dialogs.showQuestionDialog("ui juma", "POISTETAANKO X JÄSEN?", "VEKS!", "Ei sitenkään..");
    }

    @FXML void handleTallennaJaPoistu() {
    	tallenna();
    	// ModalController.closeStage(tallennaJaPoistu); -- koittaa varmaan sulkea yhtäaikaa dialogin kans
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
//_----------------------------------------------------
	
	
	/**
	 * tallentaa pelin tiedot
	 */
	private void tallenna() {
		Dialogs.showMessageDialog("ei osata tallentaa");
		
	}
   /**
    * hakee pelaajan henkilökohtaisen pelihistorian
    */
	private void haePelihistoria() {
		// Hae myöhemmin tähän pelaajan henkilökohtainen pelihistoria
		ModalController.showModal(PaaikkunaGUIController.class.getResource("Pelihistoria.fxml"), "Pelihistoria", null, "");		
	}
	
}
