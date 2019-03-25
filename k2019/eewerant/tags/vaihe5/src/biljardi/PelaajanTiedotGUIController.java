package biljardi;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vaihe5.Jasen;


/**
 * @author eewerant
 * @version 11.3.2019
 *
 */
public class PelaajanTiedotGUIController implements ModalControllerInterface<Jasen> {
	
    @FXML private TextField pelaajaNimi;
    @FXML private TextField pelaajaRanking;
    @FXML private TextField pelaajaVuosikurssi;
    @FXML private TextField pelaajaElo;
    @FXML private TextField pelaajaPelatut;
    @FXML private TextField PelaajaWL;
	@FXML private Button tallennaJaPoistu;
    @FXML void handlePelihistoria() {
    	haePelihistoria();
    }



	@FXML void handlePoistaJasen() {
		Dialogs.showQuestionDialog("ui juma", "POISTETAANKO X J�SEN?", "VEKS!", "Ei sitenk��n..");
    }

    @FXML void handleTallennaJaPoistu() {
    	tallenna();
    	// ModalController.closeStage(tallennaJaPoistu); -- koittaa varmaan sulkea yht�aikaa dialogin kans
    }



	@Override
	public Jasen getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleShown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefault(Jasen jasen) {
		pelaajaNimi.setText(jasen.getNimi());
		pelaajaRanking.setText("" + jasen.getRanking());
		pelaajaVuosikurssi.setText("" + jasen.getVuosikurssi());
		pelaajaElo.setText("" + jasen.getElo());
		pelaajaPelatut.setText("" + jasen.getPelatut());
		PelaajaWL.setText(jasen.getVoitot() + " - " + jasen.getHaviot());
		
	}
//_----------------------------------------------------
	
	/**
	 * tallentaa pelin tiedot
	 */
	private void tallenna() {
		Dialogs.showMessageDialog("ei osata");
	}
	
	

					
   /**
    * hakee pelaajan henkil�kohtaisen pelihistorian
    */
	private void haePelihistoria() {
		// Hae my�hemmin t�h�n pelaajan henkil�kohtainen pelihistoria
		ModalController.showModal(PaaikkunaGUIController.class.getResource("Pelihistoria.fxml"), "Pelihistoria", null, "");		
	}
	
}
