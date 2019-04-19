/**
 * 
 */
package biljardi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import biljardi.PaaikkunaGUIController.SortNimi;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import vaihe5.Biljardi;
import vaihe5.Jasen;

/**
 * @author Eetu Rantala
 * Luokka uuden pelin rekisteröimistä varten tietokantaan
 *
 */
public class UusiPeliGUIController implements ModalControllerInterface<Biljardi> {

    @FXML private RadioButton p1RadioButton;
    @FXML private ToggleGroup voittaja;
    @FXML private RadioButton p2RadioButton;
    @FXML private Button Peruuta;
    @FXML private Label p1Label;
    @FXML private Label p2Label;
    @FXML private TextField pelaaja1HakuText;
    @FXML private ListChooser<Jasen> p1ListChooser;
    @FXML private TextField pelaaja2HakuText;
    @FXML private ListChooser<Jasen> p2ListChooser;

    @FXML
    void handleOk() {
    	pelaaPeli();
    }

    @FXML
    void handleP1Haku() {
    	haePelaajaa(p1ListChooser, pelaaja1HakuText);
    }
    
    @FXML
    void handleP2Haku() {
    	haePelaajaa(p2ListChooser, pelaaja2HakuText);
    }

    @FXML
    void handleP1Valittu() {
    	valitsePelaaja(p1ListChooser, "p1");
    }


    @FXML
    void handleP2Valittu() {
    	valitsePelaaja(p2ListChooser, "p2");
    }

    @FXML
    void handlePeruuta() {
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
		List<Jasen> lajittelu = new ArrayList<Jasen>();
        for (int i = 0; i < biljardi.getLkm(); i++) {
        	Jasen jasen = biljardi.annaJasen(i);
        	lajittelu.add(jasen);
       }
        Collections.sort(lajittelu, new SortNimi());
        for (int i = 0; i < biljardi.getLkm(); i++) {
        	 Jasen jasen = lajittelu.get(i);
        	 p1ListChooser.add(jasen.getNimi(), jasen);
        	 p2ListChooser.add(jasen.getNimi(), jasen);
        }
	}

	

//======================================================================================================
	
	
	private Biljardi biljardi;
	private Jasen p1;
	private Jasen p2;
	private Boolean tulos;
	
	/**
	 * Testiohjelma jolla lisätään yksi pelattu peli ja sen tulos alempiin luokkiin
	 */
	public void pelaaPeli() {
		if (p1 == null || p2 == null) {Dialogs.showMessageDialog("Valitse pelaajat!"); return;}
		if (!p1RadioButton.isSelected() && !p2RadioButton.isSelected()) {Dialogs.showMessageDialog("Valitse voittaja!"); return;}
		if (p1 == p2) {Dialogs.showMessageDialog("Onneksi olkon! Voitit itsesi :^)"); return;}
		tulos = p1RadioButton.isSelected();
		biljardi.pelaa(p1, p2, tulos);
		@SuppressWarnings("hiding")
        String voittaja;
		if(tulos) voittaja = p1.getNimi();
		else voittaja = p2.getNimi();
		Dialogs.showMessageDialog("Onnittelut voitosta " + voittaja + "!");
		ModalController.closeStage(Peruuta);
	}
	/**
	 * asetetaan valittu pelaaja jommaksi kummaksi pelaajaksi ja laitetaan pelaajan nimi labeliin
	 * @param chooser ListChooser, josta pelaaja haetaan
	 * @param pelaaja apumerkkijono, joka kertoo kumpi pelaaja kyseessa
	 */
	public void valitsePelaaja(ListChooser<Jasen> chooser, String pelaaja) {
	    if (chooser.getSelectedObject() == null) return;
		if (pelaaja.equals("p1")) {
			p1 = chooser.getSelectedObject();
			p1Label.setText(chooser.getSelectedObject().getNimi());
		}
		else {
			p2 = chooser.getSelectedObject();
			p2Label.setText(chooser.getSelectedObject().getNimi());
		}
		
	}
	
	/**
	 * Haetaan pelajaa listChooseriin Hakukentän avulla
	 * @param hakuLista Chooser, johon tulokset tuodaan järjestyksessä
	 * @param hakuField Kenttä johon hakusana kirjoitetaan
	 */
	public void haePelaajaa(ListChooser<Jasen> hakuLista, TextField hakuField) {
	     hakuLista.clear();
	     List<Jasen> osumat = new ArrayList<Jasen>();
	     String hakusana = "(?i:.*" + hakuField.getText().trim() + ".*)";
	     System.out.println(hakusana);
	     for (int i = 0 ; i < biljardi.getLkm(); i++) {
	         Jasen j = biljardi.annaJasen(i);
	         if (j.getNimi().matches(hakusana)) osumat.add(j);
	     }
	     
	     Collections.sort(osumat, new SortNimi());
	     for (int i = 0 ; i < osumat.size(); i++) {
	         hakuLista.add(osumat.get(i).getNimi(), osumat.get(i));
	     }
	 }
	
	
}
