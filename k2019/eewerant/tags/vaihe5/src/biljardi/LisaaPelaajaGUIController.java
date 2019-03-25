/**
 * 
 */
package biljardi;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vaihe5.Biljardi;

/**
 * @author eewerant
 * @version 22.3.2019
 * kontrolleri uuden pelaajan lisäämiseksi jäsenistöön
 */
public class LisaaPelaajaGUIController implements ModalControllerInterface<Biljardi>{


    @FXML private TextField nimiText;
    @FXML private TextField vuosikurssiText;
    @FXML private Label tarkistusLabel;
    @FXML private Button tallennaButton;
    @FXML private Button peruutaButton;
    
    @FXML void handlePeruuta() {
        ModalController.closeStage(peruutaButton);
    }

    @FXML void handleTallenna() {
        tallennaJasen();
    }


    @Override
    public Biljardi getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        tallennaButton.requestFocus();
        
    }

    @Override
    public void setDefault(Biljardi biljardi) {
        this.biljardi = biljardi;
        
    }
    
//=============================================================================//
    
    private Biljardi biljardi;
    

    /**
     * luodaan uusi jäsen jäsenistöön ja lisätään se sinne
     */
    public void tallennaJasen() {
        if (!(nimiText.getText().length() < 25)) {
            tarkistusLabel.setText("Antamasi nimi saa olla max 25 merkkiä");
        } 
        else if (!(vuosikurssiText.getText().matches("\\d{4}"))) {
            tarkistusLabel.setText("Anna vuosikurssi muodossa: VVVV");
        }
        else {
            biljardi.lisaaJasen(nimiText.getText(), Integer.parseInt(vuosikurssiText.getText()));
            ModalController.closeStage(tallennaButton);
        }     
    }

}


