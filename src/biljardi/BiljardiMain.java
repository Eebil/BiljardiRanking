package biljardi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import vaihe5.Biljardi;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Käyttöliittymän pääluokka, joka käynnistää ohjelman
 * @author Eetu Rantala
 * @version 31.1.2019
 *
 */

public class BiljardiMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("Paaikkuna.fxml"));
            final Pane root = ldr.load();
            final PaaikkunaGUIController biljardiCtrl = (PaaikkunaGUIController) ldr.getController(); 
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("biljardi.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Biljardi");
            
            Biljardi biljardiRanking = new Biljardi();
            biljardiCtrl.setRanking(biljardiRanking);
            
                primaryStage.setOnCloseRequest((event) -> {
               if (!biljardiCtrl.voikoSulkea()) event.consume();
               });
            primaryStage.show();
           if (!biljardiCtrl.avaa() ) Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}

