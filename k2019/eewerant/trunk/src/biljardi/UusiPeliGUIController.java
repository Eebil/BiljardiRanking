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

/**
 * @author eewerant
 *
 */
public class UusiPeliGUIController implements ModalControllerInterface<String> {

    @FXML private ToggleGroup voittaja;
    @FXML private Button Peruuta;

    @FXML void handleOk() {
    	Dialogs.showMessageDialog("ei vielä kykyä rekisteröidä pelejä");
    }
    @FXML void handlePeruuta() {
    	ModalController.closeStage(Peruuta);
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

}
