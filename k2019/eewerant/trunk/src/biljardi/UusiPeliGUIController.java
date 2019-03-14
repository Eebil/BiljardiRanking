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
    	Dialogs.showMessageDialog("ei vielä kykyä rekisteröidä pelejä");
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
	public void setDefault(Biljardi arg0) {
		// TODO Auto-generated method stub
		
	}

	
//======================================================================================================
	
	public void lisaaTestipeli() {
		Biljardi biljardi = getResult();
		Jasen pelaaja1 = biljardi.annaJasen(0);
		Jasen pelaaja2 = biljardi.annaJasen(1);
		
	}
}
