/**
 * 
 */
package biljardi;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import vaihe5.Biljardi;
import vaihe5.Jasen;

/**
 * @author eewerant
 *
 */
public class UusiPeliGUIController implements ModalControllerInterface<Biljardi> {

    @FXML private ToggleGroup voittaja;
    @FXML private Button Peruuta;

    @FXML void handleOk() {
    	lisaaTestipeli();
    	//Dialogs.showMessageDialog("ei viel� kyky� rekister�id� pelej�");
    }
    @FXML void handlePeruuta() {
    	ModalController.closeStage(Peruuta);
    }
	@Override
	public Biljardi getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void handleShown() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDefault(Biljardi bilis) {
		this.biljardi = bilis;
		
	}

	

//======================================================================================================
	
	
	private Biljardi biljardi;
	
	/**
	 * Testiohjelma jolla lisätään yksi pelattu peli ja sen tulos alempiin luokkiin
	 */
	public void lisaaTestipeli() {
		Jasen pelaaja1 = biljardi.annaJasen(0); // Valitaan myöhemmin liukulistasta
		Jasen pelaaja2 = biljardi.annaJasen(1); // Tämä myös
		Boolean tulos = false; // myöhemmin määräytyy radiobuttonilla. Muista laittaa joku herja, jos radiobuttonia ei ole painettu
		biljardi.pelaa(pelaaja1, pelaaja2, tulos);
		Dialogs.showMessageDialog("Jee, se onnistui!");
	}
}
