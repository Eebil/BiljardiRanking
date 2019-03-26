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
		Dialogs.showQuestionDialog("ui juma", "POISTETAANKO X JƒSEN?", "VEKS!", "Ei sitenk‰‰n..");
    }

    @FXML void handleTallennaJaPoistu() {
    	tallenna();
    	// ModalController.closeStage(tallennaJaPoistu); -- koittaa varmaan sulkea yhtÔøΩaikaa dialogin kans
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
		this.jasen = jasen;
		pelaajaNimi.setText(jasen.getNimi());
		pelaajaRanking.setText("" + jasen.getRanking());
		pelaajaVuosikurssi.setText("" + jasen.getVuosikurssi());
		pelaajaElo.setText("" + jasen.getElo());
		pelaajaPelatut.setText("" + jasen.getPelatut());
		PelaajaWL.setText(jasen.getVoitot() + " - " + jasen.getHaviot());
		
	}
//_----------------------------------------------------
	
	
	private Jasen jasen;
	/**
	 * tallentaa pelin tiedot
	 */
	private void tallenna() {
		if (!pelaajaVuosikurssi.getText().matches("\\d{4}") || pelaajaNimi.getText().length() > 25) {
			Dialogs.showMessageDialog("Liian pitk‰ nimi tai vuosikurssi v‰‰r‰‰ formaattia :< !");
			return;
		}
		jasen.setNimi(pelaajaNimi.getText());
		jasen.setVuosikurssi(Integer.parseInt(pelaajaVuosikurssi.getText()));
		ModalController.closeStage(tallennaJaPoistu);
	}
	
	

					
   /**
    * hakee pelaajan henkilÔøΩkohtaisen pelihistorian
    */
	private void haePelihistoria() {
		// Hae myÔøΩhemmin tÔøΩhÔøΩn pelaajan henkilÔøΩkohtainen pelihistoria
		ModalController.showModal(PaaikkunaGUIController.class.getResource("Pelihistoria.fxml"), "Pelihistoria", null, "");		
	}
	
}
