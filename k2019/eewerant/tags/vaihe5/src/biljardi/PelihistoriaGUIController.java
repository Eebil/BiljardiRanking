/**
 * 
 */
package biljardi;

import java.util.Collections;
import java.util.List;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vaihe5.Peli;

/**
 * @author eewerant
 * @version 19.3.2019
 *
 */
public class PelihistoriaGUIController implements ModalControllerInterface<List<Peli>> {

    @FXML private StringGrid<?> pelihistoriaGrid;
    @FXML private Button poistu;

    @FXML void handlePoistu() {
        ModalController.closeStage(poistu);
    }
    
    

    @Override
    public List<Peli> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(List<Peli> pelit) {
        pelihistoriaGrid.clear();
        /*pelihistoriaGrid.setColumnWidth(1, 10);
          pelihistoriaGrid.setColumnWidth(2, 5);
          pelihistoriaGrid.setColumnWidth(3, 9); */ //EI TOIMI JOSTAIN SYYSTÄ 
        Collections.reverse(pelit); // Näin pelit tulevat niin että tuorein peli näkyy ensimmäisenä
        for (Peli peli : pelit) {
            String [] osat = peli.toString().trim().split("\\|", 3);
            pelihistoriaGrid.add(osat);
        }
        
    }

}